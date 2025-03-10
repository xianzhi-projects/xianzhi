<template>
  <a-card title="菜单信息">
    <a-form
      :labelAlign="'right'"
      :labelCol="{ span: 3 }"
      :model="resource"
      :wrapperCol="{ span: 16 }"
      layout="horizontal"
    >
      <a-form-item label="上级菜单">
        <a-input v-model:value="parentName" disabled />
      </a-form-item>
      <a-form-item label="资源类型">
        <a-radio-group v-model:value="resource.resourceType">
          <a-radio value="MENU">菜单</a-radio>
          <a-radio value="CATALOG">目录</a-radio>
          <a-radio value="LINK">外链</a-radio>
        </a-radio-group>
      </a-form-item>

      <a-form-item label="名称">
        <a-input v-model:value="resource.resourceName" />
      </a-form-item>
      <a-form-item label="图标">
        <a-input v-model:value="resource.menuIcon" />
      </a-form-item>
      <a-form-item :label="resource.resourceType === ResourceType.LINK ? '外链地址' : '路径'">
        <a-input v-model:value="resource.resourceKey" />
      </a-form-item>
      <a-form-item v-if="resource.resourceType === ResourceType.MENU" label="组件">
        <a-input v-model:value="resource.menuComponent" />
      </a-form-item>
      <a-form-item label="排序">
        <a-input-number v-model:value="resource.menuSort" />
      </a-form-item>
      <a-form-item label="是否启用">
        <a-radio-group v-model:value="resource.enableFlag">
          <a-radio :value="true">是</a-radio>
          <a-radio :value="false">否</a-radio>
        </a-radio-group>
      </a-form-item>
      <a-form-item label="是否显示">
        <a-radio-group v-model:value="resource.showFlag">
          <a-radio :value="true">是</a-radio>
          <a-radio :value="false">否</a-radio>
        </a-radio-group>
      </a-form-item>
      <a-form-item label="描述">
        <a-textarea v-model:value="resource.resourceDesc" :maxlength="255" style="height: 100px" />
      </a-form-item>
      <a-form-item>
        <a-button :loading="loading" type="primary" @click="submitForm">提交</a-button>
      </a-form-item>
    </a-form>
  </a-card>
</template>

<script lang="ts" setup>
import {ref, watch} from 'vue'
import {initTreeData, selectedNode} from '@/views/system/resource/resource'
import {createResource, type ResourceDTO, ResourceType, updateResource,} from '@/api/resourceApi.ts'

const loading = ref(false)
const parentName = ref('')
const resource = ref<ResourceDTO>({
  id: '',
  resourceName: '', // 菜单名称
  resourceType: ResourceType.MENU, // 类型
  resourceKey: '', // 路径
  menuComponent: '', // 组件
  menuIcon: '', // 图标
  parentId: '', // 上级菜单
  resourceSort: 0, // 排序
  resourceDesc: '', // 描述
  showFlag: true,
  enableFlag: true,
})

// 监听选中的节点
watch(
  () => selectedNode.value,
  (newVal) => {
    resource.value = {
      id: newVal.key,
      resourceName: newVal.title, // 菜单名称
      resourceType: newVal.resourceType || 'MENU', // 类型
      resourceKey: newVal.resourceKey, // 路径
      menuComponent: newVal.menuComponent, // 组件
      menuIcon: newVal.menuIcon, // 图标
      resourceSort: newVal.menuSort, // 排序
      resourceDesc: newVal.resourceDesc, // 描述
      showFlag: newVal.showFlag || true,
      enableFlag: newVal.enableFlag || true,
    }
    if (newVal.parent && newVal.parent.node) {
      resource.value.parentId = newVal.parent.node.key
      parentName.value = newVal.parent.node.title
    }
  },
)

const submitForm = async () => {
  loading.value = true
  try {
    let result: null
    if (resource.value.id) {
      result = await updateResource(resource.value)
    } else {
      // 提交表单逻辑
      result = await createResource(resource.value)
    }
    if (result.success && result.code === '200') {
      // 新增修改后刷新树
      await initTreeData()
      return
    }
  } finally {
    loading.value = false
  }
}
</script>
<style scoped></style>
