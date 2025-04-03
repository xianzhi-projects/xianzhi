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
 * Authorization Server Configuration
 * This class configures an OAuth 2.0 Authorization Server using Spring Security’s OAuth2
 * infrastructure. It defines the security filter chain, JWK (JSON Web Key) source for token
 * signing, and custom authentication providers to support various grant types. The configuration
 * integrates custom token and client authentication logic, leverages properties for key management,
 * and uses SLF4J for logging. It is annotated as a Spring @Configuration class and employs
 * constructor-based dependency injection.
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfig {

    /**
     * OAuth2 Token Endpoint Configurer Customizer
     * This field provides a customizer for configuring the OAuth 2.0 token endpoint, allowing
     * tailored behavior for token issuance (e.g., custom grant types or token formats). It is
     * injected via constructor-based dependency injection.
     */
    private final OAuth2TokenEndpointConfigurerCustomizer oAuth2TokenEndpointConfigurerCustomizer;

    /**
     * OAuth2 Client Authentication Configurer Customizer
     * This field provides a customizer for configuring client authentication, enabling customization
     * of how clients are authenticated during OAuth 2.0 flows (e.g., client credentials validation).
     * It is injected via constructor-based dependency injection.
     */
    private final OAuth2ClientAuthenticationConfigurerCustomizer oAuth2ClientAuthenticationConfigurerCustomizer;

    /**
     * OAuth2 Properties
     * This field holds configuration properties for the OAuth 2.0 authorization server, such as
     * the issuer URL, key pair path, alias, and password for JWK management. It is injected via
     * constructor-based dependency injection and used to externalize security settings.
     */
    private final OAuth2Properties oAuth2Properties;

    /**
     * Authorization Server Security Filter Chain
     * This method configures and returns the security filter chain for the OAuth 2.0 Authorization
     * Server. It applies default OAuth 2.0 security settings, customizes the token endpoint and
     * client authentication, sets the issuer from properties, and enables JWT-based resource server
     * support. The filter chain is assigned the highest precedence to ensure it takes priority over
     * other security configurations. Custom authentication providers are added post-construction.
     *
     * @param http The HttpSecurity object used to build the security configuration.
     * @return A SecurityFilterChain instance representing the configured authorization server security.
     * @throws Exception If an error occurs during security configuration (e.g., invalid settings).
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        // Apply default OAuth 2.0 Authorization Server security settings
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        // Customize the OAuth2AuthorizationServerConfigurer
        OAuth2AuthorizationServerConfigurer configurer = http.getConfigurer(OAuth2AuthorizationServerConfigurer.class);
        configurer.tokenEndpoint(oAuth2TokenEndpointConfigurerCustomizer)
                .clientAuthentication(oAuth2ClientAuthenticationConfigurerCustomizer)
                .authorizationServerSettings(AuthorizationServerSettings.builder()
                        .issuer(oAuth2Properties.getIssuer())
                        .build());

        // Configure resource server with JWT support
        http.oauth2ResourceServer((resourceServer) -> resourceServer
                .jwt(Customizer.withDefaults()));

        // Build the security filter chain
        DefaultSecurityFilterChain securityFilterChain = http.build();

        // Add custom authentication providers
        addCustomOAuth2GrantAuthenticationProvider(http);

        return securityFilterChain;
    }

    /**
     * JWK Source Configuration
     * This method creates and returns a JWKSource for generating and retrieving JSON Web Keys (JWKs)
     * used in JWT signing. It loads an RSA key pair from a Java KeyStore (JKS) file specified in
     * the OAuth2Properties, using asymmetric encryption. The public key is exposed for retrieval,
     * while the private key is used for signing. A unique key ID is assigned to the JWK.
     *
     * @return A JWKSource instance providing access to the RSA key pair within a SecurityContext.
     * @throws KeyStoreException         If the KeyStore type is invalid or unavailable.
     * @throws CertificateException      If the certificate in the KeyStore cannot be loaded.
     * @throws IOException               If the KeyStore file cannot be read.
     * @throws NoSuchAlgorithmException  If the algorithm for key retrieval is not supported.
     * @throws UnrecoverableKeyException If the private key cannot be recovered from the KeyStore.
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() throws KeyStoreException, CertificateException, IOException,
            NoSuchAlgorithmException, UnrecoverableKeyException {
        // Load the KeyStore from the specified file path
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream(ResourceUtils.getFile(oAuth2Properties.getKeyPairPath())),
                oAuth2Properties.getKeyPairPassword().toCharArray());

        // Extract RSA public and private keys
        RSAPublicKey publicKey = (RSAPublicKey) keyStore.getCertificate(oAuth2Properties.getKeyPairAlias()).getPublicKey();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyStore.getKey(oAuth2Properties.getKeyPairAlias(),
                oAuth2Properties.getKeyPairPassword().toCharArray());

        // Build an RSAKey with a random key ID
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();

        // Return an immutable JWK set
        return new ImmutableJWKSet<>(new JWKSet(rsaKey));
    }

    /**
     * JWT Encoder Bean
     * This method creates a JwtEncoder bean using the provided JWKSource. The encoder is responsible
     * for generating JWTs signed with the RSA key pair from the JWKSource, used in OAuth 2.0 token
     * issuance.
     *
     * @param jwkSource The JWKSource providing the RSA key pair for signing JWTs.
     * @return A JwtEncoder instance for encoding JWTs.
     */
    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

    /**
     * Add Custom OAuth2 Grant Authentication Provider
     * This method enhances the HttpSecurity configuration by adding custom authentication providers
     * for OAuth 2.0 grant types. It retrieves shared objects (AuthenticationManager,
     * OAuth2AuthorizationService, and OAuth2TokenGenerator) from the HttpSecurity context and uses
     * them to configure a PasswordAuthenticationProvider for password grant support, alongside a
     * default DaoAuthenticationProvider for standard authentication.
     *
     * @param http The HttpSecurity object to which authentication providers are added.
     */
    private void addCustomOAuth2GrantAuthenticationProvider(HttpSecurity http) {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        OAuth2AuthorizationService authorizationService = http.getSharedObject(OAuth2AuthorizationService.class);
        OAuth2TokenGenerator tokenGenerator = http.getSharedObject(OAuth2TokenGenerator.class);

        // Create and register a custom password authentication provider
        PasswordAuthenticationProvider passwordAuthenticationProvider = new PasswordAuthenticationProvider(
                authorizationService, tokenGenerator, authenticationManager);

        // Add default and custom authentication providers
        http.authenticationProvider(new DaoAuthenticationProvider());
        http.authenticationProvider(passwordAuthenticationProvider);
    }
}
