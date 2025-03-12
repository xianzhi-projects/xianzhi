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

package io.xianzhi.code.bootstrap.controller;

import io.xianzhi.code.bootstrap.service.AgentGroupService;
import io.xianzhi.code.model.dto.AgentGroupDTO;
import io.xianzhi.code.model.page.AgentGroupPage;
import io.xianzhi.code.model.vo.AgentGroupVO;
import io.xianzhi.core.result.ListResult;
import io.xianzhi.core.result.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * agent分组接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/c/agent_group")
public class AgentGroupController {

    /**
     * agent分组持久层
     */
    private final AgentGroupService agentGroupService;

    /**
     * 分页查询agent分组列表
     *
     * @param agentGroupPage 分页查询参数
     * @return agent分组列表
     */
    @PreAuthorize("@xz.hasPermission('code:agentGroup:list')")
    @PostMapping(value = "/pageAgentGroupList")
    public ResponseResult<ListResult<AgentGroupVO>> pageAgentGroupList(AgentGroupPage agentGroupPage) {
        return ResponseResult.success();
    }

    /**
     * 创建agent分组
     *
     * @param agentGroupDTO agent分组参数
     * @return agent分组id
     */
    @PreAuthorize("@xz.hasPermission('code:agentGroup:create')")
    @PostMapping(value = "/createAgentGroup")
    public ResponseResult<String> createAgentGroup(AgentGroupDTO agentGroupDTO) {
        return ResponseResult.success();
    }

    /**
     * 更新agent分组
     *
     * @param agentGroupDTO agent分组参数
     * @return 响应信息
     */
    public ResponseResult<Object> updateAgentGroup(AgentGroupDTO agentGroupDTO) {
        return ResponseResult.success();
    }

    /**
     * 删除agent分组
     *
     * @param id agent分组id
     * @return 响应信息
     */
    public ResponseResult<Object> deleteAgentGroup(String id) {
        return ResponseResult.success();
    }
}
