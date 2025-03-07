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

package io.xianzhi.system.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 部门信息出参
 *
 * @author Max
 * @since 1.0.0
 */
@Data
public class DepartmentVO implements Serializable {


    /**
     * 主键ID
     */
    private String id;
    /**
     * 部门名称
     */
    private String departmentName;
    /**
     * 部门描述
     */
    private String departmentDesc;
    /**
     * 部门负责人ID
     */
    private UserVO departmentOwner;
    /**
     * 部门邮箱地址
     */
    private String departmentEmail;
    /**
     * 部门电话
     */
    private String departmentPhone;
    /**
     * 子集部门
     */
    private List<DepartmentVO> children;
}
