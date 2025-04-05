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

package io.xianzhi.system.model.code;

import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.result.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 部门相关状态码
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum DepartmentCode implements Result {
    /**
     * 部门名称已经存在
     */
    DEPARTMENT_NAME_EXISTS(CommonCode.DATA_EXISTS.code(), "sys.department.name.exists"),
    /**
     * 部门不存在
     */
    DEPARTMENT_NOT_EXISTS(CommonCode.DATA_NOT_EXISTS.code(), "sys.department.not.exists"),
    /**
     * 父级部门不存在
     */
    PARENT_DEPARTMENT_NOT_EXISTS(CommonCode.DATA_NOT_EXISTS.code(), "sys.department.parent.not.exists"),

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
