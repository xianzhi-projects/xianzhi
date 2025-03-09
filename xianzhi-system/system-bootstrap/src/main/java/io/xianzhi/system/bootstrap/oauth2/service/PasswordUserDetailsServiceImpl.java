

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

package io.xianzhi.system.bootstrap.oauth2.service;


import io.xianzhi.common.oauth2.authorization.enums.GrantTypeEnum;
import io.xianzhi.common.redis.RedisHandler;
import io.xianzhi.system.bootstrap.dao.dataobj.UserDO;
import io.xianzhi.system.bootstrap.dao.mapper.UserMapper;
import io.xianzhi.system.security.context.XianZhiOAuth2UserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 密码方式
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PasswordUserDetailsServiceImpl implements XianZhiUserDetailsService {

    /**
     * Redis处理器
     */
    private final RedisHandler redisHandler;

    /**
     * 用户信息持久层
     */
    private final UserMapper userMapper;


    /**
     * 判断是否支持
     *
     * @param grantType 授权类型
     * @return 是否支持
     */
    @Override
    public boolean support(String grantType) {
        return GrantTypeEnum.PASSWORD.getCode().equalsIgnoreCase(grantType);
    }


    /**
     * Get the order value of this object.
     * <p>Higher values are interpreted as lower priority. As a consequence,
     * the object with the lowest value has the highest priority (somewhat
     * analogous to Servlet {@code load-on-startup} values).
     * <p>Same order values will result in arbitrary sort positions for the
     * affected objects.
     *
     * @return the order value
     * @see #HIGHEST_PRECEDENCE
     * @see #LOWEST_PRECEDENCE
     */
    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!StringUtils.hasText(username)) {
            throw new UsernameNotFoundException("用户名不能为空");
        }
        UserDO user = userMapper.selectUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("用户名或者密码错误"));
        XianZhiOAuth2UserDetails userDetails = new XianZhiOAuth2UserDetails();
        userDetails.setUsername(username);
        userDetails.setPassword(user.getPassword());
        userDetails.setStatusCode(user.getUserStatus());
        userDetails.setId(user.getId());
        return userDetails;

    }
}
