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
import io.xianzhi.code.model.enums.VisibilityEnum;
import io.xianzhi.code.model.vo.ProjectVO;
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


    private Pair<ProjectDO, ProjectGroupDO> checkedProjectDTO(ProjectDTO projectDTO) {
        ProjectDO project;
        ProjectTypeEnum projectType = projectDTO.getProjectType();
        ProjectGroupDO projectGroup;
        if (projectType.getCode().equals(ProjectTypeEnum.GROUP.getCode())) {
            projectGroup = projectGroupMapper.selectProjectGroupById(projectDTO.getProjectGroupId()).orElseThrow(() -> new BusinessException("项目分组不存在"));
        } else {
            projectGroup = null;
        }
        if (StringUtils.hasText(projectDTO.getId())) {
            project = projectMapper.selectProjectById(projectDTO.getId()).orElseThrow(() -> new BusinessException("项目不存在"));
        } else {
            project = new ProjectDO();
            if (projectMapper.existsProjectByProjectPathAndGroupIdAndProjectType(projectDTO.getProjectPath(), projectDTO.getProjectGroupId(), projectDTO.getProjectType().getCode())) {
                throw new BusinessException("项目路径已经存在");
            }
            if (null == projectGroup) {
                project.setProjectGroupId(UserContextHolder.getCurrentUserId());
            } else {
                project.setProjectGroupId(projectGroup.getId());
            }
            project.setProjectType(projectDTO.getProjectType().getCode());
            project.setProjectPath(projectDTO.getProjectPath());
        }
        if (projectMapper.existsProjectByProjectNameAndIdNotAndGroupIdAndProjectType(projectDTO.getProjectName(), projectDTO.getId(), projectDTO.getProjectGroupId(), projectDTO.getProjectType().getCode())) {
            throw new BusinessException("项目名称已经存在");
        }
        project.setProjectName(projectDTO.getProjectName());
        project.setProjectDesc(projectDTO.getProjectDesc());
        project.setProjectLogo(projectDTO.getProjectLogo());
        // 个人项目可以随便修改
        if (null == projectGroup) {
            project.setProjectVisibility(projectDTO.getProjectVisibility().getCode());
        } else {
            // 分组是私有的，项目只能是私有
            if (VisibilityEnum.PRIVATE.getCode().equals(projectGroup.getVisibility())) {
                project.setProjectVisibility(VisibilityEnum.PRIVATE.getCode());
                // 分组是内部的
            } else if (VisibilityEnum.INNER.getCode().equals(projectGroup.getVisibility())) {
                if (projectDTO.getProjectVisibility().equals(VisibilityEnum.PRIVATE) || projectDTO.getProjectVisibility().equals(VisibilityEnum.INNER)) {
                    project.setProjectVisibility(projectDTO.getProjectVisibility().getCode());
                } else {
                    throw new BusinessException("项目可见性错误");
                }
            } else {
                project.setProjectVisibility(projectDTO.getProjectVisibility().getCode());
            }
        }
        return Pair.of(project, Objects.requireNonNullElseGet(projectGroup, ProjectGroupDO::new));
    }
}
