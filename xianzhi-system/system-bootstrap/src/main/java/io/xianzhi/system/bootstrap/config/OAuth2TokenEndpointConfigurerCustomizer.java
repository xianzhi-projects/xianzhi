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
 * OAuth2 Token Endpoint Configurer Customizer
 * This class provides a customizer for configuring the OAuth2TokenEndpointConfigurer, which is
 * part of Spring Security’s OAuth 2.0 Authorization Server setup. It customizes the token endpoint
 * by defining how access token requests are converted, and how success and failure responses are
 * handled. The class is registered as a Spring component, uses SLF4J for logging, and employs
 * constructor-based dependency injection for required dependencies, with field-based injection
 * for the failure handler.
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2TokenEndpointConfigurerCustomizer implements Customizer<OAuth2TokenEndpointConfigurer> {

    /**
     * Authentication Success Handler
     * This field holds an AuthenticationSuccessHandler instance responsible for handling successful
     * authentication events at the token endpoint (e.g., issuing an access token response). It is
     * injected via constructor-based dependency injection, ensuring it is required and immutable.
     */
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    /**
     * Authentication Failure Handler
     * This field holds an AuthenticationFailureHandler instance responsible for handling
     * authentication failures at the token endpoint (e.g., invalid credentials or grant type).
     * It is injected via Spring’s @Resource annotation, allowing the customizer to delegate error
     * responses to this handler. The specific implementation (xianZhiAuthenticationFailureHandler)
     * is assumed to be defined elsewhere in the application.
     */
    @Resource
    private AuthenticationFailureHandler xianZhiAuthenticationFailureHandler;

    /**
     * Customize OAuth2 Token Endpoint Configurer
     * This method implements the Customizer interface to apply customizations to the provided
     * OAuth2TokenEndpointConfigurer. It configures the token endpoint by setting a composite
     * authentication converter for handling various grant types, and assigns handlers for
     * successful and failed authentication attempts. This enables support for multiple OAuth 2.0
     * flows (e.g., password, refresh token, client credentials) with tailored response handling.
     *
     * @param oAuth2TokenEndpointConfigurer The OAuth2TokenEndpointConfigurer instance to be customized.
     */
    @Override
    public void customize(OAuth2TokenEndpointConfigurer oAuth2TokenEndpointConfigurer) {
        oAuth2TokenEndpointConfigurer
                // Set a composite converter for access token requests
                .accessTokenRequestConverter(getAccessTokenConverter())
                // Handler for successful token issuance
                .accessTokenResponseHandler(authenticationSuccessHandler)
                // Handler for authentication errors
                .errorResponseHandler(xianZhiAuthenticationFailureHandler);
    }

    /**
     * Get Access Token Converter
     * This method creates and returns a composite AuthenticationConverter that delegates to a list
     * of specific converters for different OAuth 2.0 grant types. It supports password, refresh
     * token, client credentials, authorization code, and authorization code request flows,
     * allowing the token endpoint to process a variety of authentication requests.
     *
     * @return An AuthenticationConverter instance capable of handling multiple OAuth 2.0 grant types.
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
