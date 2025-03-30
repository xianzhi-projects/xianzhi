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

/**
 * 资源类型枚举
 */
export enum ResourceType {
  /**
   * 菜单
   */
  MENU = 'MENU',
  /**
   * 目录
   */
  CATALOG = 'CATALOG',
  /**
   * 按钮
   */
  BUTTON = 'BUTTON',
  /**
   * 链接
   */
  LINK = 'LINK',
}

/**
 * 资源信息入参
 */
export interface ResourceDTO {
  /**
   * 资源ID
   */
  id: string
  /**
   * 资源名称
   */
  resourceName: string
  /**
   * 资源类型
   */
  resourceType: ResourceType
  /**
   * 资源描述
   */
  resourceDesc: string
  /**
   * 资源key
   */
  resourceKey: string
  /**
   * 资源排序
   */
  resourceSorted: number
  /**
   * 父级ID
   */
  parentId: string
  /**
   * 菜单图标
   */
  menuIcon: string
  /**
   * 菜单组件
   */
  menuComponent: string
  /**
   * 是否显示
   */
  showFlag: boolean
  /**
   * 是否启用
   */
  enableFlag: boolean
}

/**
 * 资源信息出参
 */
export interface ResourceVO {
  /**
   * 资源ID
   */
  id: string
  /**
   * 资源名称
   */
  resourceName: string
  /**
   * 资源类型
   */
  resourceType: ResourceType
  /**
   * 资源描述
   */
  resourceDesc: string
  /**
   * 资源key
   */
  resourceKey: string
  /**
   * 资源排序
   */
  resourceSorted: number
  /**
   * 父级ID
   */
  parentId: string
  /**
   * 菜单图标
   */
  menuIcon: string
  /**
   * 菜单组件
   */
  menuComponent: string
  /**
   * 是否显示
   */
  showFlag: boolean
  /**
   * 是否启用
   */
  enableFlag: boolean
  /**
   * 子资源
   */
  children: ResourceVO[]
}


export interface RouteConfig {
  /**
   * 路由路径
   */
  path: string;
  /**
   * 路由名称
   */
  name: string;
  /**
   * 路由组件
   */
  component?: any;
  /**
   * 路由元信息
   */
  meta: {
    /**
     * 菜单图标
     */
    icon?: string;
    /**
     * 是否显示
     */
    showFlag: boolean;
  };
  children?: RouteConfig[];
}

