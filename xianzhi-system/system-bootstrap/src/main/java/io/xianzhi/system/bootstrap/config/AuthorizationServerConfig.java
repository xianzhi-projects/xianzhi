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

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import io.xianzhi.common.oauth2.authorization.properties.OAuth2Properties;
import io.xianzhi.common.oauth2.authorization.providers.PasswordAuthenticationProvider;
import io.xianzhi.system.bootstrap.oauth2.DaoAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

/**
 * 认证服务配置
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfig {

    /**
     * token认证配置
     */
    private final OAuth2TokenEndpointConfigurerCustomizer oAuth2TokenEndpointConfigurerCustomizer;

    /**
     * 客户端认证配置
     */
    private final OAuth2ClientAuthenticationConfigurerCustomizer oAuth2ClientAuthenticationConfigurerCustomizer;

    /**
     * 安全配置
     */
    private final OAuth2Properties oAuth2Properties;


    /**
     * 认证服务安全过滤链
     *
     * @param http http安全配置
     * @return SecurityFilterChain
     * @throws Exception 异常
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        OAuth2AuthorizationServerConfigurer configurer = http.getConfigurer(OAuth2AuthorizationServerConfigurer.class);
        configurer.tokenEndpoint(oAuth2TokenEndpointConfigurerCustomizer)
                .clientAuthentication(oAuth2ClientAuthenticationConfigurerCustomizer)
                .authorizationServerSettings(AuthorizationServerSettings.builder().issuer(oAuth2Properties.getIssuer()).build());
        http.oauth2ResourceServer((resourceServer) -> resourceServer
                .jwt(Customizer.withDefaults()));
        DefaultSecurityFilterChain securityFilterChain = http.build();
        addCustomOAuth2GrantAuthenticationProvider(http);
        return securityFilterChain;
    }


    /**
     * 配置jwk源，使用非对称加密，公开用于检索匹配指定选择器的JWK的方法
     *
     * @return JWKSource
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException, UnrecoverableKeyException {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream(ResourceUtils.getFile(oAuth2Properties.getKeyPairPath())), oAuth2Properties.getKeyPairPassword().toCharArray());
        RSAPublicKey publicKey = (RSAPublicKey) keyStore.getCertificate(oAuth2Properties.getKeyPairAlias()).getPublicKey();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyStore.getKey(oAuth2Properties.getKeyPairAlias(), oAuth2Properties.getKeyPairPassword().toCharArray());
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        return new ImmutableJWKSet<>(new JWKSet(rsaKey));
    }


    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }


    private void addCustomOAuth2GrantAuthenticationProvider(HttpSecurity http) {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        OAuth2AuthorizationService authorizationService = http.getSharedObject(OAuth2AuthorizationService.class);
        OAuth2TokenGenerator tokenGenerator = http.getSharedObject(OAuth2TokenGenerator.class);
        PasswordAuthenticationProvider passwordAuthenticationProvider = new PasswordAuthenticationProvider(
                authorizationService, tokenGenerator, authenticationManager);

        // 处理 UsernamePasswordAuthenticationToken
        http.authenticationProvider(new DaoAuthenticationProvider());
        // 处理 OAuth2ResourceOwnerSmsAuthenticationToken
        http.authenticationProvider(passwordAuthenticationProvider);
    }
}
