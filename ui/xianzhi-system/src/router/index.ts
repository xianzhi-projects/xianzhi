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
// src/router/index.ts
import {createRouter, createWebHistory} from 'vue-router';
import Layout from '@/layout/index.vue';
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

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
  console.log(router.getRoutes())
  NProgress.start()
})

// 路由后置守卫
router.afterEach(() => {
  NProgress.done()
})


export default router;

