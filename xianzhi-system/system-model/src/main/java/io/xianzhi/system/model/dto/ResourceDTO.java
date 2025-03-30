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

package io.xianzhi.system.model.dto;

import io.xianzhi.system.model.enums.ResourceTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 资源信息入参
 *
 * @author Max
 * @since 1.0.0
 */
@Data
public class ResourceDTO implements Serializable {

    /**
     * 资源ID
     */
    private String id;
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 资源类型
     */
    private ResourceTypeEnum resourceType;
    /**
     * 资源标识
     */
    private String resourceKey;
    /**
     * 资源描述
     */
    private String resourceDesc;

    /**
     * 资源排序
     */
    private Integer resourceSorted;
    /**
     * 是否显示
     */
    private Boolean showFlag;
    /**
     * 是否启用
     */
    private Boolean enableFlag;
    /**
     * 菜单ICON
     */
    private String menuIcon;
    /**
     * 菜单组件
     */
    private String menuComponent;
    /**
     * 父级ID
     */
    private String parentId;
}
