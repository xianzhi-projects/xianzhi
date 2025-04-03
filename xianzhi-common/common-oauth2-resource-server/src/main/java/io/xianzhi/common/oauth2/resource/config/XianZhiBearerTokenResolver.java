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

package io.xianzhi.common.oauth2.resource.config;

import io.xianzhi.common.security.properties.SecurityProperties;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

/**
 * token处理
 *
 * @author Max
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class XianZhiBearerTokenResolver implements BearerTokenResolver {

    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    /**
     * 安全配置
     */
    private final SecurityProperties securityProperties;

    /**
     * Resolve any
     * <a href="https://tools.ietf.org/html/rfc6750#section-1.2" target="_blank">Bearer
     * Token</a> value from the request.
     *
     * @param request the request
     * @return the Bearer Token value or {@code null} if none found
     * @throws OAuth2AuthenticationException if the found token is invalid
     */
    @Override
    public String resolve(HttpServletRequest request) {
        if (securityProperties.getPermitAllList().stream()
                .anyMatch(pattern -> PATH_MATCHER.match(pattern, request.getRequestURI()))) {
            return null;
        }
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        return null;
    }
}

