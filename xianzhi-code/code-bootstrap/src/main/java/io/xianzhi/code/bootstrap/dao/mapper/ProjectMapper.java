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

package io.xianzhi.code.bootstrap.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.xianzhi.code.bootstrap.dao.dataobj.ProjectDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * 项目信息持久层
 *
 * @author Max
 * @since 1.0.0
 */
@Mapper
public interface ProjectMapper extends BaseMapper<ProjectDO> {

    /**
     * 根据项目ID查询项目信息
     *
     * @param id 项目ID
     * @return 项目信息
     */
    Optional<ProjectDO> selectProjectById(@Param("id") String id);

    /**
     * 判断项目名称是否存在
     *
     * @param projectName    项目名称
     * @param id             项目ID
     * @param projectGroupId 分组ID
     * @param projectType    项目类型
     * @return 是否存在
     */
    boolean existsProjectByProjectNameAndIdNotAndGroupIdAndProjectType(@Param("projectName") String projectName, @Param("id") String id, @Param("projectGroupId") String projectGroupId, @Param("projectType") String projectType);

    /**
     * 判断项目路径是否存在
     *
     * @param projectPath    项目路径
     * @param projectGroupId 项目分组ID
     * @param projectType    项目类型
     * @return 是否存在
     */
    boolean existsProjectByProjectPathAndGroupIdAndProjectType(@Param("projectPath") String projectPath, @Param("projectGroupId") String projectGroupId, @Param("projectType") String projectType);
}
