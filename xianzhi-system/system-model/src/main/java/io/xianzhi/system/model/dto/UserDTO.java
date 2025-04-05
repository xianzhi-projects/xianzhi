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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * 用户信息入参
 *
 * @author Max
 * @since 1.0.0
 */
@Data
public class UserDTO implements Serializable {

    /**
     * 用户ID
     */
    @Length(max = 20, message = "用户ID长度不能超过20个字符", groups = UpdateGroup.class)
    @NotBlank(message = "用户ID不能为空", groups = UpdateGroup.class)
    private String id;
    /**
     * 用户名
     */
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]{2,19}$", message = "用户名只能包含字母和数字，且以字母开头，长度为3-20个字符", groups = CreateGroup.class)
    @NotBlank(message = "用户名不能为空", groups = CreateGroup.class)
    private String username;
    /**
     * 密码
     */
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[a-zA-Z\\d!@#$%^&*]{6,20}$", message = "密码必须包含至少一个小写字母、一个大写字母、一个数字和一个特殊字符，长度为6-20个字符", groups = CreateGroup.class)
    @NotBlank(message = "密码不能为空", groups = CreateGroup.class)
    private String password;
    /**
     * 昵称
     */
    @Pattern(regexp = "^[^\\s]{3,12}$", message = "昵称只能包含非空格字符，长度为3-12个字符", groups = CreateGroup.class)
    @NotBlank(message = "昵称不能为空", groups = CreateGroup.class)
    private String nickName;
    /**
     * 真实姓名
     */
    @Pattern(regexp = "^([\\u4e00-\\u9fa5]{32}|[a-zA-Z ]{32})$", message = "真实姓名只能包含中文或英文字符，长度为1-32个字符", groups = CreateGroup.class)
    @NotBlank(message = "真实姓名不能为空", groups = CreateGroup.class)
    private String realName;
    /**
     * 部门ID
     */
    @Length(max = 20, message = "部门ID长度不能超过20个字符", groups = {CreateGroup.class, UpdateGroup.class})
    @NotBlank(message = "部门ID不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    private String departmentId;
    /**
     * 领导ID
     */
    private String leaderId;
    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确", groups = {CreateGroup.class, UpdateGroup.class})
    @NotBlank(message = "邮箱不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    private String email;
    /**
     * 手机号码
     */
    @Pattern(regexp = "^1[3-9][0-9]{9}$", message = "手机号码格式不正确", groups = {CreateGroup.class, UpdateGroup.class})
    @NotBlank(message = "手机号码不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    private String phone;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别
     */
    private String gender;
    /**
     * 备注
     */
    @Length(max = 255, message = "备注长度不能超过255个字符", groups = {CreateGroup.class, UpdateGroup.class})
    private String remark;


}
