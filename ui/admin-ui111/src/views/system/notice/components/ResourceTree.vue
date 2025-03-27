<template>
  <a-card style="width: 100%;height: 1000px" title="">
    <template #extra>
      <a-button type="primary">新增菜单</a-button>
    </template>
    <TreeComponent/>
  </a-card>
</template>

<script lang="ts" setup>
import TreeComponent from "@/components/TreeComponent.vue";
import {onMounted} from "vue";

onMounted(() => {
  initTreeData()
  // 默认展开第一层
  autoExpandParent.value = false
})
</script>


<style scoped></style>
