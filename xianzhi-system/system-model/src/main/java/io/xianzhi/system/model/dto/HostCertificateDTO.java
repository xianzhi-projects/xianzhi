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
import io.xianzhi.system.model.enums.CertTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * 主机凭证DTO
 *
 * @author Max
 * @since 1.0.0
 */
@Data
public class HostCertificateDTO implements Serializable {


    /**
     * 主键ID
     */
    @NotBlank(message = "主键ID不能为空", groups = UpdateGroup.class)
    private String id;
    /**
     * 凭证名称
     */
    @NotBlank(message = "证书名称不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    private String certName;
    /**
     * 凭证描述
     */
    @Length(message = "证书描述长度不能超过255", max = 255, groups = {CreateGroup.class, UpdateGroup.class})
    private String certDesc;
    /**
     * 凭证类型
     */
    @NotNull(message = "证书类型不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    private CertTypeEnum certType;
    /**
     * 主机用户名
     */
    @Length(max = 64, message = "主机用户名长度不能超过64")
    @NotBlank(message = "主机用户名不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    private String username;
    /**
     * 主机密码
     */
    @Length(max = 255, message = "主机密码长度不能超过255", groups = {CreateGroup.class, UpdateGroup.class})
    private String password;
    /**
     * 主机私钥
     */
    @Length(max = 2000, message = "主机私钥长度不能超过2000", groups = {CreateGroup.class, UpdateGroup.class})
    private String privateKey;
}
