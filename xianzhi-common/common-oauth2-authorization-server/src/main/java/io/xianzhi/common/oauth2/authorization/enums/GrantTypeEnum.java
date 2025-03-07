
package io.xianzhi.common.oauth2.authorization.enums;

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

    /**
     * 密码模式
     */
    PASSWORD("password", "密码模式"),
    ;

    /**
     * code
     */
    private final String code;

    /**
     * 描述
     */
    private final String desc;
}


