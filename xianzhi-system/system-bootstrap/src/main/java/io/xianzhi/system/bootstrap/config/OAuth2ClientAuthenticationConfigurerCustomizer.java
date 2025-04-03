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
 * OAuth2 Client Authentication Configurer Customizer
 * This class provides a customizer for configuring the OAuth2ClientAuthenticationConfigurer,
 * which is part of Spring Security’s OAuth 2.0 Authorization Server setup. It allows customization
 * of client authentication behavior, specifically by setting a custom failure handler for
 * authentication errors. The class is registered as a Spring component, uses SLF4J for logging,
 * and employs constructor-based dependency injection for required dependencies, with field-based
 * injection for the failure handler.
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2ClientAuthenticationConfigurerCustomizer implements Customizer<OAuth2ClientAuthenticationConfigurer> {

    /**
     * Client Authentication Failure Handler
     * This field holds an AuthenticationFailureHandler instance responsible for handling client
     * authentication failures (e.g., invalid client credentials). It is injected via Spring’s
     *
     * &#064;Resource  annotation, allowing the customizer to delegate error responses to this handler.
     * The specific implementation (xianZhiClientAuthenticationFailureHandler) is assumed to be
     * defined elsewhere in the application.
     */
    @Resource
    private AuthenticationFailureHandler xianZhiClientAuthenticationFailureHandler;

    /**
     * Customize OAuth2 Client Authentication Configurer
     * This method implements the Customizer interface to apply customizations to the provided
     * OAuth2ClientAuthenticationConfigurer. It configures the configurer to use a custom error
     * response handler (xianZhiClientAuthenticationFailureHandler) for client authentication
     * failures, enabling tailored error handling (e.g., custom error messages or HTTP responses)
     * when client authentication does not succeed.
     *
     * @param oAuth2ClientAuthenticationConfigurer The OAuth2ClientAuthenticationConfigurer instance
     *                                             to be customized.
     */
    @Override
    public void customize(OAuth2ClientAuthenticationConfigurer oAuth2ClientAuthenticationConfigurer) {
        oAuth2ClientAuthenticationConfigurer
                // Set the custom failure handler for client authentication errors
                .errorResponseHandler(xianZhiClientAuthenticationFailureHandler);
    }
}
