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

const routes = [
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/authorization/Login.vue')
  }, // 登录页
  {
    path: '/',
    name: 'admin',
    component: Layout,
    children: [{
      path: '/dashboard',
      name: 'dashboard',
      component: () => import('@/views/Dashboard.vue'),
    }]
  }, // 管理布局页
  {
    path: '/:pathMatch(.*)*',
    name: '404', component: () => import('@/views/error/404.vue')
  }, // 404 页面
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;

