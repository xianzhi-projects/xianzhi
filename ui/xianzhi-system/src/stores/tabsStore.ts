// stores/tabsStore.ts
import {defineStore} from "pinia"; // 导入 Pinia 的 defineStore 方法
import {computed, ref} from "vue"; // 导入 Vue 的 ref 和 computed 用于响应式数据

// 定义标签页的数据类型
export type Tab = {
  title: string; // 标签页的标题，例如 "首页" 或 "用户管理"
  path: string;  // 标签页对应的路由路径，例如 "/home" 或 "/users"
};

/**
 * 定义一个 Pinia Store 来管理标签页的状态
 * Store 的名称是 "tabs"
 */
export const useTabsStore = defineStore("tabs", () => {
  // 使用 ref 定义一个响应式的标签页列表，初始值为空数组
  const tabs = ref<Tab[]>([]);

  // 使用 computed 定义一个计算属性，返回当前的标签页列表
  const tabsList = computed(() => tabs.value);

  /**
   * 添加一个新的标签页到列表中
   * @param tab 要添加的标签页对象，包含 title 和 path
   */
  function addTab(tab: Tab) {
    // 检查是否已存在相同路径的标签页，如果存在则不添加，避免重复
    if (tabs.value.some((item) => item.path === tab.path)) return;
    // 将新标签页添加到列表
    tabs.value.push(tab);
  }

  /**
   * 从列表中删除指定的标签页
   * @param targetPath 要删除的标签页的路由路径
   */
  function removeTab(targetPath: string) {
    // 使用 filter 方法过滤掉目标路径的标签页，更新列表
    tabs.value = tabs.value.filter((tab) => tab.path !== targetPath);
  }

  // 返回 Store 的状态和方法，供外部组件使用
  return {
    tabs,       // 响应式的标签页列表
    tabsList,   // 计算属性，获取当前标签页列表
    addTab,     // 添加标签页的方法
    removeTab,  // 删除标签页的方法
  };
});
