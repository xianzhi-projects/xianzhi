<script lang="ts" setup>
// 无逻辑，仅渲染路由视图
</script>

<template>
  <a-layout-content style="padding: 16px; height: calc(100vh - 64px); overflow: auto;">
    <a-watermark content="先知研发效能平台">
      <div style="height: 100%;">
        <router-view />
      </div>
    </a-watermark>

  </a-layout-content>
</template>

<style scoped>
/* 无需额外样式 */
</style>
