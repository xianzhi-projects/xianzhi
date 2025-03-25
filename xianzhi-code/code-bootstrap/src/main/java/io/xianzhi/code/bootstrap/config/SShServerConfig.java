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

import io.xianzhi.code.bootstrap.factory.DisableShellFactory;
import io.xianzhi.code.bootstrap.factory.XianZhiGitCommandFactory;
import io.xianzhi.code.bootstrap.properties.CodeServerProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.sshd.git.GitLocationResolver;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.auth.pubkey.PublickeyAuthenticator;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SSH服务配置
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class SShServerConfig {

    /**
     * 服务配置类
     */
    private final CodeServerProperties codeServerProperties;

    private final PublickeyAuthenticator xianZhiPublicKeyAuthenticator;

    private final GitLocationResolver gitLocationResolver;


    @Bean
    public SshServer sshServer() {
        SshServer sshd = SshServer.setUpDefaultServer();
        sshd.setPort(codeServerProperties.getSshServerPort());
        // 设置主机密钥
        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider());
        // 设置命令工厂
        sshd.setCommandFactory(new XianZhiGitCommandFactory(gitLocationResolver));
        // 设置公钥
        sshd.setPublickeyAuthenticator(xianZhiPublicKeyAuthenticator);
        // 禁用shell
        sshd.setShellFactory(new DisableShellFactory());
        // 禁用密码认证
        sshd.setPasswordAuthenticator(null);
        try {
            sshd.start();
        } catch (Exception exception) {
            log.error("开启SSH服务失败:{}", exception.getMessage(), exception);
            throw new RuntimeException(exception);

        }
        return sshd;
    }
}
