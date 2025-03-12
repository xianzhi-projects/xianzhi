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

import io.xianzhi.code.bootstrap.dao.mapper.AgentGroupMapper;
import io.xianzhi.code.bootstrap.dao.mapper.AgentMapper;
import io.xianzhi.code.bootstrap.service.AgentService;
import io.xianzhi.code.model.dto.AgentDTO;
import io.xianzhi.code.model.page.AgentPage;
import io.xianzhi.code.model.vo.AgentVO;
import io.xianzhi.core.result.ListResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * agent实现
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {
    /**
     * agent持久层
     */
    private final AgentMapper agentMapper;

    /**
     * agent 分组持久层
     */
    private final AgentGroupMapper agentGroupMapper;

    /**
     * 分页查询agent列表
     *
     * @param agentPage 分页查询参数
     * @return agent列表
     */
    @Override
    public ListResult<AgentVO> pageAgentList(AgentPage agentPage) {
        return null;
    }

    /**
     * 薪资agent
     *
     * @param agentDTO agent入参
     * @return 响应信息
     */
    @Override
    public String createAgent(AgentDTO agentDTO) {
        return "";
    }

    /**
     * 更新agent
     *
     * @param agentDTO agent入参
     */
    @Override
    public void updateAgent(AgentDTO agentDTO) {

    }

    /**
     * 删除agent
     *
     * @param id agentID
     */
    @Override
    public void deletedAgent(String id) {

    }
}
