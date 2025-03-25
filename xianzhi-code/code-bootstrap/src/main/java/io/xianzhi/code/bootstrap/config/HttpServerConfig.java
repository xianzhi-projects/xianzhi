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
 * HTTP配置
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class HttpServerConfig {

    /**
     *
     */
    private final RepositoryResolver<HttpServletRequest> httpRepositoryResolver;
    /**
     *
     */
    private final UploadPackErrorHandler xianZhiUploadPackErrorHandler;
    /**
     *
     */
    private final ReceivePackErrorHandler xianZhiReceivePackErrorHandler;
    /**
     *
     */
    private final UploadPackFactory<HttpServletRequest> xianZhiUploadPackFactory;
    /**
     *
     */
    private final ReceivePackFactory<HttpServletRequest> xianZhiReceivePackFactory;


    @Bean
    public GitServlet gitServlet() {
        GitServlet gitServlet = new GitServlet();
        gitServlet.setRepositoryResolver(httpRepositoryResolver);
        gitServlet.setUploadPackFactory(xianZhiUploadPackFactory);
        gitServlet.setReceivePackFactory(xianZhiReceivePackFactory);
        gitServlet.setUploadPackErrorHandler(xianZhiUploadPackErrorHandler);
        gitServlet.setReceivePackErrorHandler(xianZhiReceivePackErrorHandler);
        return gitServlet;
    }

    @Bean
    public ServletRegistrationBean<HttpServlet> myServletRegistration() {
        // 注册 Servlet
        ServletRegistrationBean<HttpServlet> registrationBean = new ServletRegistrationBean<>(gitServlet(), "/*");
        registrationBean.setName("XianZhiGitServlet");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }
}
