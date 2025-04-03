

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

package io.xianzhi.common.oauth2.authorization.providers;

import com.fasterxml.jackson.databind.node.TextNode;
import io.xianzhi.common.oauth2.authorization.enums.GrantTypeEnum;
import io.xianzhi.common.oauth2.authorization.token.PasswordAuthenticationToken;
import io.xianzhi.common.oauth2.exception.OAuth2Exception;
import io.xianzhi.common.security.code.SecurityCode;
import io.xianzhi.core.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;

import java.util.List;
import java.util.Map;

/**
 * 密码认证提供者
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
public class PasswordAuthenticationProvider extends AbstractAuthenticationProvider {


    public PasswordAuthenticationProvider(OAuth2AuthorizationService authorizationService, OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator, AuthenticationManager authenticationManager) {
        super(authorizationService, tokenGenerator, authenticationManager);
    }

    /**
     * 检查客户端授权类型是否合法
     *
     * @param grantTypes 当前客户端授权类型
     */
    @Override
    public void checkedGrantType(List<String> grantTypes) {
        if (!grantTypes.contains(GrantTypeEnum.PASSWORD.getCode())) {
            log.error("认证失败,当前客户端不支持验证码授权类型，当前支持的授权类型:{}", JSONUtils.toJSONString(grantTypes));
            throw new OAuth2Exception(SecurityCode.GRANT_TYPE_NOT_SUPPORT);
        }
    }

    /**
     * 构建认证token
     *
     * @param reqParameters 请求参数
     * @return 认证token
     */
    @Override
    public AbstractAuthenticationToken buildToken(Map<String, Object> reqParameters) {
        TextNode username = (TextNode) reqParameters.get(OAuth2ParameterNames.USERNAME);
        TextNode password = (TextNode) reqParameters.get(OAuth2ParameterNames.PASSWORD);
        return new UsernamePasswordAuthenticationToken(username.asText(), password.asText());
    }

    /**
     * Returns <code>true</code> if this <Code>AuthenticationProvider</code> supports the
     * indicated <Code>Authentication</code> object.
     * <p>
     * Returning <code>true</code> does not guarantee an
     * <code>AuthenticationProvider</code> will be able to authenticate the presented
     * instance of the <code>Authentication</code> class. It simply indicates it can
     * support closer evaluation of it. An <code>AuthenticationProvider</code> can still
     * return <code>null</code> from the  method to
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
    public boolean supports(Class<?> authentication) {
        return PasswordAuthenticationToken.class.isAssignableFrom(authentication);

    }
}
