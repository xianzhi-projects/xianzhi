import {h, ref} from 'vue'
import type {ItemType, MenuMode} from 'ant-design-vue'
import router from '@/router'
import type {RouteRecordRaw} from 'vue-router'
import * as antIcons from '@ant-design/icons-vue'
import {ResourceType} from "@/api/resourceApi.ts";
// 菜单顶部缩放
const collapsed = ref<boolean>(false);
// 菜单信息
const MenuOptions = ref<ItemType[]>([])
const state = ref({
  mode: 'inline' as MenuMode,
  selectedKeys: [''],
  openKeys: [],
})

export function refreshMenu(routerList: RouteRecordRaw[] | undefined) {
  MenuOptions.value = resolveRouter(routerList)
  console.log(MenuOptions.value)
  // 设置选中的菜单
  state.value.selectedKeys = [router.currentRoute.value.meta.fullPath]
}


/**
 * 转换路由配置为菜单配置
 * @param routers 路由信息
 */
const resolveRouter = (routers: RouteRecordRaw[] | undefined): ItemType[] => {
  const childrenItems: ItemType[] = []
  if (routers) {
    routers.forEach((route: RouteRecordRaw) => {
      if (route.meta && route.meta.showFlag) {
        if (route.meta.resourceType === ResourceType.DIRECTORY) {
          if (route?.children && route?.children?.length != 0) {
            const configItem = decodeConfig(route)
            const childrenData = resolveRouter(route?.children )
            if (childrenData.length != 0) {
              if (configItem) {
                configItem['children'] = childrenData
              }
            }
            childrenItems.push(configItem)
          }
        } else {
          childrenItems.push(decodeConfig(route, null))
        }
      }
    })
  }
  return childrenItems
}


// 获取单个导航菜单的数据
const decodeConfig = (item: RouteRecordRaw) => {
  const routeMeta = item?.meta ?? {}
  console.log(item)
  const menuItem: ItemType = {
    key: item?.path ,
    label: item?.name,
  }
  // 动态生成图标
  if (routeMeta?.icon) {
    const IconComponent = antIcons[routeMeta.icon as keyof typeof antIcons]
    // 从全局注册的图标中取出
    if (IconComponent) {
      menuItem.icon = () => h(IconComponent) // 使用 h 函数生成图标
    }
  }
  return menuItem
}

// 当选中菜单的时候，跳转到对应的路由
const onSelect = event => {
  router.push({path: event.key})
}


export default {collapsed, state, onSelect, MenuOptions, refreshMenu}

