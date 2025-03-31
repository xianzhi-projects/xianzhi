<!--
  -  Copyright 2025 XianZhi Group .
  -
  -  Licensed under the Apache License, Version 2.0 (the "License");
  -  you may not use this file except in compliance with the License.
  -  You may obtain a copy of the License at
  -
  -      http://www.apache.org/licenses/LICENSE-2.0
  -
  -  Unless required by applicable law or agreed to in writing, software
  -  distributed under the License is distributed on an "AS IS" BASIS,
  -  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  -  See the License for the specific language governing permissions and
  -  limitations under the License.
  -->

<template>
  <el-card class="box-card" title="菜单信息">
    <el-form
      :labelAlign="'right'"
      :labelCol="{ span: 3 }"
      :model="resource"
      :wrapperCol="{ span: 16 }"
      layout="horizontal"
    >
      <el-form-item label="上级菜单">
        <el-input v-model="parentName" disabled/>
      </el-form-item>
      <el-form-item label="资源类型">
        <el-radio-group v-model="resource.resourceType">
          <el-radio value="MENU">菜单</el-radio>
          <el-radio value="CATALOG">目录</el-radio>
          <el-radio value="LINK">外链</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="名称">
        <el-input v-model="resource.resourceName"/>
      </el-form-item>
      <el-form-item label="图标">
        <el-input v-model="resource.menuIcon"/>
      </el-form-item>
      <el-form-item :label="resource.resourceType === ResourceType.LINK ? '外链地址' : '路径'">
        <el-input v-model="resource.resourceKey"/>
      </el-form-item>
      <el-form-item v-if="resource.resourceType === ResourceType.MENU" label="组件">
        <el-input v-model="resource.menuComponent"/>
      </el-form-item>
      <el-form-item label="排序">
        <el-input-number v-model="resource.resourceSorted" :max="100" :min="0"/>
      </el-form-item>
      <el-form-item label="是否启用">
        <el-radio-group v-model="resource.enableFlag">
          <el-radio :value="true">是</el-radio>
          <el-radio :value="false">否</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="是否显示">
        <el-radio-group v-model="resource.showFlag">
          <el-radio :value="true">是</el-radio>
          <el-radio :value="false">否</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="描述">
        <el-input
          v-model="resource.resourceDesc"
          :autosize="{ minRows: 10, maxRows: 255 }"
          placeholder="Please input"
          style="width: 100%"
          type="textarea"
        />
      </el-form-item>
      <el-form-item>
        <el-button :loading="loading" type="primary" @click="submitForm">提交</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script lang="ts" setup>
import {ref, watch} from 'vue'
import {createResource, updateResource,} from '@/api/system/resourceApi.ts'
import {type ResourceDTO, ResourceType} from "@/types/system/resource.ts";
import {refreshResourceTree, selectedNode} from "@/views/system/resource/index.ts";
import {ElMessage} from "element-plus";

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
  resourceSorted: 0, // 排序
  resourceDesc: '', // 描述
  showFlag: true,
  enableFlag: true,
})

// 监听选中的节点
watch(() => selectedNode.value, (newVal) => {
    resource.value = {
      id: newVal.id,
      resourceName: newVal?.resourceName, // 菜单名称
      resourceType: newVal?.resourceType || 'MENU', // 类型
      resourceKey: newVal.resourceKey, // 路径
      menuComponent: newVal.menuComponent, // 组件
      menuIcon: newVal.menuIcon, // 图标
      resourceSorted: newVal?.resourceSorted, // 排序
      resourceDesc: newVal.resourceDesc, // 描述
      showFlag: newVal.showFlag || true,
      enableFlag: newVal.enableFlag || true,
      parentId: newVal?.parentId || '', // 上级菜单
    }
    if (newVal?.parent) {
      parentName.value = newVal?.parent.resourceName || ''
    }

  },
)

const submitForm = async () => {
  loading.value = true
  console.log(resource.value)
  try {
    let result: null
    if (resource.value.id) {
      result = await updateResource(resource.value)
    } else {
      // 提交表单逻辑
      result = await createResource(resource.value)
    }
    if (result.code === '200') {
      ElMessage.success('操作成功')
      // 新增修改后刷新树
      await refreshResourceTree()

      return
    }
  } finally {
    loading.value = false
  }
}
</script>
<style scoped>
.box-card{
  min-height: 800px;
}
</style>

