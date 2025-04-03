
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

package io.xianzhi.common.oauth2.authorization.converters;

import io.xianzhi.common.oauth2.authorization.token.AbstractBaseAuthenticationToken;
import io.xianzhi.common.oauth2.authorization.utils.OAuth2EndpointUtils;
import io.xianzhi.common.oauth2.exception.OAuth2Exception;
import io.xianzhi.common.security.code.SecurityCode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 认证转换器
 *
 * @author Max
 * @since 1.0.0
 */
public abstract class AbstractAuthenticationConverter implements AuthenticationConverter {


    /**
     * 是否支持此convert
     *
     * @param grantType 授权类型
     * @return 是否支持
     */
    public abstract boolean support(String grantType);


    /**
     * 检查认证参数是否合法
     *
     * @param request              request
     * @param additionalParameters 扩展参数
     */
    public abstract void checkedParams(HttpServletRequest request, Map<String, Object> additionalParameters);


    /**
     * 构建token
     *
     * @param clientPrincipal      客户端认证信息
     * @param requestedScopes      scope
     * @param additionalParameters 扩展信息
     * @return 认证token
     */
    public abstract AbstractBaseAuthenticationToken buildToken(Authentication clientPrincipal, Set<String> requestedScopes,
                                                               Map<String, Object> additionalParameters);

    /**
     * 认证转换器
     *
     * @param request 认证请求
     * @return 认证信息
     */
    @Override
    public Authentication convert(HttpServletRequest request) {
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        // 交给子类判断是否支持授权类型
        if (!support(grantType)) {
            return null;
        }

        // 获取客户端认证信息
        Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();
        if (clientPrincipal == null) {
            throw new OAuth2Exception(SecurityCode.CLIENT_ERROR);
        }


        MultiValueMap<String, String> parameters = OAuth2EndpointUtils.getParameters(request);
        // 检查scope
        String scope = parameters.getFirst(OAuth2ParameterNames.SCOPE);
        Set<String> scopes = null;
        if (StringUtils.hasText(scope) &&
                parameters.get(OAuth2ParameterNames.SCOPE).size() != 1) {
            throw new OAuth2Exception(SecurityCode.SCOPE_ERROR);
        }
        if (StringUtils.hasText(scope)) {
            scopes = new HashSet<>(
                    Arrays.asList(StringUtils.delimitedListToStringArray(scope, " ")));
        }
        // 扩展信息
        Map<String, Object> additionalParameters = parameters.entrySet()
                .stream()
                .filter(e -> !e.getKey().equals(OAuth2ParameterNames.GRANT_TYPE)
                        && !e.getKey().equals(OAuth2ParameterNames.SCOPE))
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().get(0)));
        checkedParams(request, additionalParameters);

        return buildToken(clientPrincipal, scopes, additionalParameters);
    }
}

