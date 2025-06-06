

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

package io.xianzhi.system.security.context;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.xianzhi.common.oauth2.OAuth2UserDetails;
import io.xianzhi.system.model.enums.UserStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Map;

/**
 * 授权用户信息
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class XianZhiOAuth2UserDetails extends OAuth2UserDetails {


    /**
     * 用户ID
     */
    private String id;
    /**
     * 用户权限
     */
    @JsonIgnore
    Collection<? extends GrantedAuthority> authorities;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户邮箱地址
     */
    private String email;
    /**
     * 用户真实姓名
     */
    private String realName;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户状态 字典值
     */
    private String userStatus;
    /**
     * 用户名
     */
    private String username;

    /**
     * 获取唯一标识
     *
     * @return 唯一标识
     */
    @Override
    @JsonIgnore
    public String getUniqueKey() {
        return this.id;
    }

    /**
     * Returns the username used to authenticate the user. Cannot return
     * <code>null</code>.
     *
     * @return the username (never <code>null</code>)
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
     */
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return userStatus.equals(UserStatusEnum.NORMAL.getCode());
    }

    /**
     * Get the OAuth 2.0 token attributes
     *
     * @return the OAuth 2.0 token attributes
     */
    @Override
    @JsonIgnore
    public Map<String, Object> getAttributes() {
        return Map.of();
    }

    /**
     * Get the {@link Collection} of {@link GrantedAuthority}s associated with this OAuth
     * 2.0 token
     *
     * @return the OAuth 2.0 token authorities
     */
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Returns the name of the authenticated <code>Principal</code>. Never
     * <code>null</code>.
     *
     * @return the name of the authenticated <code>Principal</code>
     */
    @Override
    @JsonIgnore
    public String getName() {
        return this.username;
    }
}
