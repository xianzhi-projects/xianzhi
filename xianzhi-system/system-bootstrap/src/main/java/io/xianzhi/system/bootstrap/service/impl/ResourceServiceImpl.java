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
import io.xianzhi.system.model.enums.ResourceTypeEnum;
import io.xianzhi.system.model.vo.ResourceVO;
import io.xianzhi.system.security.context.UserContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
public class ResourceServiceImpl implements ResourceService, InitializingBean {

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
        return convertResourceTree(resourceMapper.selectAllResource());
    }

    /**
     * 查询当前用户资源信息
     *
     * @return 当前用户资源信息
     */
    @Override
    public List<ResourceVO> getCurrentUserResource() {
        List<ResourceDO> resource;
        if (UserContextHolder.admin()) {
            resource = resourceMapper.selectAllEnableResource();
        } else {
            resource = new ArrayList<>();
        }
        return convertResourceTree(resource);
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
        if (StringUtils.hasText(id)){
            resourceMapper.deletedResourceByParentId(id);
            resourceMapper.deletedResourceById(id);
        } else {
            throw new RuntimeException("资源ID不能为空");
        }

    }


    /**
     * 检查资源信息入参
     *
     * @param resourceDTO 资源信息入参
     * @return 资源信息
     */
    private ResourceDO checkedResourceDTO(ResourceDTO resourceDTO) {
        ResourceDO resource;
        String parentId = resourceDTO.getParentId();
        ResourceDO parent = null;
        if (StringUtils.hasText(parentId) && !"-1".equals(parentId)) {
            parent = resourceMapper.selectResourceById(parentId).orElseThrow(() -> new RuntimeException("父级资源不存在"));
        }
        if (StringUtils.hasText(resourceDTO.getId())) {
            resource = resourceMapper.selectResourceById(resourceDTO.getId()).orElseThrow(() -> new RuntimeException("资源不存在"));
        } else {
            resource = new ResourceDO();
            resource.setResourceType(resourceDTO.getResourceType().getCode());
            resource.setParentId(resourceDTO.getParentId());
            if (null != parent) {
                ResourceTypeEnum parentType = ResourceTypeEnum.getByCode(parent.getResourceType());
                switch (resourceDTO.getResourceType()){
                    case BUTTON -> {
                        if (!ResourceTypeEnum.MENU.equals(parentType)) {
                            throw new RuntimeException("按钮资源只能挂在目录或菜单下");
                        }
                    }
                    case CATALOG -> {
                        if (!ResourceTypeEnum.CATALOG.equals(parentType)) {
                            throw new RuntimeException("目录资源只能挂在目录下");
                        }
                    }
                    case MENU -> {
                        if (!ResourceTypeEnum.CATALOG.equals(parentType)) {
                            throw new RuntimeException("菜单资源只能挂在目录下");
                        }
                    }
                    case LINK -> {
                        if (!ResourceTypeEnum.CATALOG.equals(parentType)) {
                            throw new RuntimeException("链接资源只能挂在目录下");
                        }
                    }
                    default -> {
                        throw new RuntimeException("资源类型不支持");
                    }
                }
            }
        }
        if (resourceMapper.existsResourceByResourceNameAndIdNot(resourceDTO.getResourceName(), resourceDTO.getId())) {
            throw new RuntimeException("资源名称已存在");
        }
        if (resourceMapper.existsResourceByResourceKeyAndIdNot(resourceDTO.getResourceKey(), resourceDTO.getId())) {
            throw new RuntimeException("资源标识已存在");
        }
        resource.setResourceName(resourceDTO.getResourceName());
        resource.setResourceDesc(resourceDTO.getResourceDesc());
        resource.setResourceKey(resourceDTO.getResourceKey());
        resource.setMenuIcon(resourceDTO.getMenuIcon());
        resource.setMenuComponent(resourceDTO.getMenuComponent());
        resource.setShowFlag(resourceDTO.getShowFlag());
        resource.setEnableFlag(resourceDTO.getEnableFlag());
        resource.setResourceSorted(resourceDTO.getResourceSorted());
        return resource;
    }


    /**
     * 转换资源信息出参
     *
     * @param resources 资源信息实体
     * @return 资源信息出参
     */
    public List<ResourceVO> convertResourceTree(List<ResourceDO> resources) {
        if (ObjectUtils.isEmpty(resources)) {
            return Collections.emptyList();
        }
        return resources.stream().filter(item -> !StringUtils.hasText(item.getParentId()) || "-1".equals(item.getParentId()))
                .map(item -> convertResourceVO(item, resources)).sorted(Comparator.comparingInt(ResourceVO::getResourceSorted)).toList();
    }

    /**
     * 获取子集资源
     *
     * @param parentId  父级资源ID
     * @param resources 资源信息
     * @return 子集资源信息
     */
    private List<ResourceVO> getChildren(String parentId, List<ResourceDO> resources) {
        return resources.stream().filter(item -> item.getParentId().equals(parentId))
                .map(item -> convertResourceVO(item, resources)).sorted(Comparator.comparingInt(ResourceVO::getResourceSorted)).toList();
    }

    /**
     * 获取资源信息出参
     *
     * @param item      资源信息
     * @param resources 所有资源信息
     * @return 资源信息出参
     */
    public ResourceVO convertResourceVO(ResourceDO item, List<ResourceDO> resources) {
        if (null == item) {
            return null;
        }
        ResourceVO resourceVO = new ResourceVO();
        resourceVO.setId(item.getId());
        resourceVO.setResourceName(item.getResourceName());
        resourceVO.setResourceType(item.getResourceType());
        resourceVO.setResourceDesc(item.getResourceDesc());
        resourceVO.setResourceKey(item.getResourceKey());
        resourceVO.setResourceSorted(item.getResourceSorted());
        resourceVO.setMenuIcon(item.getMenuIcon());
        resourceVO.setMenuComponent(item.getMenuComponent());
        resourceVO.setShowFlag(item.getShowFlag());
        if (!ObjectUtils.isEmpty(resources)) {
            resourceVO.setChildren(getChildren(item.getId(), resources));
        }
        return resourceVO;
    }

    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {
//        UserContextHolder.setAnonymousUser();
//        ResourceDO resourceDO = new ResourceDO();
//        resourceDO.setResourceName("概览");
//        resourceDO.setResourceType(ResourceTypeEnum.CATALOG.getCode());
//        resourceDO.setResourceDesc("概览");
//        resourceDO.setResourceKey("/");
//        resourceDO.setResourceSorted(1);
//        resourceDO.setParentId("-1");
//        resourceDO.setMenuIcon("el-icon-s-home");
//        resourceDO.setShowFlag(true);
//        resourceMapper.insert(resourceDO);
//        ResourceDO resourceDO1 = new ResourceDO();
//        resourceDO1.setResourceName("分析页");
//        resourceDO1.setResourceType(ResourceTypeEnum.CATALOG.getCode());
//        resourceDO1.setResourceDesc("分析页");
//        resourceDO1.setResourceKey("/analytics");
//        resourceDO1.setResourceSorted(1);
//        resourceDO1.setParentId(resourceDO.getId());
//        resourceDO1.setMenuIcon("el-icon-s-data");
//        resourceDO1.setMenuComponent("@/analytics/Index.vue");
//        resourceDO1.setShowFlag(true);
//        resourceMapper.insert(resourceDO1);
    }
}
