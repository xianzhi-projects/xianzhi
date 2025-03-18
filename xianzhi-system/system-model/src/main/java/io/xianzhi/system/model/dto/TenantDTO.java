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
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * 租户入参
 *
 * @author Max
 * @since 1.0.0
 */
@Data
public class TenantDTO implements Serializable {

    /**
     * 主键ID
     */
    @Length(max = 20, message = "租户ID长度不能超过20")
    @NotBlank(message = "租户ID不能为空", groups = UpdateGroup.class)
    private String id;

    /**
     * 租户名称
     */
    @Length(max = 64, message = "租户名称长度不能超过63")
    @NotBlank(message = "租户名称不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    private String tenantName;
    /**
     * 租户编码
     */
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_-]{2,63}$", message = "租户编码格式不正确")
    @NotBlank(message = "租户编码不能为空", groups = {CreateGroup.class})
    private String tenantCode;
    /**
     * 租户描述
     */
    @Length(max = 255, message = "租户描述长度不能超过255")
    private String tenantDesc;

    /**
     * 租户logoId
     */
    @Length(max = 20, message = "租户logoId长度不能超过20")
    private String tenantLogoId;
    /**
     * 租户负责人
     */
    @Length(max = 20, message = "租户负责人长度不能超过20")
    @NotBlank(message = "租户负责人不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    private String tenantOwner;
}
