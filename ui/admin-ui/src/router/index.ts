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
import Layout from "@/layout/index.ts";

// 定义 CustomRouteMeta，扩展 RouteMeta
interface CustomRouteMeta extends RouteMeta {
  title?: string;
  icon?: string;
  showFlag?: boolean;
  resourceType?: ResourceType;
}

// 基础路由配置
const baseRoutes: RouteRecordRaw[] = [
  {
    path: '/',
    redirect: '/dashboard',
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/authentication/Login.vue'),
  },
  {
    path: '/admin',
    name: 'admin',
    component: Layout,
    children: [],
  },
  {
    path: '/:pathMatch(.*)*',
    name: '404',
    component: () => import('@/views/error/404.vue'),
  },
];

// 创建 Vue Router 实例
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: baseRoutes,
  scrollBehavior(to, from, savePosition) {
    return savePosition ? savePosition : { top: 0 };
  },
});

// 获取所有模块路由（懒加载）
const modulesRoutes = import.meta.glob('/src/views/**/*.vue');
const modulesRoutesKeys = Object.keys(modulesRoutes);

const publicPaths = ['/login', '/404'];

// 路由前置守卫
router.beforeEach(async (to, from, next) => {
  NProgress.start();
  const userStore = useUserStore();
  const routerStore = useRouterStore();

  console.log('To:', to.path, 'isLogin:', userStore.isLogin, 'routerList:', routerStore.routerList);

  if (publicPaths.includes(to.path)) {
    next();
    return;
  }

  if (!userStore.isLogin && to.path !== '/login') {
    next({ path: '/login', query: { redirect: to.path } });
    return;
  }

  if (userStore.isLogin && to.path === '/login') {
    next({ path: '/' });
    return;
  }

  if (userStore.isLogin && (!routerStore.routerList || routerStore.routerList.length === 0)) {
    try {
      const { data } = await getCurrentUserResource();
      console.log('Loaded resources:', data);
      if (!data || data.length === 0) {
        userStore.removeUser();
        next({ path: '/login' });
        return;
      }

      const asyncRouter = generateRoutes(data);
      routerStore.setRouterList(asyncRouter.children);
      console.log('Dynamic routes:', asyncRouter.children);
      addRoutesSafely(asyncRouter);
      console.log(router.getRoutes())
      next({ ...to, replace: true });
    } catch (error) {
      console.error('动态路由加载失败:', error);
      next({ path: '/login' });
    }
  } else {
    next();
  }
});

// 路由后置守卫
router.afterEach(() => {
  NProgress.done();
});

/**
 * 根据资源数据生成动态路由
 * @param items 后端返回的资源列表
 * @returns 动态路由配置
 */
function generateRoutes(items: ResourceVO[]): RouteRecordRaw {
  const adminRoute = baseRoutes.find((route) => route.path === '/admin');
  if (!adminRoute) {
    throw new Error('Admin route not found');
  }

  return {
    ...adminRoute,
    children: items.map(convertResourceToRoute),
  };
}

/**
 * 将资源项转换为路由配置
 * @param item 资源对象
 * @returns 路由配置
 */
function convertResourceToRoute(item: ResourceVO): RouteRecordRaw {
  const route: RouteRecordRaw = {
    path: item.resourceKey,
    name: item.resourceName,
    meta: {
      icon: item.menuIcon,
      showFlag: item.showFlag,
      resourceType: item.resourceType,
      title: item.resourceDesc,
    } as CustomRouteMeta, // 类型兼容 RouteMeta
    component: undefined,
    children: [],
  };

  if (item.resourceType === ResourceType.MENU) {
    const componentPath = item.menuComponent || item.resourceKey;
    const moduleKey = modulesRoutesKeys.find((key) => key.includes(componentPath));
    if (moduleKey) {
      route.component = modulesRoutes[moduleKey];
    } else {
      console.warn(`组件未找到: ${componentPath}, 使用默认 404`);
      route.component = () => import('@/views/error/404.vue');
    }
  }

  if (item.resourceType === ResourceType.DIRECTORY && item.children?.length) {
    route.children = item.children.map(convertResourceToRoute);
  }

  return route;
}

/**
 * 安全地添加路由
 * @param routes 要添加的路由
 */
function addRoutesSafely(routes: RouteRecordRaw | RouteRecordRaw[]) {
  const routesArray = Array.isArray(routes) ? routes : [routes];
  routesArray.forEach((route) => {
    try {
      router.addRoute(route);
    } catch (error) {
      console.error('添加路由失败:', error);
    }
  });
}

export default router;
