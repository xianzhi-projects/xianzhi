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

package io.xianzhi.code.bootstrap.service;

import io.xianzhi.code.model.dto.AgentDTO;
import io.xianzhi.code.model.page.AgentPage;
import io.xianzhi.code.model.vo.AgentVO;
import io.xianzhi.core.result.ListResult;

/**
 * agent接口
 *
 * @author Max
 * @since 1.0.0
 */
public interface AgentService {
    /**
     * 分页查询agent列表
     *
     * @param agentPage 分页查询参数
     * @return agent列表
     */
    ListResult<AgentVO> pageAgentList(AgentPage agentPage);

    /**
     * 薪资agent
     *
     * @param agentDTO agent入参
     * @return 响应信息
     */
    String createAgent(AgentDTO agentDTO);

    /**
     * 更新agent
     *
     * @param agentDTO agent入参
     */
    void updateAgent(AgentDTO agentDTO);

    /**
     * 删除agent
     *
     * @param id agentID
     */
    void deletedAgent(String id);
}
