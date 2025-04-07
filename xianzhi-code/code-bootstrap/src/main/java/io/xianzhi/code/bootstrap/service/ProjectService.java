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

import io.xianzhi.code.model.dto.AddMemberDTO;
import io.xianzhi.code.model.dto.ImportProjectDTO;
import io.xianzhi.code.model.dto.ProjectDTO;
import io.xianzhi.code.model.page.ProjectPage;
import io.xianzhi.code.model.vo.ProjectVO;
import io.xianzhi.core.result.ListResult;

import java.util.List;

/**
 * 项目接口
 *
 * @author Max
 * @since 1.0.0
 */
public interface ProjectService {

    /**
     * 新增项目
     *
     * @param projectDTO 项目信息入参
     * @return 项目ID
     */
    String createProject(ProjectDTO projectDTO);

    /**
     * 更新项目
     *
     * @param projectDTO 项目信息入参
     */
    void updateProject(ProjectDTO projectDTO);

    /**
     * 删除项目
     *
     * @param id 项目ID
     */
    void deletedProject(String id);

    /**
     * 查询项目详情
     *
     * @param id 项目ID
     * @return 项目详情
     */
    ProjectVO getProjectDetails(String id);

    ProjectVO project(String group, String project);

    /**
     * 分页查询项目列表
     *
     * @param projectPage 分页查询条件
     * @return 项目列表
     */
    ListResult<ProjectVO> pageProjectList(ProjectPage projectPage);

    /**
     * 导入项目
     *
     * @param importProjectDTO 导入项目入参
     */
    void importProject(ImportProjectDTO importProjectDTO);

    /**
     * 添加成员
     *
     * @param projectId 项目ID
     * @param members   成员信息
     */
    void addMember(String projectId, List<AddMemberDTO> members);

    /**
     * 移除成员
     *
     * @param projectId 项目ID
     * @param members   成员ID
     */
    void removeMember(String projectId, List<String> members);

}
