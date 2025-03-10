<template>
  <a-card class="card">
    <a-flex gap="middle" vertical>
      <a-flex :gap="100">
        <a-input v-model:value="searchValue" placeholder="搜索菜单...." style="{width: 80%}" />
        <a-button type="primary" @click="onCreateBaseNode">新增根节点</a-button>
      </a-flex>
    </a-flex>
    <a-tree
      :auto-expand-parent="autoExpandParent"
      :expanded-keys="expandedKeys"
      :selected-keys="selectedKeys"
      :tree-data="filteredTreeData"
      default-expand-all
      show-icon
      @expand="onExpand"
      @select="onSelect"
    >
      <template #switcherIcon="{ switcherCls }">
        <down-outlined :class="switcherCls" />
      </template>
      <template #title="{ data: data }">
        <span>{{ data.title }}</span>
        <a-space style="float: right">
          <a-button size="small" type="primary" @click.stop="() => addResource(data)"
            >新增</a-button
          >
          <a-button size="small" type="primary" @click.stop="() => addResource(data)"
            >删除</a-button
          >
        </a-space>
      </template>
    </a-tree>
  </a-card>
</template>

<script lang="ts" setup>
import {computed, onMounted, ref} from 'vue'
import {DownOutlined} from '@ant-design/icons-vue'
import {initTreeData, selectedKeys, selectedNode, treeData,} from '@/views/system/resource/resource'
import type {TreeDataNode} from 'ant-design-vue/es/vc-tree-select/interface'

const searchValue = ref<string>('')
const expandedKeys = ref<(string | number)[]>([])
const autoExpandParent = ref<boolean>(true)

// 组件加载的时候初始化树形数据
onMounted(() => {
  initTreeData()
  // 默认展开第一层
  autoExpandParent.value = false
})
// 选中节点
const onSelect = (keys: (string | number)[], event: any) => {
  selectedNode.value = event.node
  selectedKeys.value = keys
}
// 新增根节点
const onCreateBaseNode = () => {
  selectedNode.value = {}
}
// 展开节点
const onExpand = (keys: (string | number)[]) => {
  expandedKeys.value = keys
}
// 计算属性：根据搜索值过滤树数据
const filteredTreeData = computed(() => {
  if (!searchValue.value) return treeData.value
  const filterTree = (nodes: TreeDataNode[]): TreeDataNode[] => {
    return nodes
      .map((node) => ({
        ...node,
        children: node.children ? filterTree(node.children) : undefined,
      }))
      .filter(
        (node) =>
          node.title.includes(searchValue.value) ||
          (node.children && node.children.some((child) => child.title.includes(searchValue.value))),
      )
  }

  return filterTree(treeData.value)
})

const addResource = (event: any) => {
  selectedKeys.value = [event.key]
  selectedNode.value = {
    parent: {
      node: {
        key: event.key,
        title: event.title,
      },
    },
  }
}
</script>
<style lang="less" scoped>
.ant-card {
  height: 100%;
  display: flex;
  flex-direction: column;

  :deep(.ant-card-body) {
    flex: 1;
    padding: 16px;
    display: flex;
    flex-direction: column;
  }

  // 搜索区域优化
  .ant-flex:first-child {
    margin-bottom: 12px;

    .ant-input {
      flex: 1;
      margin-right: 16px;
    }

    .ant-btn {
      flex-shrink: 0;
    }
  }

  // 树形控件优化
  :deep(.ant-tree) {
    flex: 1;
    overflow: auto;
    font-size: 14px;

    .ant-tree-treenode {
      align-items: center;
      padding: 8px 0;
      width: 100%;

      &:hover {
        background: #f5f5f5;

        .ant-tree-node-content-wrapper {
          background: transparent;
        }
      }

      .ant-tree-node-content-wrapper {
        flex: 1;
        display: flex;
        align-items: center;
        padding-left: 8px;
        transition: none;

        .ant-tree-title {
          flex: 1;
          display: flex;
          justify-content: space-between;
          align-items: center;

          > span {
            overflow: hidden;
            text-overflow: ellipsis;
          }

          .ant-space {
            padding-left: 16px;
            flex-shrink: 0;

            .ant-btn {
              font-size: 12px;
              padding: 0 8px;
              height: 24px;
            }
          }
        }
      }
    }

    // 展开图标优化
    .ant-tree-switcher {
      color: #666;
      .anticon {
        font-size: 12px;
      }
    }
  }

  // 选中状态优化
  :deep(.ant-tree-node-selected) {
    background: #e6f4ff !important;
    .ant-tree-node-content-wrapper {
      background: transparent !important;
    }
  }
}
</style>
