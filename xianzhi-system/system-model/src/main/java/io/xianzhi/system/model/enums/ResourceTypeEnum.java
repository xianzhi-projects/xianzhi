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

package io.xianzhi.system.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单类型枚举
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum ResourceTypeEnum {
    /**
     * 菜单
     */
    MENU("MENU", "菜单"),
    /**
     * 按钮
     */
    BUTTON("BUTTON", "按钮"),

    /**
     * 链接
     */
    LINK("LINK", "链接"),
    /**
     * 目录
     */
    CATALOG("CATALOG", "目录"),


    ;


    public static ResourceTypeEnum getByCode(String code) {
        for (ResourceTypeEnum value : ResourceTypeEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }


    private final String code;

    private final String desc;
}
