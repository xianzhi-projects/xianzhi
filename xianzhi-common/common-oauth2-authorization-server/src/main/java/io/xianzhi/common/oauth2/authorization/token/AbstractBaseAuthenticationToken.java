

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

package io.xianzhi.common.oauth2.authorization.token;

import io.micrometer.common.lang.Nullable;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.util.Assert;

import java.util.*;

/**
 * 抽象token
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Getter
public abstract class AbstractBaseAuthenticationToken extends AbstractAuthenticationToken {
    /**
     * 授权类型
     */
    private final AuthorizationGrantType authorizationGrantType;
    /**
     * 客户端信息
     */
    private final Authentication clientPrincipal;
    /**
     * 作用域
     */
    private final Set<String> scopes;
    /**
     * 其他扩展参数
     */
    private final Map<String, Object> additionalParameters;

    /**
     * 构造
     *
     * @param authorizationGrantType 授权类型
     * @param clientPrincipal        客户端信息
     * @param scopes                 作用域
     * @param additionalParameters   其他扩展参数
     */
    public AbstractBaseAuthenticationToken(AuthorizationGrantType authorizationGrantType,
                                           Authentication clientPrincipal, @Nullable Set<String> scopes,
                                           @Nullable Map<String, Object> additionalParameters) {
        super(Collections.emptyList());
        Assert.notNull(authorizationGrantType, "authorizationGrantType cannot be null");
        Assert.notNull(clientPrincipal, "clientPrincipal cannot be null");
        this.authorizationGrantType = authorizationGrantType;
        this.clientPrincipal = clientPrincipal;
        this.scopes = Collections.unmodifiableSet(scopes != null ? new HashSet<>(scopes) : Collections.emptySet());
        this.additionalParameters = Collections.unmodifiableMap(
                additionalParameters != null ? new HashMap<>(additionalParameters) : Collections.emptyMap());
    }

    /**
     * 返回客户端认证信息
     *
     * @return 客户端认证信息
     */
    @Override
    public Object getPrincipal() {
        return this.clientPrincipal;
    }

    /**
     * 返回证书，空字符串即可
     *
     * @return 证书
     */
    @Override
    public Object getCredentials() {
        return "";
    }


}