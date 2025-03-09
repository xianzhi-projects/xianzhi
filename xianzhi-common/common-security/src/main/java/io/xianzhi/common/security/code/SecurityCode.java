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


    LOGIN_SUCCESS("200", "security:login.success"),
    /**
     * 用户名或密码错误
     */
    USER_NAME_PASSWORD_ERROR("90000001", "用户名或密码错误"),
    /**
     * 账号被禁用
     */
    ACCOUNT_DISABLED("90000002", "账号被禁用"),
    /**
     * 账号被锁定
     */
    ACCOUNT_LOCKED("90000003", "账号被锁定"),
    /**
     * 账号凭证过期
     */
    ACCOUNT_CREDENTIALS_EXPIRED("90000004", "账号凭证过期"),
    /**
     * 验证码错误
     */
    CAPTCHA_ERROR("90000005", "验证码错误"),

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
