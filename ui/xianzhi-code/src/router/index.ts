import {createRouter, createWebHistory} from 'vue-router'
import Layout from '@/layout/Index.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Layout,
      children: [
        {
          path: '/',
          name: 'index',
          component: () => import('@/views/project/Index.vue'),
        },
        {
          path: '/project_group',
          name: 'projectGroup',
          component: () => import('@/views/project_group/Index.vue'),
        },
        {
          path: '/pull_request',
          name: 'pullRequest',
          component: () => import('@/views/pull_request/Index.vue'),

        }, {
          path: '/trashes',
          name: 'trashes',
          component: () => import('@/views/trashes/Index.vue'),

        },
        {
          path: '/:group',
          component: () => import('@/views/project_group/Index.vue'),
          children: [
          ]
        },
      ]
    },


  ],
})

export default router
