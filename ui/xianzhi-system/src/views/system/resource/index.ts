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
import {ref} from 'vue'
import type {ResourceVO} from "@/types/resource.ts";
import {ResourceType} from "@/types/resource.ts";
import {resourceTree} from "@/api/resourceApi.ts";
import {ElMessage} from "element-plus";
import type Node from "element-plus/es/components/tree/src/model/node";


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

export const onSelect = (data: ResourceVO,t1: any) => {
  selectedNode.value = data
  selectedNode.value.parentId = t1.parent.data.id
  selectedNode.value.parentName = t1.parent.data.resourceName

}

export const append = (data: ResourceVO) => {
  console.log('append', data)

}

export const remove = (node: Node, data: ResourceVO) => {
}

/**
 * 刷新资源树
 */
export async function refreshResourceTree() {
  const rep = await resourceTree();
  if (rep.code === '200') {
    dataSource.value =  rep.data
  } else {
    ElMessage.error(rep.message)
    dataSource.value = []
  }
}

