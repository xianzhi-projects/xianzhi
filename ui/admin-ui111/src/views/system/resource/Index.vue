<script lang="ts" setup>
import ResourceTree from '@/views/system/resource/components/ResourceTree.vue'
import ResourceEdit from '@/views/system/resource/components/ResourceEdit.vue'
import TreeComponent from "@/components/TreeComponent.vue";
</script>
<template>
  <a-row :gutter="16" style="margin-left: 0; margin-right: 0">
    <a-col :span="6">
      <ResourceTree/>
    </a-col>
    <a-col :span="10">
      <ResourceEdit/>
    </a-col>
    <a-col :span="8">
      <a-card style="width: 100%;height: 1000px" title="">
        <TreeComponent/>
      </a-card>
    </a-col>
  </a-row>
</template>

<style scoped>

</style>
