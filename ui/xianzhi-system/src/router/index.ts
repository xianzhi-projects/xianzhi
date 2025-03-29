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
import {createRouter, createWebHistory} from 'vue-router';
import Layout from '@/layout/index.vue';
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import {useUserStore} from "@/stores/userStore.ts";
import {useRouterStore} from "@/stores/routerStore.ts";
import {getCurrentUserResource} from "@/api/resourceApi.ts";
import {ElMessage} from "element-plus";

const routes = [
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/authorization/Login.vue')
  }, // 登录页
  {
    path: '/',
    name: 'index',
    component: Layout,
    children: [
      {
        path: '/dashboard',
        name: 'dashboard',
        component: () => import('@/views/Dashboard.vue'),
      }
    ]
  }, // 管理布局页
  {
    path: '/:pathMatch(.*)*',
    name: '404', component: () => import('@/views/error/404.vue')
  }, // 404 页面
];

const router = createRouter({
  history: createWebHistory(),
  routes: routes,
  scrollBehavior(to, from, savePosition) {
    return savePosition ? savePosition : {top: 0}
  },
})

// 获取所有模块路由（懒加载）
const modulesRoutes = import.meta.glob('/src/views/**/*.vue')
const modulesRoutesKeys = Object.keys(modulesRoutes)
// 路由前置守卫
router.beforeEach(async (to, from, next) => {
  NProgress.start()
  const userStore = useUserStore()
  const routerStore = useRouterStore()
  // 未登录用户重定向到登录页
  if (!userStore.isLogin && to.path !== '/login') {

    next({path: '/login', query: {redirect: to.path}})
    return
  }
  if (userStore.isLogin && (!routerStore.routerList || routerStore.routerList.length === 0)) {
    try {
      const { data } = await getCurrentUserResource()
      if (!data || data.length === 0) {
        ElMessage.error('当前用户没有权限访问任何页面')
        userStore.removeToken()
        next({ path: '/login' })
        return
      }
      // 重新导航到当前路由
      next({ ...to, replace: true })
    } catch (error) {
      ElMessage.error('动态路由加载失败')
      userStore.removeToken()
      next({ path: '/login' })
      return
    }
  }
  // 已登录用户访问登录页，重定向到首页
  if (userStore.isLogin && to.path === '/login') {
    next({path: '/'})
    return
  }
  next()
})
// 路由后置守卫
router.afterEach(() => {
  NProgress.done()
})


export default router;

