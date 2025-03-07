package io.xianzhi.common.oauth2.resource;

import io.xianzhi.common.oauth2.OAuth2UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;

/**
 * 授权信息接口
 *
 * @author Max
 * @since 1.0.0
 */
public interface AuthorizationInfoHandler {

    /**
     * 加载授权信息
     *
     * @param jwt jwt
     * @return 授权信息
     */
    Optional<OAuth2UserDetails> loadAuthorizationInfo(Jwt jwt);
}
