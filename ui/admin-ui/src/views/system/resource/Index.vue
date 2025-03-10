<script lang="ts" setup>
import ResourceTree from '@/views/system/resource/components/ResourceTree.vue'
import ResourceEdit from '@/views/system/resource/components/ResourceEdit.vue'
import PermissionList from '@/views/system/resource/components/PermissionList.vue'
</script>
<template>
  <a-row :gutter="16" style="margin-left: 0; margin-right: 0">
    <a-col :span="6">
      <ResourceTree />
    </a-col>
    <a-col :span="10">
      <ResourceEdit />
    </a-col>
    <a-col :span="8">
      <PermissionList />
    </a-col>
  </a-row>
</template>

<style scoped></style>
