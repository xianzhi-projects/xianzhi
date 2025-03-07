

package io.xianzhi.common.oauth2.authorization.providers;



import io.xianzhi.common.oauth2.authorization.token.AbstractBaseAuthenticationToken;
import io.xianzhi.common.oauth2.authorization.utils.OAuth2Assert;
import io.xianzhi.common.oauth2.code.OAuth2Code;
import io.xianzhi.common.oauth2.exception.OAuth2Exception;
import io.xianzhi.common.security.code.SecurityCode;
import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.exception.BusinessException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContextHolder;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 抽象认证提供者
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Getter
public abstract class AbstractAuthenticationProvider implements AuthenticationProvider {
    /**
     * 认证信息接口
     */
    private final OAuth2AuthorizationService authorizationService;
    /**
     * token生成
     */
    private final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;
    /**
     * 认证管理
     */
    private final AuthenticationManager authenticationManager;


    public AbstractAuthenticationProvider(OAuth2AuthorizationService authorizationService, OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator, AuthenticationManager authenticationManager) {
        Assert.notNull(authorizationService, "authorizationService cannot be null");
        Assert.notNull(tokenGenerator, "tokenGenerator cannot be null");
        this.authenticationManager = authenticationManager;
        this.authorizationService = authorizationService;
        this.tokenGenerator = tokenGenerator;
    }

    private static Set<String> getScopes(AbstractBaseAuthenticationToken token, RegisteredClient registeredClient) {
        Set<String> authorizedScopes;
        // Default to configured scopes
        if (!CollectionUtils.isEmpty(token.getScopes())) {
            for (String requestedScope : token.getScopes()) {
                if (!registeredClient.getScopes().contains(requestedScope)) {
                    throw new OAuth2Exception(OAuth2Code.INVALID_SCOPE);
                }
            }
            authorizedScopes = new LinkedHashSet<>(token.getScopes());
        } else {
            authorizedScopes = new LinkedHashSet<>();
        }
        return authorizedScopes;
    }

    /**
     * 检查客户端授权类型是否合法
     *
     * @param grantTypes 当前客户端授权类型
     */
    public abstract void checkedGrantType(List<String> grantTypes);

    /**
     * 构建认证token
     *
     * @param reqParameters 请求参数
     * @return 认证token
     */
    public abstract AbstractAuthenticationToken buildToken(Map<String, Object> reqParameters);

    /**
     * Performs authentication with the same contract as
     * {@link AuthenticationManager#authenticate(Authentication)}
     * .
     *
     * @param authentication the authentication request object.
     * @return a fully authenticated object including credentials. May return
     * <code>null</code> if the <code>AuthenticationProvider</code> is unable to support
     * authentication of the passed <code>Authentication</code> object. In such a case,
     * the next <code>AuthenticationProvider</code> that supports the presented
     * <code>Authentication</code> class will be tried.
     * @throws AuthenticationException if authentication fails.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AbstractBaseAuthenticationToken token = (AbstractBaseAuthenticationToken) authentication;
        OAuth2ClientAuthenticationToken oAuth2ClientAuthenticationToken = getAuthenticatedClientElseThrowInvalidClient(authentication);
        RegisteredClient registeredClient = oAuth2ClientAuthenticationToken.getRegisteredClient();
        OAuth2Assert.notNull(registeredClient, OAuth2Code.CLIENT_ERROR);
        List<String> grantTypes = registeredClient.getAuthorizationGrantTypes().stream().map(AuthorizationGrantType::getValue).collect(Collectors.toList());
        checkedGrantType(grantTypes);
        Set<String> authorizedScopes = getScopes(token, registeredClient);
        Map<String, Object> reqParameters = token.getAdditionalParameters();
        try {
            AbstractAuthenticationToken abstractAuthenticationToken = buildToken(reqParameters);
            Authentication authenticate = authenticationManager.authenticate(abstractAuthenticationToken);
            DefaultOAuth2TokenContext.Builder tokenContextBuilder =
                    DefaultOAuth2TokenContext.builder()
                            .registeredClient(registeredClient)
                            .principal(authenticate)
                            .authorizationServerContext(AuthorizationServerContextHolder.getContext())
                            .authorizedScopes(authorizedScopes)
                            .authorizationGrantType(token.getAuthorizationGrantType())
                            .authorizationGrant(token);
            OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization
                    .withRegisteredClient(registeredClient)
                    .principalName(authenticate.getName())
                    .authorizationGrantType(token.getAuthorizationGrantType())
                    .authorizedScopes(authorizedScopes);

            // ----- Access token -----
            OAuth2TokenContext tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.ACCESS_TOKEN).build();

            OAuth2Token generateAccessToken = tokenGenerator.generate(tokenContext);
            if (generateAccessToken == null) {
                log.error("accessToken 生成为空");
                throw new OAuth2Exception(OAuth2Code.GENERATOR_ACCESS_TOKEN_ERROR);
            }
            OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
                    generateAccessToken.getTokenValue(), generateAccessToken.getIssuedAt(),
                    generateAccessToken.getExpiresAt(), tokenContext.getAuthorizedScopes());

            if (generateAccessToken instanceof ClaimAccessor) {
                OAuth2Token finalGenerateAccessToken = generateAccessToken;
                authorizationBuilder.id(accessToken.getTokenValue())
                        .token(accessToken,
                                (metadata) -> metadata.put(OAuth2Authorization.Token.CLAIMS_METADATA_NAME,
                                        ((ClaimAccessor) finalGenerateAccessToken).getClaims()))
                        .authorizedScopes(authorizedScopes)
                        .attribute(Principal.class.getName(), authentication);
            } else {
                authorizationBuilder.id(accessToken.getTokenValue()).accessToken(accessToken);
            }


            // ----- Refresh token -----
            OAuth2RefreshToken refreshToken = null;
            if (registeredClient.getAuthorizationGrantTypes().contains(AuthorizationGrantType.REFRESH_TOKEN) &&
                    // Do not issue refresh token to public client
                    !oAuth2ClientAuthenticationToken.getClientAuthenticationMethod().equals(ClientAuthenticationMethod.NONE)) {
                tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.REFRESH_TOKEN).build();
                OAuth2Token generatedRefreshToken = tokenGenerator.generate(tokenContext);
                refreshToken = (OAuth2RefreshToken) generatedRefreshToken;
                authorizationBuilder.refreshToken(refreshToken);
            }

            OAuth2Authorization authorization = authorizationBuilder.build();
            this.authorizationService.save(authorization);
            return new OAuth2AccessTokenAuthenticationToken(registeredClient, abstractAuthenticationToken, accessToken,
                    refreshToken, Objects.requireNonNull(authorization.getAccessToken().getClaims()));
        } catch (Exception exception) {

            if (exception instanceof OAuth2Exception) {
                throw (OAuth2Exception) exception;
            }
            if (exception instanceof BusinessException) {
                throw new OAuth2Exception(((BusinessException) exception).getResult());
            }
            if (exception instanceof BadCredentialsException) {
                throw new OAuth2Exception(SecurityCode.USER_NAME_PASSWORD_ERROR);
            }
            throw new OAuth2Exception(CommonCode.ERROR);
        }
    }

    /**
     * Returns <code>true</code> if this <Code>AuthenticationProvider</code> supports the
     * indicated <Code>Authentication</code> object.
     * <p>
     * Returning <code>true</code> does not guarantee an
     * <code>AuthenticationProvider</code> will be able to authenticate the presented
     * instance of the <code>Authentication</code> class. It simply indicates it can
     * support closer evaluation of it. An <code>AuthenticationProvider</code> can still
     * return <code>null</code> from the {@link #authenticate(Authentication)} method to
     * indicate another <code>AuthenticationProvider</code> should be tried.
     * </p>
     * <p>
     * Selection of an <code>AuthenticationProvider</code> capable of performing
     * authentication is conducted at runtime the <code>ProviderManager</code>.
     * </p>
     *
     * @param authentication
     * @return <code>true</code> if the implementation can more closely evaluate the
     * <code>Authentication</code> class presented
     */
    @Override
    public abstract boolean supports(Class<?> authentication);


    private OAuth2ClientAuthenticationToken getAuthenticatedClientElseThrowInvalidClient(Authentication authentication) {
        OAuth2ClientAuthenticationToken clientPrincipal = null;
        if (OAuth2ClientAuthenticationToken.class.isAssignableFrom(authentication.getPrincipal().getClass())) {
            clientPrincipal = (OAuth2ClientAuthenticationToken) authentication.getPrincipal();
        }
        if (clientPrincipal != null && clientPrincipal.isAuthenticated()) {
            return clientPrincipal;
        }
        throw new OAuth2Exception(OAuth2Code.CLIENT_ERROR);
    }
}
