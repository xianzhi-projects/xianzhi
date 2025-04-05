import {createRouter, createWebHistory} from 'vue-router'
import Layout from '@/layout/Index.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Layout,
      children:[
        {
          path: '/projects',
          name: 'projects',
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

        }
      ]
    },


  ],
})

export default router
