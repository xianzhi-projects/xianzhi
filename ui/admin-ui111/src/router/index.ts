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
import type {RouteMeta, RouteRecordRaw} from 'vue-router';
import {createRouter, createWebHistory} from 'vue-router';
import NProgress from 'nprogress';
import 'nprogress/nprogress.css';
import {useUserStore} from '@/stores/userStore';
import {useRouterStore} from '@/stores/routerStore';
import type {ResourceVO} from '@/api/resourceApi.ts';
import {getCurrentUserResource, ResourceType} from '@/api/resourceApi';
import Layout from '@/layout/index.vue';
import model from '@/layout/index.ts';

// 定义自定义路由元数据接口，扩展 RouteMeta
interface CustomRouteMeta extends RouteMeta {
  title?: string;         // 路由标题
  icon?: string;          // 路由图标
  showFlag?: boolean;     // 是否显示在菜单中
  resourceType?: ResourceType; // 资源类型（菜单或目录）
  fullPath?: string;      // 路由完整路径
}

// 定义基础路由配置
const baseRoutes: RouteRecordRaw[] = [
  { path: '/', redirect: '/dashboard' }, // 根路径重定向到 /dashboard
  { path: '/login', name: 'login', component: () => import('@/views/authentication/Login.vue') }, // 登录页
  { path: '/admin', name: 'admin', component: Layout, children: [] }, // 管理布局页
  { path: '/:pathMatch(.*)*', name: '404', component: () => import('@/views/error/404.vue') }, // 404 页面
];

// 创建 Vue Router 实例
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL), // 使用 HTML5 历史模式
  routes: baseRoutes, // 初始路由
  scrollBehavior(to, from, savePosition) {
    return savePosition ? savePosition : { top: 0 }; // 页面滚动行为，默认回到顶部
  },
});

// 动态加载所有视图组件
const modulesRoutes = import.meta.glob('/src/views/**/*.vue');
const modulesRoutesKeys = Object.keys(modulesRoutes);

// 定义无需认证的公共路径
const publicPaths = ['/login', '/404'];

// 路由前置守卫：处理认证、动态路由加载和默认子菜单跳转
router.beforeEach(async (to, from, next) => {
  NProgress.start(); // 启动进度条
  const userStore = useUserStore();
  const routerStore = useRouterStore();

  // 如果目标路径是公共路径，直接放行
  if (publicPaths.includes(to.path)) {
    next();
    return;
  }

  // 未登录且目标不是登录页，重定向到登录页
  if (!userStore.isLogin && to.path !== '/login') {
    next({ path: '/login', query: { redirect: to.path } });
    return;
  }

  // 已登录且目标是登录页，重定向到首页
  if (userStore.isLogin && to.path === '/login') {
    next({ path: '/' });
    return;
  }

  // 已登录但路由列表未初始化，加载动态路由
  if (userStore.isLogin && (!routerStore.routerList || routerStore.routerList.length === 0)) {
    try {
      const { data } = await getCurrentUserResource(); // 获取用户资源
      const asyncRouter = generateRoutes(data); // 生成动态路由
      routerStore.setRouterList(asyncRouter.children); // 存储路由列表
      addRoutesSafely(asyncRouter); // 添加路由到路由表
      const correctedPath = to.path.startsWith('/admin') ? to.path : `/admin${to.path}`; // 规范化路径
      const firstChildPath = getFirstChildPath(routerStore.routerList, correctedPath); // 检查是否需要跳转到子菜单
      if (firstChildPath && correctedPath !== firstChildPath) {
        next({ path: firstChildPath, replace: true }); // 跳转到第一个子菜单
      } else {
        next({ path: correctedPath, replace: true }); // 继续导航
      }
    } catch (error) {
      next({ path: '/login' }); // 加载失败重定向到登录页
    }
  } else {
    // 已登录且路由已初始化，检查是否需要跳转到子菜单
    const correctedPath = to.path.startsWith('/admin') ? to.path : `/admin${to.path}`;
    const firstChildPath = getFirstChildPath(routerStore.routerList, correctedPath);
    if (firstChildPath && correctedPath !== firstChildPath) {
      next({ path: firstChildPath, replace: true });
    } else if (to.path !== correctedPath) {
      next({ path: correctedPath, replace: true });
    } else {
      next();
    }
  }
});

// 路由后置守卫：同步菜单选中状态
router.afterEach((to) => {
  NProgress.done(); // 结束进度条
  const routerStore = useRouterStore();
  const firstChildPath = getFirstChildPath(routerStore.routerList, to.path); // 获取第一个子菜单路径
  model.state.value.selectedKeys = [firstChildPath || to.path]; // 设置选中状态
});

// 根据资源生成动态路由
function generateRoutes(items: ResourceVO[]): RouteRecordRaw {
  const adminRoute = baseRoutes.find((route) => route.path === '/admin');
  if (!adminRoute) throw new Error('Admin route not found');
  return { ...adminRoute, children: items.map((item) => convertResourceToRoute(item, '/admin')) };
}

// 将资源转换为路由配置
function convertResourceToRoute(item: ResourceVO, parentPath: string): RouteRecordRaw {
  const routePath = item.resourceKey.startsWith('/') ? item.resourceKey.slice(1) : item.resourceKey; // 移除前导斜杠
  const pathSegments = routePath.split('/').filter(Boolean); // 分割并移除空段
  const relativePath = pathSegments[pathSegments.length - 1]; // 取最后一段作为相对路径
  const fullPath = `${parentPath}/${relativePath}`.replace(/\/+/g, '/'); // 拼接完整路径并规范化

  const route: RouteRecordRaw = {
    path: relativePath,
    name: item.resourceName,
    meta: {
      icon: item.menuIcon,
      showFlag: item.showFlag,
      resourceType: item.resourceType,
      title: item.resourceDesc,
      fullPath: fullPath,
    } as CustomRouteMeta,
    component: undefined,
    children: [],
  };

  // 如果是菜单类型，加载对应组件
  if (item.resourceType === ResourceType.MENU) {
    const componentPath = item.menuComponent || item.resourceKey;
    const moduleKey = modulesRoutesKeys.find((key) => key.includes(componentPath));
    if (moduleKey) {
      route.component = modulesRoutes[moduleKey];
    } else {
      route.component = () => import('@/views/error/404.vue'); // 未找到组件时使用 404
    }
  }

  // 如果是目录类型，递归处理子路由
  if (item.resourceType === ResourceType.DIRECTORY && item.children?.length) {
    route.children = item.children.map((child) => convertResourceToRoute(child, fullPath));
  }

  return route;
}

// 安全添加路由到路由表
function addRoutesSafely(routes: RouteRecordRaw | RouteRecordRaw[]) {
  const routesArray = Array.isArray(routes) ? routes : [routes];
  routesArray.forEach((route) => {
    try {
      router.addRoute(route);
    } catch (error) {
      // 忽略添加失败的路由
    }
  });
}

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

export default router;
