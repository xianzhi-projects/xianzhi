package io.xianzhi.common.jsch;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import io.xianzhi.common.jsch.model.JavaEnvironment;
import io.xianzhi.common.jsch.model.OsInfo;
import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Consumer;

/**
 * Jsch tool
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
public class JschUtils {

    private static final ThreadPoolTaskExecutor executor;

    static {
        executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("sshThreadPool-");
        executor.setAllowCoreThreadTimeOut(true);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.initialize();
    }


    /**
     * 通过密码获取Session
     *
     * @param host     主机地址
     * @param port     主机端口
     * @param username 用户名
     * @param password 密码
     * @return Session
     */
    public static Session getSessionByPassword(String host, int port, String username, String password) throws JSchException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(username, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        // 30 秒超时
        session.connect(30000);
        return session;
    }

    /**
     * 通过私钥获取Session
     *
     * @param host       主机地址
     * @param port       端口
     * @param username   用户名
     * @param privateKey 私钥
     * @return Session
     */
    public static Session getSessionByPrivateKey(String host, int port, String username, String privateKey) throws JSchException {
        JSch jsch = new JSch();
        byte[] privateKeyBytes = privateKey.getBytes(StandardCharsets.UTF_8);
        // 加载私钥字符串
        jsch.addIdentity(username, privateKeyBytes, null, null);
        Session session = jsch.getSession(username, host, port);
        session.setConfig("StrictHostKeyChecking", "no");
        // 30 秒超时
        session.connect(30000);
        return session;
    }

    /**
     * 执行命令并实时回调
     *
     * @param session  SSH 会话
     * @param command  执行的命令
     * @param callback 处理输出回调（stdout 和 stderr）
     */
    public static void executeCommand(Session session, String command, Consumer<String> callback) {
        if (session == null || !session.isConnected()) {
            throw new BusinessException(CommonCode.ERROR);
        }

        ChannelExec channel = null;
        try {
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            channel.setInputStream(null);

            // 捕获标准输出和错误输出
            InputStream stdout = channel.getInputStream();
            InputStream stderr = channel.getErrStream();

            channel.connect();

            // 提交标准输出的处理任务
            Future<?> stdoutFuture = executor.submit(() -> readStream(stdout, callback));

            // 提交错误输出的处理任务
            Future<?> stderrFuture = executor.submit(() -> readStream(stderr, callback));

            // 等待标准输出和错误输出处理任务完成
            stdoutFuture.get();  // 阻塞直到标准输出任务完成
            stderrFuture.get();  // 阻塞直到错误输出任务完成

        } catch (Exception e) {
            log.error("执行命令失败: {}", e.getMessage(), e);
            throw new BusinessException(CommonCode.ERROR);
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }
    }

    /**
     * 读取流数据并传递给回调
     *
     * @param inputStream 输入流
     * @param callback    处理流数据的回调
     */
    private static void readStream(InputStream inputStream, Consumer<String> callback) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                callback.accept(line);
            }
        } catch (IOException e) {
            log.error("读取流数据失败: {}", e.getMessage(), e);
        }
    }


    /**
     * 执行命令并通过回调返回输出
     *
     * @param session SSH 会话
     * @param command 命令
     * @return 命令输出
     */
    public static String executeCommand(Session session, String command) {
        final StringBuilder result = new StringBuilder();
        executeCommand(session, command, line -> result.append(line).append("\n"));
        return result.toString().trim();
    }


    /**
     * 获取远程系统信息
     *
     * @param session SSH 会话
     * @return OsInfo 结构体
     */
    public static OsInfo getOsInfo(Session session) {
        if (session == null || !session.isConnected()) {
            throw new IllegalStateException("SSH 会话未连接");
        }
        OsInfo osInfo = new OsInfo();
        try {
            osInfo.setOsPlatform(executeCommand(session, "uname -s"));
            osInfo.setOsArch(executeCommand(session, "uname -m"));
            osInfo.setOsName(executeCommand(session, "cat /etc/os-release | grep '^NAME=' | cut -d'=' -f2 | tr -d '\"'"));
            osInfo.setOsVersion(executeCommand(session, "cat /etc/os-release | grep '^VERSION=' | cut -d'=' -f2 | tr -d '\"'"));
            osInfo.setOsDescription(executeCommand(session, "lsb_release -d | cut -f2"));
            osInfo.setOsLanguage(executeCommand(session, "echo $LANG"));
            osInfo.setOsTimezone(executeCommand(session, "date +%Z"));
            osInfo.setOsCountry(executeCommand(session, "echo $LANG | cut -d'_' -f2"));
        } catch (Exception e) {
            throw new RuntimeException("获取操作系统信息失败", e);
        }
        return osInfo;
    }


    /**
     * 获取远程主机的 Java 环境信息
     *
     * @param session SSH 会话
     * @return JavaEnvironment 对象，如果没有安装 Java，则返回 null
     */
    public static JavaEnvironment getJavaEnvironment(Session session) {
        JavaEnvironment environment = new JavaEnvironment();

        try {
            // 执行命令获取 Java 版本信息
            String versionOutput = executeCommand(session, "java -version");
            String pathOutput = executeCommand(session, "which java");

            // 如果返回的版本信息为空，则表示 Java 没有安装
            if (versionOutput.isEmpty()) {
                return null;
            }

            // 设置 Java 路径
            environment.setPath(pathOutput.trim());

            // 解析版本信息并设置
            environment.setVersion(parseJavaVersion(versionOutput));

            // 设置 Java 实现类型（OpenJDK 或其他）
            String javaName = getJavaName(versionOutput);
            environment.setName(javaName);

        } catch (Exception e) {
            return null; // 如果发生异常，表示无法获取 Java 环境信息
        }

        return environment;
    }


    /**
     * 解析 Java 版本信息
     *
     * @param output 命令输出
     * @return Java 版本
     */
    private static String parseJavaVersion(String output) {
        String version = null;

        // 逐行检查输出，提取版本信息
        for (String line : output.split("\n")) {
            if (line.startsWith("openjdk") || line.startsWith("java version")) {
                // 提取版本号
                String[] parts = line.split("\"");
                if (parts.length > 1) {
                    version = parts[1].trim(); // 版本号位于双引号内
                }
                break;
            }
        }
        return version;
    }

    /**
     * 获取 Java 名称（OpenJDK 或其他 Java 实现）
     *
     * @param output 命令输出
     * @return Java 实现的名称
     */
    private static String getJavaName(String output) {
        if (output.contains("OpenJDK")) {
            return "OpenJDK";
        }

        // 如果是其他 Java 实现（例如 Oracle JDK），可以根据需要扩展
        if (output.contains("Oracle")) {
            return "Oracle JDK";
        }

        // 如果无法确定，返回默认值
        return "Unknown Java";
    }
}