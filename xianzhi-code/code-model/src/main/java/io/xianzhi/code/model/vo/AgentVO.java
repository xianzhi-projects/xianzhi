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

package io.xianzhi.code.model.vo;

import io.xianzhi.system.model.vo.UserVO;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * agent出参
 *
 * @author Max
 * @since 1.0.0
 */
@Data
public class AgentVO implements Serializable {

    /**
     * agentId
     */
    private String id;
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
     * agent详情
     */
    private List<AgentDetailsVO> agentDetails;
    /**
     * 主机IP
     */
    private String hostIp;
    /**
     * 主机名称
     */
    private String agentStatus;
    /**
     * 新增时间
     */
    private LocalDateTime createAt;
    /**
     * 修改时间
     */
    private LocalDateTime updateAt;

    /**
     * 创建人
     */
    private UserVO createBy;
    /**
     * 修改人
     */
    private UserVO updateBy;

}
