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

package io.xianzhi.code.bootstrap.service.impl;

import io.xianzhi.code.bootstrap.service.AgentGroupService;
import io.xianzhi.code.model.dto.AgentGroupDTO;
import io.xianzhi.code.model.page.AgentGroupPage;
import io.xianzhi.code.model.vo.AgentGroupVO;
import io.xianzhi.core.result.ListResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * agent分组接口实现
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AgentGroupServiceImpl implements AgentGroupService {
    /**
     * 分页查询agent分组列表
     *
     * @param agentGroupPage 分页查询参数
     * @return agent分组列表
     */
    @Override
    public ListResult<AgentGroupVO> pageAgentGroupList(AgentGroupPage agentGroupPage) {
        return null;
    }

    /**
     * 创建agent分组  (幂等)
     *
     * @param agentGroupDTO agent分组参数
     * @return agent分组id
     */
    @Override
    public String createAgentGroup(AgentGroupDTO agentGroupDTO) {
        return "";
    }

    /**
     * 更新agent分组
     *
     * @param agentGroupDTO agent分组参数
     */
    @Override
    public void updateAgentGroup(AgentGroupDTO agentGroupDTO) {

    }

    /**
     * 删除agent分组
     *
     * @param id agent分组id
     */
    @Override
    public void deleteAgentGroup(String id) {

    }
}
