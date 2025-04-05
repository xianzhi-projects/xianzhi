<script lang="ts" setup>
import {
  dialogFormVisible,
  loading,
  refreshDepartmentList,
  refreshUserList,
  selectedNode,
  total,
  userList,
  userPage
} from "@/views/system/user/index.ts";
import {onBeforeMount, watch} from "vue";
import UserEdit from "@/views/system/user/UserEdit.vue";


onBeforeMount(() => {
  refreshDepartmentList();
  refreshUserList(userPage.value);
});

// 监听选中的节点
watch(
  () => selectedNode.value,
  (newVal) => {
    console.log('选中的节点', newVal);
    userPage.value.departmentId = newVal?.id;
    userPage.value.pageNo = 1; // 切换部门时重置页码
    refreshUserList(userPage.value);
  }
);

// 页码改变时触发
const handleCurrentChange = (newPage: number) => {
  userPage.value.pageNo = newPage;
  refreshUserList(userPage.value);
};

// 每页条数改变时触发
const handleSizeChange = (newSize: number) => {
  userPage.value.pageSize = newSize;
  userPage.value.pageNo = 1; // 改变每页条数时重置页码
  refreshUserList(userPage.value);
};
</script>

<template>
  <div class="user-list h-100">
    <el-card class="h-100" header="用户管理">
      <template #header>
        <div class="card-header">
          <span>用户列表</span>
          <div>
            <el-button-group>
              <el-button  type="primary" @click="dialogFormVisible = true">添加用户</el-button>
              <el-button type="primary">批量删除</el-button>
            </el-button-group>
          </div>
        </div>
      </template>
      <div class="table-container">
        <el-table v-loading="loading" :data="userList" :empty-text="'暂无数据'" style="width: 100%;">
          <el-table-column label="头像" prop="avatar">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <el-avatar :size="40" :src="scope.row.avatar" />
              </div>
            </template>
          </el-table-column>
          <el-table-column label="用户名" prop="username" />
          <el-table-column label="昵称" prop="nickName" />
          <el-table-column label="工号" prop="workNumber" />
          <el-table-column label="真实姓名" prop="realName" />
          <el-table-column label="邮箱地址" prop="email" />
          <el-table-column label="手机号码" prop="phone" />
          <el-table-column label="所属部门" prop="department.departmentName" />
          <el-table-column fixed="right" label="操作">
            <template #default>
              <el-button link size="small" type="primary">Edit</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 添加分页组件 -->
        <div class="pagination-container">
          <el-pagination
            v-model:current-page="userPage.pageNo"
            v-model:page-size="userPage.pageSize"
            :page-sizes="[10, 20, 50, 100]"
            :total="total"
            background
            layout="total, sizes, prev, pager, next, jumper"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
          />
        </div>
      </div>
    </el-card>
  </div>
  <UserEdit />
</template>

<style lang="scss" scoped>
.user-list {
  .card-header {
    display: flex;
    justify-content: space-between;
  }

  :deep(.el-card) {
    display: flex;
    flex-direction: column;
    height: 100%;
  }

  :deep(.el-card__header) {
    flex-shrink: 0;
  }

  :deep(.el-card__body) {
    flex: 1;
    display: flex;
    flex-direction: column;
    padding: 0;
  }

  .table-container {
    flex: 1;
    overflow: auto;
    display: flex;
    flex-direction: column;
  }

  .el-table {
    width: 100%;
    flex: 1;
  }

  .pagination-container {
    padding: 10px;
    text-align: right;
    flex-shrink: 0;
  }
}
</style>
