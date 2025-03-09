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
import io.xianzhi.system.model.vo.ResourceVO;
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
     * 根据ID查询资源信息
     *
     * @param id 资源ID
     * @return 资源信息
     */
    Optional<ResourceDO> selectResourceById(@Param("id") String id);

    List<ResourceVO> selectAdminResource();

}
