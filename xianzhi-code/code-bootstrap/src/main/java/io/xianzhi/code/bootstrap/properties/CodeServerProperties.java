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

package io.xianzhi.code.bootstrap.properties;

import io.xianzhi.core.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.sshd.git.GitLocationResolver;
import org.apache.sshd.server.session.ServerSession;
import org.eclipse.jgit.errors.RepositoryNotFoundException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.ServiceMayNotContinueException;
import org.eclipse.jgit.transport.resolver.RepositoryResolver;
import org.eclipse.jgit.transport.resolver.ServiceNotAuthorizedException;
import org.eclipse.jgit.transport.resolver.ServiceNotEnabledException;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;

/**
 * 代码托管服务配置类
 *
 * @author Max
 * @since 1.0.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "xianzhi.code")
public class CodeServerProperties implements InitializingBean {


    /**
     * 代码托管服务地址
     */
    private String externalUrl;

    /**
     * SSH服务器端口
     */
    private Integer sshServerPort = 22;

    /**
     * 仓库地址
     */
    private String repositoryDir = "/etc/xianzhi/code/repository";


    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        if (!StringUtils.hasText(externalUrl)) {
            throw new BusinessException("请配置托管地址");
        }
        if (!StringUtils.hasText(repositoryDir)) {
            repositoryDir = "/etc/xianzhi/code/repository";
        }
        if (sshServerPort <= 0 || sshServerPort >= 65535) {
            sshServerPort = 22;
        }
    }

    /**
     * Git仓库加载
     *
     * @author Max
     * @since 1.0.0
     */
    @Slf4j
    @Component
    @RequiredArgsConstructor
    public static class XianZhiGitLocationResolver implements GitLocationResolver {
        /**
         * @param command The complete received command
         * @param args    The command split into arguments - {@code args[0]} is the &quot;pure&quot; command itself
         *                without any other arguments. <B>Note:</B> changing the content of the arguments array may
         *                affect command execution in undetermined ways, due to invocation code changes without prior
         *                notice, so <U>highly recommended to avoid it</U>.
         * @param session The {@link ServerSession} through which the command was received
         * @param fs      The {@link FileSystem} associated with the server session
         * @return The local GIT repository root path
         * @throws IOException If failed to resolve
         */
        @Override
        public Path resolveRootDirectory(String command, String[] args, ServerSession session, FileSystem fs) throws IOException {
            return null;
        }
    }

    /**
     * 仓库加载
     *
     * @author Max
     * @since 1.0.0
     */
    @Slf4j
    @Component
    public static class HttpRepositoryResolver implements RepositoryResolver<HttpServletRequest> {
        /**
         * Locate and open a reference to a {@link Repository}.
         * <p>
         * The caller is responsible for closing the returned Repository.
         *
         * @param req  the current request, may be used to inspect session state
         *             including cookies or user authentication.
         * @param name name of the repository, as parsed out of the URL.
         * @return the opened repository instance, never null.
         * @throws RepositoryNotFoundException    the repository does not exist or the name is incorrectly
         *                                        formatted as a repository name.
         * @throws ServiceNotAuthorizedException  the repository may exist, but HTTP access is not allowed
         *                                        without authentication, i.e. this corresponds to an HTTP 401
         *                                        Unauthorized.
         * @throws ServiceNotEnabledException     the repository may exist, but HTTP access is not allowed on the
         *                                        target repository, for the current user.
         * @throws ServiceMayNotContinueException the repository may exist, but HTTP access is not allowed for
         *                                        the current request. The exception message contains a detailed
         *                                        message that should be shown to the user.
         */
        @Override
        public Repository open(HttpServletRequest req, String name) throws RepositoryNotFoundException, ServiceNotAuthorizedException, ServiceNotEnabledException, ServiceMayNotContinueException {
            return null;
        }
    }
}
