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

import io.xianzhi.common.redis.RedisHandler;
import io.xianzhi.system.security.constants.AuthorizationInfoConstant;
import io.xianzhi.system.security.context.XianZhiOAuth2UserDetails;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 抽象的授权信息信息接口
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractXianZhiUserDetailsService implements XianZhiUserDetailsService {


    @Resource
    private RedisHandler redisHandler;


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
        XianZhiOAuth2UserDetails userDetails = doLoadUserByUsername(username);
        redisHandler.vSet(String.format(AuthorizationInfoConstant.AUTH_USER_INFO__ID, userDetails.getId()), userDetails);
        return userDetails;
    }


    protected abstract XianZhiOAuth2UserDetails doLoadUserByUsername(String username) throws UsernameNotFoundException;
}
