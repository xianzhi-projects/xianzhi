/*
 * Copyright 2025 XianZhi Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.xianzhi.code.bootstrap.factory;

import lombok.Getter;
import org.apache.sshd.server.Environment;
import org.apache.sshd.server.ExitCallback;
import org.apache.sshd.server.channel.ChannelSession;
import org.apache.sshd.server.command.Command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * SSH 错误消息命令类，用于向客户端返回带有红色字体的错误信息。
 * <p>
 * 该类实现了 {@link Command} 接口，通过 SSH 通道向客户端发送错误消息。
 * 支持使用 ANSI 颜色代码将错误消息显示为红色，适用于支持 ANSI 的终端。
 * </p>
 *
 * @author Max
 * @since 1.0.0
 */
public class ErrorCommand implements Command {

    /** ANSI 红色字体代码 */
    private static final String RED_COLOR = "\u001b[31m";
    /** ANSI 重置颜色代码 */
    private static final String RESET_COLOR = "\u001b[0m";

    /** 错误消息内容 */
    @Getter
    private final String errorMessage;

    /** 输入流，用于接收客户端输入 */
    private InputStream inputStream;

    /** 输出流，用于向客户端发送输出 */
    private OutputStream outputStream;

    /** 错误流，用于向客户端发送错误信息 */
    private OutputStream errorStream;

    /** 退出回调，用于通知命令执行完成 */
    private ExitCallback exitCallback;

    /**
     * 构造方法，初始化错误消息。
     *
     * @param errorMessage 要发送给客户端的错误消息
     */
    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage != null ? errorMessage.trim() : "";
    }

    /**
     * 启动命令执行，将红色错误消息发送给客户端。
     * <p>
     * 在启动时，将错误消息以红色字体写入错误流，并触发退出回调。
     * 所有流必须在此方法调用前设置完成。
     * </p>
     *
     * @param channel SSH 会话通道
     * @param env     环境变量
     * @throws IOException 如果写入错误流或关闭流时发生错误
     */
    @Override
    public void start(ChannelSession channel, Environment env) throws IOException {
        try {
            // 规范化消息，确保换行清晰
            String coloredMessage = RED_COLOR + errorMessage.trim() + RESET_COLOR + "\r\n";
            errorStream.write(coloredMessage.getBytes());
            errorStream.flush();
            exitCallback.onExit(0);
        } finally {
            closeStreams();
        }
    }

    /**
     * 设置退出回调。
     *
     * @param callback 当命令结束时调用的退出回调
     */
    @Override
    public void setExitCallback(ExitCallback callback) {
        this.exitCallback = callback;
    }

    /**
     * 设置错误输出流。
     *
     * @param err 用于输出错误信息的流
     */
    @Override
    public void setErrorStream(OutputStream err) {
        this.errorStream = err;
    }

    /**
     * 设置输入流。
     *
     * @param in 用于接收输入的流
     */
    @Override
    public void setInputStream(InputStream in) {
        this.inputStream = in;
    }

    /**
     * 设置输出流。
     *
     * @param out 用于输出正常信息的流
     */
    @Override
    public void setOutputStream(OutputStream out) {
        this.outputStream = out;
    }

    /**
     * 销毁命令，清理资源。
     * <p>
     * 当客户端断开连接时，由 SSH 服务器调用此方法以关闭通道。
     * </p>
     *
     * @param channel SSH 会话通道
     * @throws IOException 如果关闭通道时发生错误
     */
    @Override
    public void destroy(ChannelSession channel) throws IOException {
        channel.close();
    }

    /**
     * 关闭所有流资源。
     * <p>
     * 在命令执行完成后，确保输入流、输出流和错误流都被正确关闭。
     * </p>
     * @throws IOException 如果关闭流时发生错误
     */
    private void closeStreams() throws IOException {
        if (inputStream != null) {
            inputStream.close();
        }
        if (outputStream != null) {
            outputStream.close();
        }
        if (errorStream != null) {
            errorStream.close();
        }
    }
}