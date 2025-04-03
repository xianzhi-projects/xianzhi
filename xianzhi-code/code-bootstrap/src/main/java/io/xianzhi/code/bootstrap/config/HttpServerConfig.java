/*
 *  Copyright 2025 XianZhi Group .
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.xianzhi.code.bootstrap.config;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.http.server.GitServlet;
import org.eclipse.jgit.http.server.ReceivePackErrorHandler;
import org.eclipse.jgit.http.server.UploadPackErrorHandler;
import org.eclipse.jgit.transport.resolver.ReceivePackFactory;
import org.eclipse.jgit.transport.resolver.RepositoryResolver;
import org.eclipse.jgit.transport.resolver.UploadPackFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HTTP 服务配置类，用于初始化和管理 Git 的 HTTP 服务。
 * <p>
 * 该类通过 Spring 的配置机制，注册并配置 {@link GitServlet}，以支持通过 HTTP 协议访问 Git 仓库。
 * 包括解析 Git 仓库、处理上传和接收操作的工厂类，以及错误处理机制。
 * </p>
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class HttpServerConfig {

    /**
     * 默认的 Git Servlet 映射路径
     */
    private static final String GIT_SERVLET_MAPPING = "/*";
    /**
     * Git Servlet 的名称
     */
    private static final String GIT_SERVLET_NAME = "XianZhiGitServlet";

    /**
     * Git 仓库解析器，用于根据 HTTP 请求定位对应的 Git 仓库。
     * <p>
     * 通过分析 {@link HttpServletRequest}（如 URL 或参数），将请求映射到服务器上的 {@link org.eclipse.jgit.lib.Repository} 对象。
     * </p>
     */
    private final RepositoryResolver<HttpServletRequest> repositoryResolver;

    /**
     * 上传包错误处理器，用于处理客户端执行 'git fetch' 或 'git clone' 时发生的错误。
     * <p>
     * 在 Git 智能 HTTP 协议中，'upload-pack' 负责响应客户端的拉取请求。
     * 该处理器捕获网络中断、认证失败等异常，并提供自定义错误处理逻辑。
     * </p>
     */
    private final UploadPackErrorHandler uploadPackErrorHandler;

    /**
     * 接收包错误处理器，用于处理客户端执行 'git push' 时发生的错误。
     * <p>
     * 在 Git 智能 HTTP 协议中，'receive-pack' 负责接收客户端推送的数据。
     * 该处理器处理权限不足、数据冲突等异常，并支持自定义错误响应。
     * </p>
     */
    private final ReceivePackErrorHandler receivePackErrorHandler;

    /**
     * 上传包工厂，用于创建 'upload-pack' 服务实例。
     * <p>
     * 根据 {@link HttpServletRequest} 创建 {@link org.eclipse.jgit.transport.UploadPack} 对象，
     * 用于打包仓库数据并发送给客户端，支持自定义逻辑（如权限控制）。
     * </p>
     */
    private final UploadPackFactory<HttpServletRequest> uploadPackFactory;

    /**
     * 接收包工厂，用于创建 'receive-pack' 服务实例。
     * <p>
     * 根据 {@link HttpServletRequest} 创建 {@link org.eclipse.jgit.transport.ReceivePack} 对象，
     * 用于接收客户端推送的数据并更新仓库，支持自定义逻辑（如分支验证）。
     * </p>
     */
    private final ReceivePackFactory<HttpServletRequest> receivePackFactory;

    /**
     * 配置并返回 GitServlet 实例，用于处理 Git 的 HTTP 请求。
     *
     * @return 配置完成的 {@link GitServlet} 对象
     */
    @Bean
    public GitServlet gitServlet() {
        GitServlet gitServlet = new GitServlet();
        configureGitServlet(gitServlet);
        return gitServlet;
    }

    /**
     * 注册 GitServlet 到 Spring 的 Servlet 容器。
     *
     * @return {@link ServletRegistrationBean} 对象，用于定义 Servlet 的映射和启动顺序
     */
    @Bean
    public ServletRegistrationBean<HttpServlet> gitServletRegistration() {
        ServletRegistrationBean<HttpServlet> registrationBean =
                new ServletRegistrationBean<>(gitServlet(), GIT_SERVLET_MAPPING);
        registrationBean.setName(GIT_SERVLET_NAME);
        registrationBean.setLoadOnStartup(1);
        log.info("Registered GitServlet with name '{}' and mapping '{}'", GIT_SERVLET_NAME, GIT_SERVLET_MAPPING);
        return registrationBean;
    }

    /**
     * 配置 GitServlet 的各个组件。
     *
     * @param gitServlet 要配置的 {@link GitServlet} 实例
     */
    private void configureGitServlet(GitServlet gitServlet) {
        gitServlet.setRepositoryResolver(repositoryResolver);
        gitServlet.setUploadPackFactory(uploadPackFactory);
        gitServlet.setReceivePackFactory(receivePackFactory);
        gitServlet.setUploadPackErrorHandler(uploadPackErrorHandler);
        gitServlet.setReceivePackErrorHandler(receivePackErrorHandler);
    }
}