

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

import org.springframework.core.Ordered;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 授权信息接口
 *
 * @author Max
 * @since 1.0.0
 */
public abstract interface XianZhiUserDetailsService extends UserDetailsService, Ordered {

    /**
     * 判断是否支持
     *
     * @param grantType 授权类型
     * @return 是否支持
     */
    boolean support(String grantType);
}
