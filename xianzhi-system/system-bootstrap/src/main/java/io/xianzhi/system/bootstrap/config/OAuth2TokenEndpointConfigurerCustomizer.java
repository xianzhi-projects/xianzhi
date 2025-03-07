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

import io.xianzhi.common.oauth2.authorization.converters.PasswordAuthenticationConverter;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2TokenEndpointConfigurer;
import org.springframework.security.oauth2.server.authorization.web.authentication.*;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Token认证配置
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2TokenEndpointConfigurerCustomizer implements Customizer<OAuth2TokenEndpointConfigurer> {
    /**
     * 认证成功处理
     */
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    /**
     * 认证失败处理
     */
    @Resource
    private AuthenticationFailureHandler xianZhiAuthenticationFailureHandler;


    /**
     * Performs the customizations on the input argument.
     *
     * @param oAuth2TokenEndpointConfigurer the input argument
     */
    @Override
    public void customize(OAuth2TokenEndpointConfigurer oAuth2TokenEndpointConfigurer) {
        oAuth2TokenEndpointConfigurer
                .accessTokenRequestConverter(getAccessTokenConverter())
                // 登录成功处理器
                .accessTokenResponseHandler(authenticationSuccessHandler)
                // 登录失败处理器
                .errorResponseHandler(xianZhiAuthenticationFailureHandler);
    }


    /**
     * 获取认证转换器
     *
     * @return 认证转换器
     */
    private AuthenticationConverter getAccessTokenConverter() {
        return new DelegatingAuthenticationConverter(Arrays.asList(
                new PasswordAuthenticationConverter(),
                new OAuth2RefreshTokenAuthenticationConverter(),
                new OAuth2ClientCredentialsAuthenticationConverter(),
                new OAuth2AuthorizationCodeAuthenticationConverter(),
                new OAuth2AuthorizationCodeRequestAuthenticationConverter()));
    }
}
