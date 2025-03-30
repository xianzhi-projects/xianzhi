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

package io.xianzhi.system.bootstrap.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.xianzhi.system.bootstrap.dao.dataobj.ResourceDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * 资源持久层
 *
 * @author Max
 * @since 1.0.0
 */
@Mapper
public interface ResourceMapper extends BaseMapper<ResourceDO> {
    /**
     * 查询所有的资源信息
     *
     * @return 查询所有的资源信息
     */
    List<ResourceDO> selectAllResource();

    /**
     * 查询所有启用的资源信息
     *
     * @return 查询所有启用的资源信息
     */
    List<ResourceDO> selectAllEnableResource();

    /**
     * 根据ID查询资源信息
     *
     * @param id 资源ID
     * @return 资源信息
     */
    Optional<ResourceDO> selectResourceById(@Param("id") String id);


    /**
     * 根据父级ID查询左右子集ID并且包含子集的子集
     */
    List<String> selectResourceIdByParentId(@Param("parentId") String parentId);


    /**
     * 判断资源名称是否存在
     *
     * @param resourceName 资源名称
     * @param id           资源ID
     * @return true: 存在，false: 不存在
     */
    boolean existsResourceByResourceNameAndIdNot(@Param("resourceName") String resourceName, @Param("id") String id);


    /**
     * 判断资源标识是否存在
     *
     * @param resourceKey 资源标识
     * @param id          资源ID
     * @return true: 存在，false: 不存在
     */
    boolean existsResourceByResourceKeyAndIdNot(@Param("resourceKey") String resourceKey, @Param("id") String id);


    /**
     * 根据资源ID删除资源信息
     *
     * @param id 资源ID
     */
    void deletedResourceById(@Param("id") String id);

    /**
     * 根据父级ID删除资源信息
     *
     * @param parentId 父级ID
     */
    void deletedResourceByParentId(@Param("parentId") String parentId);


}
