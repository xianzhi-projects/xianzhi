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
import io.xianzhi.code.bootstrap.dao.mapper.ProjectMapper;
import io.xianzhi.code.bootstrap.service.ProjectService;
import io.xianzhi.code.model.dto.ProjectDTO;
import io.xianzhi.code.model.vo.ProjectVO;
import io.xianzhi.core.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
     * 新增项目
     *
     * @param projectDTO 项目信息入参
     * @return 项目ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createProject(ProjectDTO projectDTO) {
        ProjectDO projectDO = checkedProjectDTO(projectDTO);
        projectMapper.insert(projectDO);
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


    private ProjectDO checkedProjectDTO(ProjectDTO projectDTO) {
        ProjectDO project;
        if (StringUtils.hasText(projectDTO.getId())) {
            project = projectMapper.selectProjectById(projectDTO.getId()).orElseThrow(() -> new BusinessException("项目不存在"));
        } else {
            project = new ProjectDO();
        }




        return project;
    }
}
