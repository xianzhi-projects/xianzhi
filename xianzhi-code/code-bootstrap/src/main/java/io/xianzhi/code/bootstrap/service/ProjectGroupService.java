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
import io.xianzhi.code.model.dto.ProjectGroupDTO;
import io.xianzhi.code.model.page.ProjectGroupPage;
import io.xianzhi.code.model.vo.ProjectGroupVO;
import io.xianzhi.code.model.vo.ProjectVO;
import io.xianzhi.core.result.ListResult;

import java.util.List;

/**
 * 项目分组接口
 *
 * @author Max
 * @since 1.0.0
 */
public interface ProjectGroupService {
    /**
     * 查询分组列表
     *
     * @param group 分组名称
     * @return 分组列表
     */
    ProjectVO group(String group);

    /**
     * 分页查询项目分组列表
     *
     * @param groupPage 分页查询参数
     * @return 项目分组列表
     */
    ListResult<ProjectGroupVO> pageProjectGroupList(ProjectGroupPage groupPage);

    /**
     * 创建项目分组  (幂等)
     *
     * @param projectGroupDTO 项目分组信息入参
     * @return 项目分组ID
     */
    String createProjectGroup(ProjectGroupDTO projectGroupDTO);

    /**
     * 更新项目分组
     *
     * @param projectGroupDTO 项目分组信息入参
     */
    void updateProjectGroup(ProjectGroupDTO projectGroupDTO);

    /**
     * 删除项目分组
     *
     * @param id 项目分组ID
     */
    void deletedProjectGroup(String id);

    /**
     * 添加成员
     *
     * @param groupId 项目分组ID
     * @param members 成员列表
     */
    void addMember(String groupId, List<AddMemberDTO> members);

    /**
     * 移除成员
     *
     * @param groupId 项目分组ID
     * @param members 成员列表
     */
    void removeMember(String groupId, List<String> members);
}
