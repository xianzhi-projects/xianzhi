package io.xianzhi.boot.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 授权类型枚举
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum GrantTypeEnum {


    PASSWORD("password", "密码授权"),


    REFRESH_TOKEN("refresh_token", "刷新令牌授权"),


    ;

    private final String code;

    private final String desc;

}
