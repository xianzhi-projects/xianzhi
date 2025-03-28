import {defineStore} from 'pinia'
import {ref} from 'vue'
import type {RouteRecordRaw} from 'vue-router'

export const useRouterStore = defineStore('router', () => {
  const routerList = ref<RouteRecordRaw[]>()

  /**
   * 设置路由信息
   * @param routers 路由信息
   */
  function setRouterList(routers: RouteRecordRaw[] | undefined) {
    routerList.value = routers
  }

  return { routerList, setRouterList }
})
