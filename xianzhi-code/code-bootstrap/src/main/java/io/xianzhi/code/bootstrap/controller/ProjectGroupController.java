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

import io.xianzhi.code.bootstrap.service.ProjectGroupService;
import io.xianzhi.code.model.dto.AddMemberDTO;
import io.xianzhi.code.model.dto.ProjectGroupDTO;
import io.xianzhi.code.model.page.ProjectGroupPage;
import io.xianzhi.code.model.vo.ProjectGroupVO;
import io.xianzhi.common.idempotent.annotations.Idempotent;
import io.xianzhi.core.result.ListResult;
import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.core.validated.CreateGroup;
import io.xianzhi.core.validated.UpdateGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目分组接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/c/projectGroup")
public class ProjectGroupController {
    /**
     * 项目分组接口
     */
    private final ProjectGroupService projectGroupService;

    /**
     * 分页查询项目分组列表
     *
     * @param groupPage 分页查询参数
     * @return 项目分组列表
     */
    @PostMapping(value = "/pageProjectGroupList")
    public ResponseResult<ListResult<ProjectGroupVO>> pageProjectGroupList(@RequestBody ProjectGroupPage groupPage) {
        return ResponseResult.success(projectGroupService.pageProjectGroupList(groupPage));
    }

    /**
     * 创建项目分组  (幂等)
     *
     * @param projectGroupDTO 项目分组信息入参
     * @return 项目分组ID
     */
    @Idempotent
    @PostMapping(value = "/createProjectGroup")
    public ResponseResult<String> createProjectGroup(@RequestBody @Validated(value = CreateGroup.class) ProjectGroupDTO projectGroupDTO) {
        return ResponseResult.success(projectGroupService.createProjectGroup(projectGroupDTO));
    }

    /**
     * 更新项目分组
     *
     * @param projectGroupDTO 项目分组信息入参
     * @return 响应信息
     */
    @PostMapping(value = "/updateProjectGroup")
    public ResponseResult<Object> updateProjectGroup(@RequestBody @Validated(value = UpdateGroup.class) ProjectGroupDTO projectGroupDTO) {
        projectGroupService.updateProjectGroup(projectGroupDTO);
        return ResponseResult.success();
    }

    /**
     * 删除项目分组
     *
     * @param id 项目分组ID
     * @return 响应信息
     */
    @PostMapping(value = "/deletedProjectGroup")
    public ResponseResult<Object> deletedProjectGroup(@RequestParam(value = "id") String id) {
        projectGroupService.deletedProjectGroup(id);
        return ResponseResult.success();
    }

    /**
     * 添加成员 (幂等)
     *
     * @param groupId 项目分组ID
     * @param members 成员列表
     * @return 响应信息
     */
    @Idempotent
    @PostMapping(value = "/addMember")
    public ResponseResult<Object> addMember(@RequestParam(value = "groupId") String groupId, @RequestBody List<AddMemberDTO> members) {
        projectGroupService.addMember(groupId, members);
        return ResponseResult.success();
    }

    /**
     * 移除成员
     *
     * @param groupId 项目分组ID
     * @param members 成员列表
     * @return 响应信息
     */
    @PostMapping(value = "/removeMember")
    public ResponseResult<Object> removeMember(@RequestParam(value = "groupId") String groupId, @RequestBody List<String> members) {
        projectGroupService.removeMember(groupId, members);
        return ResponseResult.success();
    }
}
