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

import io.xianzhi.code.bootstrap.service.ProjectService;
import io.xianzhi.code.model.dto.AddMemberDTO;
import io.xianzhi.code.model.dto.ImportProjectDTO;
import io.xianzhi.code.model.dto.ProjectDTO;
import io.xianzhi.code.model.page.ProjectPage;
import io.xianzhi.code.model.vo.ProjectVO;
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
 * 项目接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/c/project")
public class ProjectController {

    /**
     * 项目信息接口
     */
    private final ProjectService projectService;

    /**
     * 新增项目  (幂等)
     *
     * @param projectDTO 项目信息入参
     * @return 项目ID
     */
    @Idempotent
    @PostMapping(value = "/createProject")
    public ResponseResult<String> createProject(@RequestBody @Validated(value = CreateGroup.class) ProjectDTO projectDTO) {
        return ResponseResult.success(projectService.createProject(projectDTO));
    }

    /**
     * 更新项目
     *
     * @param projectDTO 项目信息入参
     * @return 响应信息
     */
    @PostMapping(value = "/updateProject")
    public ResponseResult<Object> updateProject(@RequestBody @Validated(value = UpdateGroup.class) ProjectDTO projectDTO) {
        projectService.updateProject(projectDTO);
        return ResponseResult.success();
    }

    /**
     * 删除项目
     *
     * @param id 项目ID
     * @return 响应信息
     */
    @PostMapping(value = "/deletedProject")
    public ResponseResult<Object> deletedProject(@RequestParam(value = "id") String id) {
        projectService.deletedProject(id);
        return ResponseResult.success();
    }

    /**
     * 分页查询项目列表
     *
     * @param projectPage 分页查询条件
     * @return 项目列表
     */
    @PostMapping(value = "/pageProjectList")
    public ResponseResult<ListResult<ProjectVO>> pageProjectList(@RequestBody ProjectPage projectPage) {
        return ResponseResult.success(projectService.pageProjectList(projectPage));
    }

    /**
     * 导入项目 (幂等)
     *
     * @param importProjectDTO 导入项目入参
     * @return 响应信息
     */
    @Idempotent
    @PostMapping(value = "/importProject")
    public ResponseResult<ProjectVO> importProject(ImportProjectDTO importProjectDTO) {
        projectService.importProject(importProjectDTO);
        return ResponseResult.success();
    }

    /**
     * 添加成员 (幂等)
     *
     * @param projectId 项目ID
     * @param members   成员信息
     * @return 响应信息
     */
    @Idempotent
    @PostMapping(value = "/addMember")
    public ResponseResult<Object> addMember(@RequestParam String projectId, @RequestBody List<AddMemberDTO> members) {
        projectService.addMember(projectId, members);
        return ResponseResult.success();
    }

    /**
     * 移除成员
     *
     * @param projectId 项目ID
     * @param members   成员ID
     * @return 响应信息
     */
    @PostMapping(value = "/removeMember")
    public ResponseResult<Object> removeMember(@RequestParam String projectId, @RequestBody List<String> members) {
        projectService.removeMember(projectId, members);
        return ResponseResult.success();
    }


}
