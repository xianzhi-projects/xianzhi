/*
 *  Copyright 2024 XianZhi Group
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
 *
 */

package io.xianzhi.boot.security.code;

import io.xianzhi.core.result.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Security code enumeration.
 * Represents various security-related status codes used in the application.
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum SecurityCode implements Result {

    /**
     * 用户名或者密码错误
     *
     * @since 1.0.0
     */
    USER_NAME_OR_PASSWORD_ERROR("1001", false, "security.username.or.password.error"),

    /**
     * 授权方式为空
     *
     * @since 1.0.0
     */
    GRANT_TYPE_IS_NULL("1002", false, "security.grant.type.is.null"),

    /**
     * 授权方式不支持
     *
     * @since 1.0.0
     */
    GRANT_TYPE_NOT_SUPPORT("1003", false, "security.grant.type.not.support"),

    /**
     * Token无效
     *
     * @since 1.0.0
     */
    TOKEN_INVALID("1004", false, "security.token.invalid"),

    ;

    /**
     * 自定义响应状态码
     */
    private final String code;

    /**
     * 是否操作成功
     */
    private final boolean success;

    /**
     * 自定义提示信息
     */
    private final String message;

    /**
     * 返回自定义状态码
     *
     * @return 自定义状态码
     * @since 1.0.0
     */
    @Override
    public String code() {
        return this.code;
    }

    /**
     * 表示操作是否成功
     *
     * @return {@code true} 表示成功, {@code false} 操作失败
     * @since 1.0.0
     */
    @Override
    public boolean success() {
        return this.success;
    }

    /**
     * 返回自定操作提示信息
     *
     * @return 自定义提示信息
     * @since 1.0.0
     */
    @Override
    public String message() {
        return this.message;
    }
}
