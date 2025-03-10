<script lang="ts" setup>
import {onBeforeMount} from 'vue';
import {useRouterStore} from '@/stores/routerStore.ts';
import model from '@/layout/index.ts';

// 初始化菜单数据
const routerStore = useRouterStore();

onBeforeMount(() => {
  model.refreshMenu(routerStore.routerList); // 在组件挂载前刷新菜单
});
</script>

<template>
  <!-- 侧边栏布局 -->
  <a-layout-sider
    v-model:collapsed="model.collapsed.value"
    :collapsed-width="80"
    :trigger="null"
    :width="260"
    collapsible
  >
    <!-- 侧边栏顶部 Logo 和标题 -->
    <div class="layout-sider-logo">
      <!-- <img height="32" src="@/assets/logo/logo.png" width="32" /> -->
      <span v-if="!model.collapsed.value">先知后台管理系统</span>
    </div>
    <!-- 动态菜单 -->
    <a-menu
      v-model:openKeys="model.state.value.openKeys"
      v-model:selectedKeys="model.state.value.selectedKeys"
      :items="model.MenuOptions.value"
      mode="inline"
      theme="dark"
      @select="model.onSelect"
    />
  </a-layout-sider>
</template>

<style lang="less" scoped>
.ant-layout-sider {
  height: 100vh;              // 占满视口高度
  background-color: #191a23 !important; // 设置背景色
  color: white;               // 设置文字颜色

  // 深度选择器，修改菜单背景色
  :deep(.ant-menu) {
    background-color: #191a23 !important;
  }

  // 侧边栏 Logo 样式
  .layout-sider-logo {
    height: 32px;
    margin: 16px;
    text-align: center;
    color: white;
    font-size: 20px;
    line-height: 32px;
    padding: 5px;

    img {
      vertical-align: middle; // 图片垂直居中
    }

    span {
      margin-left: 8px;      // 与图片间距
      vertical-align: middle; // 文字垂直居中
    }
  }
}
</style>
