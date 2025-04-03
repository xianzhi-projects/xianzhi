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

import {h, ref, type VNode} from 'vue';
import type {ItemType, MenuMode} from 'ant-design-vue';
import router from '@/router';
import type {RouteRecordRaw} from 'vue-router';
import * as antIcons from '@ant-design/icons-vue';
import {ResourceType} from '@/api/resourceApi.ts';

// 定义自定义路由元数据接口
interface CustomRouteMeta {
  title?: string;         // 菜单标题
  icon?: string;          // 菜单图标
  showFlag?: boolean;     // 是否显示
  resourceType?: ResourceType; // 资源类型
  fullPath?: string;      // 完整路径
}

// 定义侧边栏折叠状态
export const collapsed = ref<boolean>(false);

// 定义菜单选项数据
export const MenuOptions = ref<ItemType[]>([]);

// 定义菜单状态，包括模式、选中键和展开键
export const state = ref({
  mode: 'inline' as MenuMode, // 菜单模式
  selectedKeys: [''],         // 当前选中的菜单项
  openKeys: [] as string[],   // 当前展开的菜单项
});

// 刷新菜单数据并初始化选中状态
export function refreshMenu(routerList: RouteRecordRaw[] | undefined) {
  MenuOptions.value = resolveRouter(routerList, '/admin'); // 生成菜单项
  const currentPath = router.currentRoute.value.path; // 获取当前路径
  const firstChildPath = getFirstChildPath(routerList, currentPath); // 获取第一个子菜单路径
  state.value.selectedKeys = [firstChildPath || currentPath]; // 设置选中项
  const parentPath = findParentPath(routerList, firstChildPath || currentPath); // 查找父路径
  if (parentPath) {
    state.value.openKeys = [parentPath]; // 设置展开项
  }
  if (firstChildPath && currentPath !== firstChildPath) {
    router.push({ path: firstChildPath }).catch(() => {}); // 跳转到第一个子菜单
  }
}

// 查找当前路径的父路径，用于展开菜单
function findParentPath(routers: RouteRecordRaw[] | undefined, currentPath: string): string | null {
  if (!routers) return null;
  const normalizedPath = currentPath.replace(/\/+$/, ''); // 移除末尾斜杠
  for (const route of routers) {
    const fullPath = (route.meta?.fullPath || `/admin/${route.path}`).replace(/\/+$/, '');
    if (route.children?.some((child) => {
      const childFullPath = (child.meta?.fullPath || `${fullPath}/${child.path}`).replace(/\/+$/, '');
      return childFullPath === normalizedPath;
    })) {
      return fullPath;
    }
    const found = findParentPath(route.children, normalizedPath);
    if (found) return found;
  }
  return null;
}

// 将路由配置转换为菜单项
function resolveRouter(routers: RouteRecordRaw[] | undefined, parentPath: string): ItemType[] {
  const menuItems: ItemType[] = [];
  if (!routers) return menuItems;

  routers.forEach((route: RouteRecordRaw) => {
    if (route.meta && route.meta.showFlag !== false) {
      const fullPath = route.meta?.fullPath || `${parentPath}/${route.path}`.replace(/\/+/g, '/');
      if (route.meta.resourceType === ResourceType.DIRECTORY) {
        const children = resolveRouter(route.children, fullPath); // 递归处理子菜单
        if (children.length > 0) {
          const menuItem = decodeConfig(route, true, fullPath) as {
            key: string;
            label: string;
            icon?: () => VNode;
            children?: ItemType[];
          };
          menuItem.children = children;
          menuItems.push(menuItem);
        }
      } else if (route.meta.resourceType === ResourceType.MENU) {
        menuItems.push(decodeConfig(route, false, fullPath));
      }
    }
  });
  return menuItems;
}

// 将单个路由项转换为菜单项配置
function decodeConfig(item: RouteRecordRaw, hasChildren: boolean, fullPath: string): ItemType {
  const routeMeta = item.meta as CustomRouteMeta || {};

  const baseItem: {
    key: string;
    label: string;
    icon?: () => VNode;
    children?: ItemType[];
  } = {
    key: fullPath,
    label: routeMeta.title || (typeof item.name === 'string' ? item.name : item.path) || item.path,
  };

  if (routeMeta.icon) {
    const IconComponent = antIcons[routeMeta.icon as keyof typeof antIcons];
    if (IconComponent) {
      baseItem.icon = () => h(IconComponent); // 添加图标
    }
  }

  return baseItem as ItemType;
}

// 处理菜单选中事件
export const onSelect = (event: { key: string }) => {
  state.value.selectedKeys = [event.key];
  router.push({ path: event.key }).catch(() => {});
};

// 获取目录的第一个子菜单路径，忽略末尾斜杠差异
function getFirstChildPath(routers: RouteRecordRaw[] | undefined, currentPath: string): string | null {
  if (!routers) return null;
  const normalizedPath = currentPath.replace(/\/+$/, ''); // 移除末尾斜杠
  const route = routers.find(r => {
    const routeFullPath = (r.meta?.fullPath || `/admin/${r.path}`).replace(/\/+$/, '');
    return routeFullPath === normalizedPath;
  });
  if (route && route.meta?.resourceType === ResourceType.DIRECTORY && route.children?.length) {
    const firstChild = route.children.find(child => child.meta?.resourceType === ResourceType.MENU);
    return firstChild?.meta?.fullPath || null;
  }
  return null;
}

export default { collapsed, state, onSelect, MenuOptions, refreshMenu };
