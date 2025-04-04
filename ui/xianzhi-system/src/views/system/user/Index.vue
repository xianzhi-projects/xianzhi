<!-- 父组件 -->
<script lang="ts" setup>
import {departmentList, onSelect, selectedNode} from "@/views/system/user/index.ts";
import UserSearch from "@/views/system/user/UserSearch.vue";
import UserList from "@/views/system/user/UserList.vue";
</script>

<template>
  <div class="container cw">
    <el-row :gutter="16" class="h-100">
      <el-col :span="3">
        <el-card class="h-100" header="部门信息">
          <el-tree
            :data="departmentList"
            :default-expand-all="true"
            :expand-on-click-node="false"
            :highlight-current="true"
            :current-node-key="selectedNode?.id"
            :empty-text="'部门信息为空'"
            :props="{ label: 'departmentName', children: 'children' }"
            node-key="id"
            @node-click="onSelect"
          />
        </el-card>
      </el-col>
      <el-col :span="21">
        <div class="right-panel">
          <UserSearch />
          <UserList />
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<style lang="less" scoped>

.right-panel {
  display: flex;
  flex-direction: column;
  height: 100%; /* 占满父容器高度 */
}

.right-panel > .user-search {
  flex-shrink: 0; /* 搜索表单不压缩 */
}

.right-panel > .user-list {
  flex: 1; /* 表格占满剩余空间 */
  overflow: auto; /* 如果内容溢出，显示滚动条 */
}
</style>
