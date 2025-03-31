/*
 *  Copyright 2025 XianZhi Group .
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
import {ref} from 'vue';
import type {ResourceVO} from "@/types/system/resource.ts";
import {ResourceType} from "@/types/system/resource.ts";
import {deleteResource, resourceTree} from "@/api/system/resourceApi.ts";
import {ElMessage} from "element-plus";


export const dataSource = ref<ResourceVO[] | null>();

export const selectedNode = ref<ResourceVO>({
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
  children: [],
});

export const onSelect = (data: ResourceVO, t1: any) => {
  selectedNode.value = data
  selectedNode.value.parentId = t1.parent.data.id
  selectedNode.value.parent = t1.parent.data
}
export const defaultNodeKey = ref('')

/**
 * 刷新资源树
 */
export async function refreshResourceTree() {
  const rep = await resourceTree();
  if (rep.code === '200') {
    dataSource.value = rep.data
    if (dataSource.value && dataSource.value.length > 0) {
      defaultNodeKey.value = dataSource.value[0].id;
      console.log('defaultNodeKey', defaultNodeKey.value);
    }
  } else {
    ElMessage.error(rep.message)
    dataSource.value = []
  }
}


export const append = (data: ResourceVO) => {
  if (data == null) {
    selectedNode.value = {}
  } else {
    selectedNode.value = {
      id: '',
      resourceName: '', // 菜单名称
      resourceType: ResourceType.MENU, // 类型
      resourceKey: '', // 路径
      menuComponent: '', // 组件
      menuIcon: '', // 图标
      parentId: data.id, // 上级菜单
      resourceSorted: 0, // 排序
      resourceDesc: '', // 描述
      showFlag: true,
      enableFlag: true,
      children: [],
      parent: data
    }
  }

}

export const remove = async (data: ResourceVO) => {
  const rep = await deleteResource(data.id)
  if (rep.code === '200') {
    ElMessage.success('删除成功')
    await refreshResourceTree()
  }else{
    ElMessage.error(rep.message)
  }
}

