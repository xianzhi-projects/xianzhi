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

import io.xianzhi.core.content.ContextHolder;

/**
 * 用户信息上下文
 *
 * @author Max
 * @since 1.0.0
 */
public class UserContextHolder extends ContextHolder {

    /**
     * 管理员标识
     */
    public static final String ADMIN_FLAG = "admin";
    /**
     * 系统用户
     */
    public static final String SYSTEM_USER = "system";

    /**
     * 获取当前用户ID
     *
     * @return 当前用户ID
     */
    public static String getCurrentUserId() {
        return getContextOrThrow().getUniqueKey();
    }


    public static String getCurrentUserName() {
        XianZhiOAuth2UserDetails userDetails = (XianZhiOAuth2UserDetails) getContextOrThrow();
        return userDetails.getUsername();
    }

    /**
     * 是否是否超级管理员
     *
     * @return 是否超级管理员
     */
    public static boolean admin() {
        return getCurrentUserOrThrow().getUsername().equals(ADMIN_FLAG);
    }


    /**
     * 获取当前用户
     *
     * @return 当前用户
     */
    public static XianZhiOAuth2UserDetails getCurrentUserOrThrow() {
        return (XianZhiOAuth2UserDetails) getContextOrThrow();
    }

    /**
     * 设置系统用户，用于定时任务，或者其他无法获取用户信息场景
     */
    public static void setAnonymousUser() {
        set(() -> SYSTEM_USER);
    }
}
