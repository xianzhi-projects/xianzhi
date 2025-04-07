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

import io.xianzhi.code.bootstrap.service.AgentService;
import io.xianzhi.code.model.dto.AgentDTO;
import io.xianzhi.code.model.page.AgentPage;
import io.xianzhi.code.model.vo.AgentVO;
import io.xianzhi.common.idempotent.annotations.Idempotent;
import io.xianzhi.core.result.ListResult;
import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.core.validated.CreateGroup;
import io.xianzhi.core.validated.UpdateGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * agent接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/c/agent")
public class AgentController {

    /**
     * agent服务
     */
    private final AgentService agentService;

    /**
     * 分页查询agent列表
     *
     * @param agentPage 分页查询参数
     * @return agent列表
     */
    @PreAuthorize("@xz.hasPermission('code:agent:list')")
    @PostMapping(value = "/pageAgentList")
    public ResponseResult<ListResult<AgentVO>> pageAgentList(@RequestBody AgentPage agentPage) {
        return ResponseResult.success(agentService.pageAgentList(agentPage));
    }

    /**
     * 新增agent  (幂等)
     *
     * @param agentDTO agent入参
     * @return 响应信息
     */
    @Idempotent
    @PreAuthorize("@xz.hasPermission('code:agent:create')")
    @PostMapping(value = "/createAgent")
    public ResponseResult<String> createAgent(@RequestBody @Validated(value = CreateGroup.class) AgentDTO agentDTO) {
        return ResponseResult.success(agentService.createAgent(agentDTO));
    }

    /**
     * 更新agent
     *
     * @param agentDTO agent入参
     * @return 响应信息
     */
    @PreAuthorize("@xz.hasPermission('code:agent:update')")
    @PostMapping(value = "/updateAgent")
    public ResponseResult<String> updateAgent(@RequestBody @Validated(value = UpdateGroup.class) AgentDTO agentDTO) {
        agentService.updateAgent(agentDTO);
        return ResponseResult.success();
    }

    /**
     * 删除agent
     *
     * @param id agentID
     * @return 响应信息
     */
    @PreAuthorize("@xz.hasPermission('code:agent:deleted')")
    @PostMapping(value = "/deletedAgent")
    public ResponseResult<Object> deletedAgent(@RequestParam(value = "id") String id) {
        agentService.deletedAgent(id);
        return ResponseResult.success();
    }

    /**
     * 安装agent
     *
     * @param id agentId
     * @return 响应信息
     */
    @PreAuthorize("@xz.hasPermission('code:agent:install')")
    @PostMapping(value = "/installAgent")
    public ResponseResult<Object> installAgent(@RequestParam(value = "id") String id) {
        agentService.installAgent(id);
        return ResponseResult.success();
    }
}

