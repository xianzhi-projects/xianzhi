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

package io.xianzhi.common.security.code;

import io.xianzhi.core.result.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 安全相关状态码
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum SecurityCode implements Result {


    /**
     * 用户名或密码错误
     */
    USER_NAME_PASSWORD_ERROR("XZ-02-00000010", "用户名或密码错误"),
    /**
     * 账号被禁用
     */
    ACCOUNT_DISABLED("XZ-02-00000020", "账号被禁用"),
    /**
     * 账号被锁定
     */
    ACCOUNT_LOCKED("XZ-02-00000030", "账号被锁定"),
    /**
     * 账号凭证过期
     */
    ACCOUNT_CREDENTIALS_EXPIRED("XZ-02-00000040", "账号凭证过期"),
    /**
     * 验证码错误
     */
    CAPTCHA_ERROR("XZ-02-00000050", "验证码错误"),


    /**
     * scope 为空，或者scope传递了多个
     */
    SCOPE_ERROR("XZ-02-00000060", "security:scope.error"),

    /**
     * 客户认证异常
     */
    CLIENT_ERROR("XZ-02-00000070", "客户端认证异常"),

    /**
     * 授权类型不支持
     */
    GRANT_TYPE_NOT_SUPPORT("XZ-02-00000071", "security:grant.type.not.support"),
    /**
     * 授权类型不支持
     */
    INVALID_GRANT_TYPE("XZ-02-00000072", "授权类型不支持"),

    /**
     * 客户端无效
     */
    INVALID_SCOPE("XZ-02-00000073", "security:scope.invalid"),

    /**
     * 生成 accessToken错误
     */
    GENERATOR_ACCESS_TOKEN_ERROR("XZ-02-00000080", "security:generator.access.token.error"),

    /**
     * 生成 refreshToken错误
     */
    GENERATOR_REFRESH_TOKEN_ERROR("XZ-02-00000081", "security:generator.refresh.token.error"),
    /**
     * 客户端密钥无效
     */
    INVALID_CLIENT_SECRET("XZ-02-00000090", "security:invalid.client.secret"),

    ;
    /**
     * 自定义状态码
     */
    private final String code;
    /**
     * 自定义提示信息
     */
    private final String message;

    /**
     * 获取自定义状态码
     *
     * @return 自定义状体码
     */
    @Override
    public String code() {
        return this.code;
    }

    /**
     * 获取自定义提示信息
     *
     * @return 自定义提示信息
     */
    @Override
    public String message() {
        return this.message;
    }
}
