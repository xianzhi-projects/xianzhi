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

package io.xianzhi.code.bootstrap.dao.dataobj;

import com.baomidou.mybatisplus.annotation.TableName;
import io.xianzhi.common.mybatis.plus.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * agent实体
 *
 * @author Max
 * @since 1.0.0
 */
@Data
@TableName(value = "c_agent")
@EqualsAndHashCode(callSuper = true)
public class AgentDO extends BaseDO {

    /**
     * agent名称
     */
    private String agentName;
    /**
     * agent描述
     */
    private String agentDesc;
    /**
     * agent标签
     */
    private String agentTag;

    /**
     * 主机ip
     */
    private String hostIp;
    /**
     * 主机端口
     */
    private Integer hostPort;
    /**
     * 工作目录
     */
    private String workDir;
    /**
     * 认证类型,如果是
     * 密码认证: 用户名密码不能为空
     * 私钥认证: 用户名和私钥不能为空
     * 凭证认证: 凭证ID不能为空
     */
    private String authType;
    /**
     * 主机用户名
     */
    private String hostUsername;
    /**
     * 主机密码
     */
    private String hostPassword;
    /**
     * 主机私钥
     */
    private String hostPrivateKey;
    /**
     * 主机凭证ID
     */
    private String certificateId;

    /**
     * AGENT状态
     */
    private String agentStatus;
}
