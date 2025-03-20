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
import io.xianzhi.code.model.dto.ProjectGroupDTO;
import io.xianzhi.code.model.page.ProjectGroupPage;
import io.xianzhi.code.model.vo.ProjectGroupVO;
import io.xianzhi.common.idempotent.annotations.Idempotent;
import io.xianzhi.core.result.ListResult;
import io.xianzhi.core.result.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseResult<ListResult<ProjectGroupVO>> pageProjectGroupList(ProjectGroupPage groupPage) {
        return ResponseResult.success();
    }

    /**
     * 创建项目分组  (幂等)
     *
     * @param projectGroupDTO 项目分组信息入参
     * @return 项目分组ID
     */
    @Idempotent
    @PostMapping(value = "/createProjectGroup")
    public ResponseResult<String> createProjectGroup(ProjectGroupDTO projectGroupDTO) {
        return ResponseResult.success();
    }

    /**
     * 更新项目分组
     *
     * @param projectGroupDTO 项目分组信息入参
     * @return 响应信息
     */
    @PostMapping(value = "/updateProjectGroup")
    public ResponseResult<Object> updateProjectGroup(ProjectGroupDTO projectGroupDTO) {
        return ResponseResult.success();
    }

    /**
     * 删除项目分组
     *
     * @param id 项目分组ID
     * @return 响应信息
     */
    @PostMapping(value = "/deletedProjectGroup")
    public ResponseResult<Object> deletedProjectGroup(String id) {
        return ResponseResult.success();
    }
}
