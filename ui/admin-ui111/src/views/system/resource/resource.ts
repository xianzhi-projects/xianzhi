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

import {resourceTree, type ResourceVO} from '@/api/resourceApi.ts'
import * as antIcons from '@ant-design/icons-vue'
import {h, ref} from 'vue'
import {message} from 'ant-design-vue'
import type {TreeDataNode} from 'ant-design-vue/es/vc-tree-select/interface'

// 资源树
const treeData = ref<TreeDataNode[]>([])
// 选择的节点
const selectedNode = ref({})
// 选择的节点
const selectedKeys = ref<(string | number)[]>([])

export async function initTreeData() {
  const response = await resourceTree()
  if (response.data) {
    treeData.value = response.data.map((item: ResourceVO) => convertTree(item))
    selectedNode.value = treeData.value[0]
    // 默认选中第一个节点
    selectedKeys.value.push(treeData.value[0].key)
    return
  }
  message.error('获取资源树失败')
}

// 获取所有图标的键名
type AntdIconNames = keyof typeof antIcons

function convertTree(resource: ResourceVO): TreeDataNode {
  return {
    key: resource.id,
    id: resource.id,
    blockNode: true,
    autoExpandParent: true,
    title: resource.resourceName,
    icon: resource.menuIcon ? () => h(antIcons[resource.menuIcon as AntdIconNames]) : undefined,
    resourceType: resource.resourceType,
    menuIcon: resource.menuIcon,
    resourceKey: resource.resourceKey,
    menuComponent: resource.menuComponent,
    resourceSort: resource.resourceSorted,
    resourceDesc: resource.resourceDesc,
    showFlag: resource.showFlag,
    children: resource.children?.map((child) => convertTree(child)),
  }
}

export {treeData, selectedNode, selectedKeys}
