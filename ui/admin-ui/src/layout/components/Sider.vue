<script lang="ts" setup>
import {onBeforeMount} from 'vue';
import {useRouterStore} from '@/stores/routerStore.ts';
import {refreshMenu} from '@/layout/index.ts';

// 初始化菜单数据
const routerStore = useRouterStore();

onBeforeMount(() => {
  refreshMenu(routerStore.routerList);
});
</script>

<template>
  <a-layout-sider
    v-model:collapsed="model.collapsed.value"
    :collapsed-width="80"
    :trigger="null"
    :width="260"
    collapsible
  >
    <div class="layout-sider-logo">
      <img height="32" src="@/assets/logo/logo.png" width="32" />
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
  height: 100vh;
  background-color: #191a23 !important;
  color: white;

  :deep(.ant-menu) {
    background-color: #191a23 !important;
  }

  .layout-sider-logo {
    height: 32px;
    margin: 16px;
    text-align: center;
    color: white;
    font-size: 20px;
    line-height: 32px;
    padding: 5px;

    img {
      vertical-align: middle;
    }

    span {
      margin-left: 8px;
      vertical-align: middle;
    }
  }
}
</style>
