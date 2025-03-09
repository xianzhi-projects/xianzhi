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

package io.xianzhi.system.bootstrap.service.impl;

import io.xianzhi.system.bootstrap.dao.dataobj.ResourceDO;
import io.xianzhi.system.bootstrap.dao.mapper.ResourceMapper;
import io.xianzhi.system.bootstrap.service.ResourceService;
import io.xianzhi.system.model.dto.ResourceDTO;
import io.xianzhi.system.model.vo.ResourceVO;
import io.xianzhi.system.security.context.UserContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 资源接口实现
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    /**
     * 资源持久层
     */
    private final ResourceMapper resourceMapper;

    /**
     * 查询资源树
     *
     * @return 资源树
     */
    @Override
    public List<ResourceVO> tree() {
        return List.of();
    }

    /**
     * 查询当前用户资源信息
     *
     * @return 当前用户资源信息
     */
    @Override
    public List<ResourceVO> getCurrentUserResource() {
        if (UserContextHolder.admin()) {
            resourceMapper.selectAllResource();
        } else {

        }
        return List.of();
    }

    /**
     * 新增资源信息
     *
     * @param resourceDTO 资源信息入参
     * @return 资源ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createResource(ResourceDTO resourceDTO) {
        ResourceDO resourceDO = checkedResourceDTO(resourceDTO);
        resourceMapper.insert(resourceDO);
        return resourceDO.getId();
    }

    /**
     * 更新资源信息
     *
     * @param resourceDTO 资源信息入参
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateResource(ResourceDTO resourceDTO) {
        ResourceDO resourceDO = checkedResourceDTO(resourceDTO);
        resourceMapper.updateById(resourceDO);
    }

    /**
     * 删除资源
     *
     * @param id 资源ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletedResource(String id) {

    }


    /**
     * 检查资源信息入参
     *
     * @param resourceDTO 资源信息入参
     * @return 资源信息
     */
    private ResourceDO checkedResourceDTO(ResourceDTO resourceDTO) {
        ResourceDO resource;
        if (StringUtils.hasText(resourceDTO.getId())) {
            resource = resourceMapper.selectResourceById(resourceDTO.getId()).orElseThrow(() -> new RuntimeException("资源不存在"));
        } else {
            resource = new ResourceDO();
        }
        return resource;
    }
}
