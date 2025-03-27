<template>
  <a-result status="404" sub-title="对不起,你访问的页面不存在" title="404">
    <template #extra>
      <router-link to="/">
        <a-button type="primary">返回首页</a-button>
      </router-link>
    </template>
  </a-result>
</template>
<script lang="ts" setup>
</script>
