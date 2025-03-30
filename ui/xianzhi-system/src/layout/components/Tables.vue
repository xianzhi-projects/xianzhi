<!-- components/Tabs.vue -->
<template>
  <!-- 使用 Element Plus 的 el-tabs 组件展示标签页 -->
  <el-tabs
    v-model="activeTab"
  class="demo-tabs"
  closable
  type="card"
  @tab-remove="removeTab"
  @tab-click="handleTabClick"
  >
  <!-- 使用 v-for 动态渲染每个标签页 -->
  <el-tab-pane
    v-for="item in tabsList"
  :key="item.path"
  :label="item.title"
  :name="item.path"
  >
  </el-tab-pane>
  </el-tabs>
</template>

<script lang="ts" setup>
import {onMounted, ref, watch} from "vue"; // 导入 Vue 的核心方法
import {useRoute, useRouter} from "vue-router"; // 导入 Vue Router 的方法
import {useTabsStore} from "@/stores/tabsStore"; // 导入自定义的 Pinia Store
import type {TabsPaneContext} from "element-plus"; // 导入 Element Plus 的类型

// 初始化路由和 Store
const router = useRouter();         // 获取路由实例，用于导航
const route = useRoute();           // 获取当前路由信息
const tabsStore = useTabsStore();   // 获取 Pinia Store 实例

// 定义当前激活的标签页路径，使用 ref 保持响应式
const activeTab = ref<string>("");

// 从 Store 中获取标签页列表
const tabsList = tabsStore.tabsList;

/**
 * 处理标签页点击事件，导航到对应路由
 * @param pane 被点击的标签页的上下文信息
 */
const handleTabClick = (pane: TabsPaneContext) => {
  const { props } = pane; // 获取标签页的属性
  // 根据标签页的路径进行路由导航
  router.push({ path: props.name as string });
};

/**
 * 删除指定的 指定的标签页，并处理激活状态的切换
 * @param targetName 要删除的标签页的路径
 */
const removeTab = (targetName: string) => {
  let nextActiveTab = activeTab.value; // 保存当前激活的标签页路径

  // 如果删除的是当前激活的标签页，需要切换到相邻标签页
  if (activeTab.value === targetName) {
    // 找到当前标签页在列表中的索引
    const currentIndex = tabsList.findIndex((tab) => tab.path === targetName);
    if (currentIndex !== -1) {
      // 优先切换到下一个标签页，如果没有则切换到上一个
      const nextTab = tabsList[currentIndex + 1] || tabsList[currentIndex - 1];
      nextActiveTab = nextTab ? nextTab.path : ""; // 如果没有相邻标签页，则置空
    }
  }

  // 从 Store 中删除目标标签页
  tabsStore.removeTab(targetName);

  // 更新当前激活的标签页
  activeTab.value = nextActiveTab;

  // 根据情况导航到新的激活标签页或首页
  if (nextActiveTab) {
    router.push({ path: nextActiveTab }); // 导航到新激活的标签页
  } else {
    router.push("/"); // 如果没有标签页，跳转到首页
  }
};

/**
 * 根据当前路由添加标签页
 */
const addCurrentTab = () => {
  // 创建新的标签页对象
  const tab = {
    path: route.path,         // 当前路由路径
    title: route.name as string, // 当前路由名称作为标题（需确保路由配置中有 name）
  };

  // 如果标签页不存在，则添加到 Store
  if (!tabsList.some((t) => t.path === tab.path)) {
    tabsStore.addTab(tab);
  }

  // 设置当前激活的标签页
  activeTab.value = tab.path;
};

// 监听路由变化，自动添加新标签页
watch(
  () => route.path, // 监听路由路径的变化
  () => {
    addCurrentTab(); // 路由变化时调用添加方法
  }
);

// 组件挂载时，添加初始标签页
onMounted(() => {
  addCurrentTab(); // 组件加载时根据当前路由添加标签页
});
</script>

<style lang="less" scoped>
/* 标签页容器的样式 */
.el-tabs {
  margin-left: 5px; // 左边距调整
}

/* 调整标签页头部样式 */
:deep(.el-tabs__header) {
  margin: 0; // 移除默认外边距
}

/* 单个标签页的样式 */
:deep(.el-tabs__item) {
  height: 26px;          // 标签页高度
  line-height: 26px;     // 行高与高度一致，垂直居中
  text-align: center;    // 文字居中
  margin: 0 3px !important; // 左右间距
  font-size: 12px !important; // 字体大小
  padding: 0 10px !important; // 左右内边距
}

/* 激活状态下的标签页样式 */
:deep(.is-active) {
  background-color: #5356e0 !important; // 激活时的背景色
  border-radius: 5px !important;        // 圆角
  color: #fff !important;               // 文字颜色
}

/* 激活状态下鼠标悬停的样式 */
:deep(.is-active:hover) {
  color: #fff !important; // 悬停时保持白色文字
}
</style>
