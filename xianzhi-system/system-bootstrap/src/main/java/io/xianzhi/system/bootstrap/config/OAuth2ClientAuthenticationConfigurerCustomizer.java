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

package io.xianzhi.system.bootstrap.config;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2ClientAuthenticationConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 客户端认证配置
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2ClientAuthenticationConfigurerCustomizer implements Customizer<OAuth2ClientAuthenticationConfigurer> {

    /**
     * 客户端认证失败处理
     */
    @Resource
    private AuthenticationFailureHandler xianZhiClientAuthenticationFailureHandler;

    /**
     * Performs the customizations on the input argument.
     *
     * @param oAuth2ClientAuthenticationConfigurer the input argument
     */
    @Override
    public void customize(OAuth2ClientAuthenticationConfigurer oAuth2ClientAuthenticationConfigurer) {
        oAuth2ClientAuthenticationConfigurer
                // 客户端认证失败处理
                .errorResponseHandler(xianZhiClientAuthenticationFailureHandler);
    }
}
