<script lang="ts" setup>
// 是否显示编进权限框
import {createResource} from '@/api/resourceApi.ts'

const isShowModal = defineModel('isShowModal', {
  type: Boolean,
  required: true,
})

const resourceDTO = defineModel('resourceDTO', {
  type: Object,
  required: true,
})

const handleOk = async () => {
  if (resourceDTO.value.id) {
    // 编辑权限
  } else {
    // 新增权限
    await createResource(resourceDTO.value)
  }
  isShowModal.value = false
}
</script>

<template>
  <!-- 弹框区域 -->
  <a-modal
    v-model:open="isShowModal"
    :maskClosable="false"
    :title="resourceDTO.id ? '编辑权限' : '新增权限'"
    cancelText="取消"
    centered
    okText="确定"
    width="600px"
    @ok="handleOk"
  >
    <a-form :label-col="{ span: 6 }" :model="resourceDTO" :wrapper-col="{ span: 14 }">
      <a-form-item :rules="[{ required: true, message: '请输入权限名称' }]" label="权限名称">
        <a-input v-model:value="resourceDTO.resourceName" />
      </a-form-item>
      <a-form-item :rules="[{ required: true, message: '请输入权限标识符' }]" label="权限标识符">
        <a-input v-model:value="resourceDTO.resourceKey" />
      </a-form-item>
      <a-form-item label="权限描述">
        <a-input v-model:value="resourceDTO.resourceDesc" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<style scoped></style>
