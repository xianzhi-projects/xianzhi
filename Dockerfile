# 使用 Eclipse Temurin 提供的 OpenJDK 17 作为基础镜像
# 第一阶段用于构建，包含完整的 JDK
FROM registry-hub.xianzhi.io/eclipse-temurin:17-jdk AS builder

# 设置工作目录为 /app
WORKDIR /app

# 复制 JAR 文件到容器的工作目录
COPY system-bootstrap/target/system-bootstrap-1.0.0-SNAPSHOT.jar app.jar

# 运行阶段：使用多阶段构建，仅保留运行时所需的内容
# 使用 JRE 镜像以减少镜像体积
FROM registry-hub.xianzhi.io/eclipse-temurin:17-jre

# 设置工作目录为 /app
WORKDIR /app

# 从构建阶段复制 JAR 文件到运行阶段镜像中
COPY --from=builder /app/app.jar /app/app.jar

# 设置容器内的环境变量
# JAVA_OPTS 用于传递 JVM 参数，例如内存设置
# SPRING_PROFILES_ACTIVE 指定 Spring Boot 的运行环境
# PORT 定义应用监听的端口，固定为 8002
ENV JAVA_OPTS="" \
    SPRING_PROFILES_ACTIVE="prod" \
    PORT=8002

# 暴露容器端口 8002
EXPOSE 8002

# 指定容器启动时的默认命令
# 使用 exec 形式运行 Java 命令，支持通过 JAVA_OPTS 传递 JVM 参数
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]

# 添加健康检查，确保容器运行正常
# 假设您的 Spring Boot 项目启用了 Actuator，并提供了 /actuator/health 端点
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8002/actuator/health || exit 1

# 设置容器运行时的用户（安全性考虑）
# 默认使用 temurin 用户
USER temurin

# 添加元数据标签
LABEL maintainer="Max  max@xianzhi.io" \
      version="1.0" \
      description="研发效能平台 | 先知  Spring Boot 3 running  JDK 17"