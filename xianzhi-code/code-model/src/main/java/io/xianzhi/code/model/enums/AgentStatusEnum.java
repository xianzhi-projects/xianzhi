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
 * agent状态枚举
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AgentStatusEnum {
    /**
     * 在线
     */
    ONLINE("online", "在线"),
    /**
     * 离线
     */
    OFFLINE("offline", "离线"),
    /**
     * 待安装
     */
    WAIT_FOR_INSTALL("wait_for_install", "待安装"),
    /**
     * 安装中
     */
    INSTALLING("installing", "安装中"),
    /**
     * 安装失败
     */
    INSTALL_FAILED("install_failed", "安装失败"),

    ;


    private final String code;
    private final String desc;

    public static AgentStatusEnum getAgentStatusByCode(String code) {
        for (AgentStatusEnum statusEnum : AgentStatusEnum.values()) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum;
            }
        }
        return null;
    }
}
