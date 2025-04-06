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

import io.xianzhi.code.bootstrap.dao.dataobj.ProjectDO;
import io.xianzhi.code.bootstrap.dao.dataobj.ProjectGroupDO;
import io.xianzhi.code.bootstrap.dao.mapper.ProjectGroupMapper;
import io.xianzhi.code.bootstrap.dao.mapper.ProjectMapper;
import io.xianzhi.code.bootstrap.handler.RepositoryHandler;
import io.xianzhi.code.bootstrap.service.ProjectService;
import io.xianzhi.code.model.dto.ProjectDTO;
import io.xianzhi.code.model.enums.ProjectTypeEnum;
import io.xianzhi.code.model.vo.ProjectVO;
import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.exception.BusinessException;
import io.xianzhi.system.security.context.UserContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 项目接口实现
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    /**
     * 项目持久层
     */
    private final ProjectMapper projectMapper;

    /**
     * 项目分组
     */
    private final ProjectGroupMapper projectGroupMapper;

    /**
     * 仓库处理
     */
    private final RepositoryHandler repositoryHandler;

    /**
     * 新增项目
     *
     * @param projectDTO 项目信息入参
     * @return 项目ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createProject(ProjectDTO projectDTO) {
        Pair<ProjectDO, ProjectGroupDO> result = checkedProjectDTO(projectDTO);
        ProjectDO projectDO = result.getFirst();
        projectMapper.insert(projectDO);
        // 创建真正的仓库
        repositoryHandler.createRepository(projectDO, result.getSecond());
        return projectDO.getId();
    }

    /**
     * 更新项目
     *
     * @param projectDTO 项目信息入参
     */
    @Override
    public void updateProject(ProjectDTO projectDTO) {

    }

    /**
     * 删除项目
     *
     * @param id 项目ID
     */
    @Override
    public void deletedProject(String id) {

    }

    /**
     * 查询项目详情
     *
     * @param id 项目ID
     * @return 项目详情
     */
    @Override
    public ProjectVO getProjectDetails(String id) {
        return null;
    }

    /**
     * 检查项目DTO
     *
     * @param projectDTO 项目DTO
     * @return 项目DO和项目分组DO
     */
    private Pair<ProjectDO, ProjectGroupDO> checkedProjectDTO(ProjectDTO projectDTO) {
        ProjectDO project;
        // 修改项目
        if (StringUtils.hasText(projectDTO.getId())) {
            project = projectMapper.selectProjectById(projectDTO.getId()).orElseThrow(() -> new BusinessException(CommonCode.ERROR));
        } else {
            // 新增项目
            project = new ProjectDO();
            ProjectTypeEnum projectType = projectDTO.getProjectType();
            if (projectType.equals(ProjectTypeEnum.GROUP)) {
                projectGroupMapper.selectProjectGroupById(projectDTO.getProjectGroupId())
                        .orElseThrow(() -> new BusinessException(CommonCode.DATA_NOT_EXISTS.getCode(), "code.project.group.not.exists"));
            } else {
                project.setProjectGroupId(UserContextHolder.getCurrentUserId());
            }

        }
        return Pair.of(project, Objects.requireNonNullElseGet(projectGroup, ProjectGroupDO::new));
    }
}
