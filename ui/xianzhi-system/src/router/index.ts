import type {RouteRecordRaw} from 'vue-router'
import {createRouter, createWebHistory} from 'vue-router'
import Layout from '@/layout/Index.vue'
import Login from '@/views/authorization/Login.vue'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import {useUserStore} from '@/stores/userStore'
import {useRouterStore} from '@/stores/routerStore.ts'
import {getCurrentUserResource} from '@/api/resourceApi'
import type {ResourceVO} from "@/types/resource.ts";
import {ResourceType} from "@/types/resource.ts";

// 基础路由配置
const baseRoutes: RouteRecordRaw[] = [

  {
    path: '/',
    name: 'index',
    component: Layout,
    children: [],
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
  },
  {
    path: '/:pathMatch(.*)*',
    component: () => import('@/views/error/404.vue'),
  },
]

// 创建 Vue Router 实例
const router = createRouter({
  history: createWebHistory(),
  routes: baseRoutes,
  scrollBehavior(to, from, savePosition) {
    return savePosition ? savePosition : { top: 0 }
  },
})

// 获取所有模块路由（懒加载）
const modulesRoutes = import.meta.glob('/src/views/**/*.vue')
const modulesRoutesKeys = Object.keys(modulesRoutes)

// 路由前置守卫
router.beforeEach(async (to, from, next) => {
  console.log(router.getRoutes())
  NProgress.start()
  const userStore = useUserStore()
  const routerStore = useRouterStore();

  // 未登录用户重定向到登录页
  if (!userStore.isLogin && to.path !== '/login') {
    next({ path: '/login', query: { redirect: to.path } })
    return
  }
  // 已登录用户访问登录页，重定向到首页
  if (userStore.isLogin && to.path === '/login') {
    next({ path: '/' })
    return
  }

  // 动态路由未加载时，加载动态路由
  if (userStore.isLogin && (!routerStore.routerList || routerStore.routerList.length === 0)) {
    try {
      const { data } = await getCurrentUserResource()
      if (!data || data.length === 0) {
        userStore.removeToken()
        next({ path: '/login' })
        return
      }

      // 生成动态路由
      const asyncRouter = generateRoutes(data)
      routerStore.setRouterList(asyncRouter.children)
      // 添加动态路由
      addRoutesSafely(asyncRouter)
      // 重新导航到当前路由
      next({ ...to, replace: true })
    } catch (error) {
      console.error('动态路由加载失败:', error)
      next({ path: '/login' })
    }
  } else {
    next()
  }
})

// 路由后置守卫
router.afterEach(() => {
  NProgress.done()
})

// 根据资源数据生成动态路由
function generateRoutes(items: ResourceVO[]): RouteRecordRaw {
  const adminRoute = baseRoutes.find((route) => route.path === '/')
  if (!adminRoute) {
    throw new Error('Admin route not found')
  }

  return {
    ...adminRoute,
    children: items.map(convertResourceToRoute),
  }
}

// 将资源项转换为路由配置
function convertResourceToRoute(item: ResourceVO): RouteRecordRaw {
  const route: RouteRecordRaw = {
    path: item.resourceKey,
    name: item.resourceName,
    meta: {
      icon: item.menuIcon,
      showFlag: item.showFlag,
      resourceType: item.resourceType,
    },
    component: undefined,
    children: [],
  }

  // 处理菜单类型资源
  if (item.resourceType === ResourceType.MENU) {
    const componentPath = item.menuComponent || item.resourceKey
    const moduleKey = modulesRoutesKeys.find((key) => key.includes(componentPath))
    if (moduleKey) {
      route.component = modulesRoutes[moduleKey]
    } else {
      console.error(`组件未找到: ${componentPath}`)
    }
  }

  // 处理目录类型资源
  if (item.resourceType === ResourceType.CATALOG && item.children?.length) {
    route.children = item.children.map((child) => convertResourceToRoute(child))
  }

  return route
}

// 安全地添加路由
function addRoutesSafely(routes: RouteRecordRaw | RouteRecordRaw[]) {
  const routesArray = Array.isArray(routes) ? routes : [routes]
  routesArray.forEach((route) => {
    try {
      router.addRoute(route)
    } catch (error) {
      console.error('添加路由失败:', error)
    }
  })
}

export default router
