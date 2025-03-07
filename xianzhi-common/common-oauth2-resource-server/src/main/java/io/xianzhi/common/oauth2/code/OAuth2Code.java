package io.xianzhi.common.oauth2.code;

import io.xianzhi.core.result.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * OAuth2相关状态码
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum OAuth2Code implements Result {

    /**
     * scope 为空，或者scope传递了多个
     */
    SCOPE_ERROR("90000001", "security:scope.error"),

    /**
     * 客户认证异常
     */
    CLIENT_ERROR("91000002", "security:client.error"),

    /**
     * 授权类型不支持
     */
    GRANT_TYPE_NOT_SUPPORT("91000003", "security:grant.type.not.support"),
    /**
     * 授权类型不支持
     */
    INVALID_GRANT_TYPE("91000004", "security:grant.type.invalid"),

    /**
     * 客户端无效
     */
    INVALID_SCOPE("91000005", "security:scope.invalid"),

    /**
     * 生成 accessToken错误
     */
    GENERATOR_ACCESS_TOKEN_ERROR("91000006", "security:generator.access.token.error"),

    /**
     * 生成 refreshToken错误
     */
    GENERATOR_REFRESH_TOKEN_ERROR("91000007", "security:generator.refresh.token.error"),
    /**
     * 客户端密钥无效
     */
    INVALID_CLIENT_SECRET("91000008", "security:invalid.client.secret"),
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
