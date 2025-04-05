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
 * 字典类型枚举
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum DictCodeEnum {
    /**
     * 性别
     */
    GENDER("GENDER", "性别"),
    /**
     * 是否
     */
    YES_NO("YES_NO", "是否"),


    ;

    /**
     * 字典编码
     */
    private final String code;
    /**
     * 字典描述
     */
    private final String desc;

    /**
     * 根据字典编码获取字典类型
     *
     * @param code 字典编码
     * @return 字典类型
     */
    public static DictCodeEnum getByCode(String code) {
        for (DictCodeEnum dictCodeEnum : DictCodeEnum.values()) {
            if (dictCodeEnum.getCode().equals(code)) {
                return dictCodeEnum;
            }
        }
        return null;
    }
}
