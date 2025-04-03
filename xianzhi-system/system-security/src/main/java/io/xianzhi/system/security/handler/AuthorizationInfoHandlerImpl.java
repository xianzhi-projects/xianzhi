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

package io.xianzhi.system.security.handler;

import io.xianzhi.common.oauth2.OAuth2UserDetails;
import io.xianzhi.common.oauth2.resource.AuthorizationInfoHandler;
import io.xianzhi.common.redis.RedisHandler;
import io.xianzhi.system.security.constants.AuthorizationInfoConstant;
import io.xianzhi.system.security.context.XianZhiOAuth2UserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

/**
 * 授权信息接口实现
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorizationInfoHandlerImpl implements AuthorizationInfoHandler {

    /**
     * 缓存处理器
     */
    private final RedisHandler redisHandler;

    /**
     * 加载授权信息
     *
     * @param jwt jwt
     * @return 授权信息
     */
    @Override
    public Optional<OAuth2UserDetails> loadAuthorizationInfo(Jwt jwt) {
        String id = jwt.getClaim("id");
        XianZhiOAuth2UserDetails userDetails = redisHandler.vGet(String.format(AuthorizationInfoConstant.AUTH_USER_INFO__ID, id), XianZhiOAuth2UserDetails.class);
        userDetails.setAuthorities(Collections.singleton(new SimpleGrantedAuthority("**")));
        return Optional.of(userDetails);
    }
}
