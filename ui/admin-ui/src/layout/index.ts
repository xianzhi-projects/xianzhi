import {h, ref, type VNode} from 'vue';
import type {ItemType, MenuMode} from 'ant-design-vue';
import router from '@/router';
import type {RouteRecordRaw} from 'vue-router';
import * as antIcons from '@ant-design/icons-vue';
import {ResourceType} from '@/api/resourceApi.ts';

// 定义自定义的 RouteMeta 类型
interface CustomRouteMeta {
  title?: string;
  icon?: string;
  showFlag?: boolean;
  resourceType?: ResourceType;
}

// 侧边栏折叠状态
export const collapsed = ref<boolean>(false);

// 菜单选项
export const MenuOptions = ref<ItemType[]>([]);

// 菜单状态
export const state = ref({
  mode: 'inline' as MenuMode,
  selectedKeys: [''],
  openKeys: [] as string[],
});

/**
 * 刷新菜单数据
 * @param routerList 路由列表
 */
export function refreshMenu(routerList: RouteRecordRaw[] | undefined) {
  MenuOptions.value = resolveRouter(routerList);
  const currentPath = router.currentRoute.value.path;
  state.value.selectedKeys = [currentPath];
  const parentPath = findParentPath(routerList, currentPath);
  if (parentPath) {
    state.value.openKeys = [parentPath];
  }
}

/**
 * 查找父路径以展开子菜单
 * @param routers 路由列表
 * @param currentPath 当前路径
 * @returns 父路径
 */
const findParentPath = (routers: RouteRecordRaw[] | undefined, currentPath: string): string | null => {
  if (!routers) return null;
  for (const route of routers) {
    if (route.children?.some((child) => child.path === currentPath)) {
      return route.path;
    }
    const found = findParentPath(route.children, currentPath);
    if (found) return found;
  }
  return null;
};

/**
 * 转换路由配置为菜单配置
 * @param routers 路由信息
 * @returns 菜单项数组
 */
const resolveRouter = (routers: RouteRecordRaw[] | undefined): ItemType[] => {
  const menuItems: ItemType[] = [];
  if (!routers) return menuItems;

  routers.forEach((route: RouteRecordRaw) => {
    if (route.meta && route.meta.showFlag !== false) {
      if (route.meta.resourceType === ResourceType.DIRECTORY) {
        const children = resolveRouter(route.children);
        if (children.length > 0) {
          const menuItem = decodeConfig(route, true) as {
            key: string;
            label: string;
            icon?: () => VNode;
            children?: ItemType[];
          };
          menuItem.children = children;
          menuItems.push(menuItem);
        }
      } else if (route.meta.resourceType === ResourceType.MENU) {
        menuItems.push(decodeConfig(route, false));
      }
    }
  });
  return menuItems;
};

/**
 * 将路由配置转换为菜单项
 * @param item 路由项
 * @param hasChildren 是否有子菜单
 * @returns 菜单项配置
 */
const decodeConfig = (item: RouteRecordRaw, hasChildren: boolean): ItemType => {
  const routeMeta = item.meta as CustomRouteMeta || {}; // 使用自定义类型

  const baseItem: {
    key: string;
    label: string;
    icon?: () => VNode;
    children?: ItemType[];
  } = {
    key: item.path,
    // 确保 label 是 string 类型
    label: routeMeta.title || (typeof item.name === 'string' ? item.name : item.path) || item.path,
  };

  // 添加图标
  if (routeMeta.icon) {
    const IconComponent = antIcons[routeMeta.icon as keyof typeof antIcons];
    if (IconComponent) {
      baseItem.icon = () => h(IconComponent);
    }
  }

  return baseItem as ItemType;
};

/**
 * 菜单选中时的路由跳转
 * @param event 选中事件
 */
export const onSelect = (event: { key: string }) => {
  state.value.selectedKeys = [event.key];
  router.push({ path: event.key });
};

export default {
  collapsed,
  state,
  onSelect,
  MenuOptions,
  refreshMenu,
};
