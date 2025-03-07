

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

package io.xianzhi.system.bootstrap.oauth2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.ClaimAccessor;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithm;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * JWT生成
 *
 * @author Max
 * @since 1.0.0
 * @since
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtGenerator implements OAuth2TokenGenerator<OAuth2Token> {


    /**
     * token增强
     */
    private final OAuth2TokenCustomizer<JwtEncodingContext> oAuth2TokenCustomizer;


    private final JwtEncoder jwtEncoder;


    /**
     * Generate an OAuth 2.0 Token using the attributes contained in the {@link OAuth2TokenContext},
     * or return {@code null} if the {@link OAuth2TokenContext#getTokenType()} is not supported.
     *
     * <p>
     * If the returned {@link OAuth2Token} has a set of claims, it should implement {@link ClaimAccessor}
     * in order for it to be stored with the {@link OAuth2Authorization}.
     *
     * @param context the context containing the OAuth 2.0 Token attributes
     * @return an {@link OAuth2Token} or {@code null} if the {@link OAuth2TokenContext#getTokenType()} is not supported
     */
    @Override
    public OAuth2Token generate(OAuth2TokenContext context) {
        // 客户端信息
        RegisteredClient registeredClient = context.getRegisteredClient();

        TokenSettings tokenSettings = registeredClient.getTokenSettings();
        if (null == context.getTokenType() || (!OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType()) &&
                !OAuth2TokenType.REFRESH_TOKEN.equals(context.getTokenType())) || !tokenSettings.getAccessTokenFormat().equals(OAuth2TokenFormat.REFERENCE)) {
            return null;
        }
        boolean accessToken = OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType());
        // 设置发布机构
        String issuer = null;
        if (context.getAuthorizationServerContext() != null) {
            issuer = context.getAuthorizationServerContext().getIssuer();
        }
        Instant issuedAt = Instant.now();
        Instant expiresAt;
        JwsAlgorithm jwsAlgorithm = SignatureAlgorithm.RS256;
        if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
            expiresAt = issuedAt.plus(tokenSettings.getAccessTokenTimeToLive());
            if (registeredClient.getTokenSettings().getIdTokenSignatureAlgorithm() != null) {
                jwsAlgorithm = registeredClient.getTokenSettings().getIdTokenSignatureAlgorithm();
            }
        } else {
            expiresAt = issuedAt.plus(registeredClient.getTokenSettings().getRefreshTokenTimeToLive());
        }

        // @formatter:off
        JwtClaimsSet.Builder claimsBuilder = JwtClaimsSet.builder();
        if (StringUtils.hasText(issuer)) {
            claimsBuilder.issuer(issuer);
        }
        claimsBuilder
                .subject(context.getPrincipal().getName())
                .audience(Collections.singletonList(registeredClient.getClientId()))
                .issuedAt(issuedAt)
                .expiresAt(expiresAt);
        if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
            claimsBuilder.notBefore(issuedAt);
            if (!CollectionUtils.isEmpty(context.getAuthorizedScopes())) {
                claimsBuilder.claim(OAuth2ParameterNames.SCOPE, context.getAuthorizedScopes());
            }
        }
        // @formatter:on
        JwsHeader.Builder jwsHeaderBuilder = JwsHeader.with(jwsAlgorithm);
        if (this.oAuth2TokenCustomizer != null && OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
            // @formatter:off
            JwtEncodingContext.Builder jwtContextBuilder = JwtEncodingContext.with(jwsHeaderBuilder, claimsBuilder)
                    .registeredClient(context.getRegisteredClient())
                    .principal(context.getPrincipal())
                    .authorizationServerContext(context.getAuthorizationServerContext())
                    .authorizedScopes(context.getAuthorizedScopes())
                    .tokenType(context.getTokenType())
                    .authorizationGrantType(context.getAuthorizationGrantType());
            if (context.getAuthorization() != null) {
                jwtContextBuilder.authorization(context.getAuthorization());
            }
            if (context.getAuthorizationGrant() != null) {
                jwtContextBuilder.authorizationGrant(context.getAuthorizationGrant());
            }
            // @formatter:on
            JwtEncodingContext jwtContext = jwtContextBuilder.build();
            this.oAuth2TokenCustomizer.customize(jwtContext);
        }
        Jwt encode = jwtEncoder.encode(JwtEncoderParameters.from(jwsHeaderBuilder.build(), claimsBuilder.build()));
        if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
            return new OAuth2AccessTokenClaims(OAuth2AccessToken.TokenType.BEARER,
                    encode.getTokenValue(), issuedAt, expiresAt, registeredClient.getScopes(), encode.getClaims());
        }
        return new OAuth2RefreshToken(encode.getTokenValue(), issuedAt, expiresAt);
    }


    private static final class OAuth2AccessTokenClaims extends OAuth2AccessToken implements ClaimAccessor {
        private final Map<String, Object> claims;

        private OAuth2AccessTokenClaims(TokenType tokenType, String tokenValue,
                                        Instant issuedAt, Instant expiresAt, Set<String> scopes, Map<String, Object> claims) {
            super(tokenType, tokenValue, issuedAt, expiresAt, scopes);
            this.claims = claims;
        }

        @Override
        public Map<String, Object> getClaims() {
            return this.claims;
        }

    }

}