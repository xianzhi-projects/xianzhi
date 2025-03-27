<template>
  <a-card v-if="resourceType === ResourceType.MENU">
    <a-table :columns="columns" :data-source="dataSource" />
    <a-button type="primary" @click="addPermission">添加</a-button>
  </a-card>
  <PermissionEdit v-model:isShowModal="isShowModal" v-model:resourceDTO="resourceDTO" />
</template>

<script lang="ts" setup>
import {ref, watch} from 'vue'
import PermissionEdit from '@/views/system/resource/components/PermissionEdit.vue'
import {type ResourceDTO, ResourceType} from '@/api/resourceApi.ts'
import {selectedNode} from '@/views/system/resource/resource'

const columns = [
  { title: '名称', dataIndex: 'name', key: 'name' },
  { title: '编码', dataIndex: 'code', key: 'code' },
  {
    title: '操作',
    key: 'action',
  },
]
const isShowModal = ref(false)
const resourceType = ref(ResourceType.MENU)
const dataSource = ref([])
const resourceDTO = ref<ResourceDTO>({
  id: '',
  resourceKey: '',
  resourceName: '',
  parentId: '',
  resourceType: '',
  resourceSort: 0,
  resourceDesc: '',
  enableFlag: true,
  menuIcon: '',
  menuComponent: '',
  showFlag: true,
})

// 监听选中的节点
watch(
  () => selectedNode.value,
  (newVal) => {
    console.log('selectedNode', newVal)
    resourceType.value = newVal.resourceType
  },
)

const addPermission = () => {
  console.log('1')
  isShowModal.value = true

  // 添加权限逻辑
}
</script>
