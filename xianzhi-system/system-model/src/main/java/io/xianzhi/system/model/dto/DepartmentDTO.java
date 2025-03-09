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

package io.xianzhi.system.model.dto;

import io.xianzhi.core.validated.CreateGroup;
import io.xianzhi.core.validated.UpdateGroup;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * 部门入参
 *
 * @author Max
 * @since 1.0.0
 */
@Data
public class DepartmentDTO implements Serializable {

    /**
     * 部门ID
     */
    @Length(max = 20, message = "部门ID长度不能超过32",groups = UpdateGroup.class)
    @NotBlank(message = "部门ID不能为空",groups = UpdateGroup.class)
    private String id;
    /**
     * 部门名称
     */
    @Length(max = 64, message = "部门名称长度不能超过64",groups = {CreateGroup.class, UpdateGroup.class})
    @NotBlank(message = "部门名称不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    private String departmentName;
    /**
     * 部门描述
     */
    @Length(max = 255, message = "部门描述长度不能超过255",groups = {CreateGroup.class, UpdateGroup.class})
    private String departmentDesc;
    /**
     * 部门邮箱地址
     */
    @Length(max = 128, message = "部门邮箱地址长度不能超过128",groups = {CreateGroup.class, UpdateGroup.class})
    private String departmentEmail;
    /**
     * 部门电话
     */
    @Length(max = 32, message = "部门电话长度不能超过32",groups = {CreateGroup.class, UpdateGroup.class})
    private String departmentPhone;
    /**
     * 部门负责人ID
     */
    @Length(max = 20, message = "部门负责人ID长度不能超过32",groups = {CreateGroup.class, UpdateGroup.class})
    @NotBlank(message = "部门负责人ID不能为空")
    private String departmentOwner;
    /**
     * 父级部门ID
     */
    @Length(max = 20, message = "父级部门ID长度不能超过32",groups = {CreateGroup.class, UpdateGroup.class})
    private String parentId;
}
