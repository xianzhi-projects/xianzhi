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

import io.xianzhi.code.bootstrap.dao.mapper.ProjectGroupMapper;
import io.xianzhi.code.bootstrap.service.ProjectGroupService;
import io.xianzhi.code.model.dto.AddMemberDTO;
import io.xianzhi.code.model.dto.ProjectGroupDTO;
import io.xianzhi.code.model.page.ProjectGroupPage;
import io.xianzhi.code.model.vo.ProjectGroupVO;
import io.xianzhi.code.model.vo.ProjectVO;
import io.xianzhi.core.result.ListResult;
import io.xianzhi.system.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目分组接口实现
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectGroupServiceImpl implements ProjectGroupService {

    /**
     * 用户接口
     */
    private final UserFacade userFacade;
    /**
     * 项目组数据访问接口
     */
    private final ProjectGroupMapper projectGroupMapper;
    /**
     * 查询分组列表
     *
     * @param group 分组名称
     * @return 分组列表
     */
    @Override
    public ProjectVO group(String group) {
        return null;
    }

    /**
     * 分页查询项目分组列表
     *
     * @param groupPage 分页查询参数
     * @return 项目分组列表
     */
    @Override
    public ListResult<ProjectGroupVO> pageProjectGroupList(ProjectGroupPage groupPage) {
        return null;
    }

    /**
     * 创建项目分组  (幂等)
     *
     * @param projectGroupDTO 项目分组信息入参
     * @return 项目分组ID
     */
    @Override
    public String createProjectGroup(ProjectGroupDTO projectGroupDTO) {
        return "";
    }

    /**
     * 更新项目分组
     *
     * @param projectGroupDTO 项目分组信息入参
     */
    @Override
    public void updateProjectGroup(ProjectGroupDTO projectGroupDTO) {

    }

    /**
     * 删除项目分组
     *
     * @param id 项目分组ID
     */
    @Override
    public void deletedProjectGroup(String id) {

    }

    /**
     * 添加成员
     *
     * @param groupId 项目分组ID
     * @param members 成员列表
     */
    @Override
    public void addMember(String groupId, List<AddMemberDTO> members) {

    }

    /**
     * 移除成员
     *
     * @param groupId 项目分组ID
     * @param members 成员列表
     */
    @Override
    public void removeMember(String groupId, List<String> members) {

    }
}
