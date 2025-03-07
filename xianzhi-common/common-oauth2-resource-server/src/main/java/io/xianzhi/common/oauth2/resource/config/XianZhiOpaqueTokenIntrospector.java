package io.xianzhi.common.oauth2.resource.config;


import io.xianzhi.common.oauth2.OAuth2UserDetails;
import io.xianzhi.common.oauth2.exception.OAuth2Exception;
import io.xianzhi.common.oauth2.resource.AuthorizationInfoHandler;
import io.xianzhi.common.security.properties.SecurityProperties;
import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.content.ContextHolder;
import io.xianzhi.core.exception.BusinessException;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Token拦截
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
public class XianZhiOpaqueTokenIntrospector implements OpaqueTokenIntrospector {

    /**
     * 安全配置
     */
    private final SecurityProperties securityProperties;
    /**
     * 资源服务器接口
     */
    @Resource
    private AuthorizationInfoHandler authorizationInfoHandler;
    /**
     * jwt解码器
     */
    @Resource
    private JwtDecoder jwtDecoder;

    /**
     * Introspect and verify the given token, returning its attributes.
     * <p>
     * Returning a {@link Map} is indicative that the token is valid.
     *
     * @param token the token to introspect
     * @return the token's attributes
     */
    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        if (StringUtils.hasText(token)) {
            try {
                Jwt jwt = jwtDecoder.decode(token);
                // 这里是链接授权服务的redis，获取用户信息
                OAuth2UserDetails oAuth2UserDetails = authorizationInfoHandler.loadAuthorizationInfo(jwt).orElseThrow(() -> new OAuth2Exception(CommonCode.UNAUTHORIZED));
                // 设置用户上下文
                ContextHolder.set(oAuth2UserDetails);
                return oAuth2UserDetails;
            } catch (JwtValidationException | OAuth2Exception | BusinessException exception) {
                if (exception instanceof OAuth2Exception || exception instanceof BusinessException) {
                    throw exception;
                }
                log.error("解析Token失败:{}", exception.getMessage(), exception);
                throw new OAuth2Exception(CommonCode.UNAUTHORIZED);
            }
        }
        throw new OAuth2Exception(CommonCode.UNAUTHORIZED);
    }
}

