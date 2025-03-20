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

package io.xianzhi.code.model.dto;

import io.xianzhi.system.model.enums.HostAuthTypeEnum;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.List;

/**
 * agent入参
 *
 * @author Max
 * @since 1.0.0
 */
@Data
public class AgentDTO implements Serializable {
    /**
     * agentID
     */
    @NotBlank(message = "agentID不能为空")
    private String id;
    /**
     * agent名称
     */
    @Length(max = 64, message = "agent名称长度不能超过64")
    @NotBlank(message = "agent名称不能为空")
    private String agentName;
    /**
     * agent描述
     */
    @Length(max = 255, message = "agent描述长度不能超过255")
    private String agentDesc;
    /**
     * agent标签
     */
    @Length(max = 255, message = "agent标签长度不能超过255")
    private String agentTag;

    /**
     * agent详情
     */
    @NotEmpty(message = "agent详情不能为空")
    private List<AgentDetailsDTO> agentDetails;
    /**
     * 主机ip
     */
    @NotBlank(message = "主机ip不能为空")
    private String hostIp;
    /**
     * 主机端口
     */
    @Max(value = 65535, message = "主机端口不能超过65535")
    @Min(value = 1, message = "主机端口不能小于1")
    private Integer hostPort = 22;
    /**
     * 工作目录
     */
    @Length(max = 255, message = "工作目录长度不能超过255")
    @NotBlank(message = "工作目录不能为空")
    private String workDir;
    /**
     * 认证类型,如果是
     * 密码认证: 用户名密码不能为空
     * 私钥认证: 用户名和私钥不能为空
     * 凭证认证: 凭证ID不能为空
     */
    @NotNull(message = "主机认证类型不能为空")
    private HostAuthTypeEnum authType;
    /**
     * 主机用户名
     */
    @Length(max = 64, message = "主机用户名长度不能超过64")
    private String hostUsername;
    /**
     * 主机密码
     */
    @Length(max = 255, message = "主机密码长度不能超过255")
    private String hostPassword;
    /**
     * 主机私钥
     */
    @Length(max = 2000, message = "主机私钥长度不能超过2000")
    private String hostPrivateKey;
    /**
     * 主机凭证ID
     */
    @Length(max = 20, message = "主机凭证ID长度不能超过20")
    private String certificateId;
}
