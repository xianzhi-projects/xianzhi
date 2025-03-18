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

package io.xianzhi.code.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 主机认证类型
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum HostAuthTypeEnum {

    /**
     * 密码认证
     */
    PASSWORD("PASSWORD", "密码认证"),
    /**
     * 密钥认证
     */
    PRIVATE_KEY("PRIVATE_KEY", "密钥认证"),
    /**
     * 凭证
     */
    CERTIFICATE("CERTIFICATE", "凭证认证"),
    ;

    /**
     * 认证类型
     */
    private final String code;
    /**
     * 认证类型描述
     */
    private final String desc;
}
