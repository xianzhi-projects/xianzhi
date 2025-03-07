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

package io.xianzhi.system.bootstrap.service;

import io.xianzhi.system.model.dto.ResourceDTO;
import io.xianzhi.system.model.vo.ResourceVO;

import java.util.List;

/**
 * 资源接口
 *
 * @author Max
 * @since 1.0.0
 */
public interface ResourceService {
    /**
     * 查询资源树
     *
     * @return 资源树
     */
    List<ResourceVO> tree();

    /**
     * 查询当前用户资源信息
     *
     * @return 当前用户资源信息
     */
    List<ResourceVO> getCurrentUserResource();

    /**
     * 新增资源信息
     *
     * @param resourceDTO 资源信息入参
     * @return 资源ID
     */
    String createResource(ResourceDTO resourceDTO);

    /**
     * 更新资源信息
     *
     * @param resourceDTO 资源信息入参
     */
    void updateResource(ResourceDTO resourceDTO);

    /**
     * 删除资源
     *
     * @param id 资源ID
     */
    void deletedResource(String id);
}
