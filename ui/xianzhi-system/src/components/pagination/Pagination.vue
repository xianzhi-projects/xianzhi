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

// src/components/Pagination.vue
<template>
  <el-pagination
    v-model:current-page="page.pageNo"
    v-model:page-size="page.pageSize"
    :page-sizes="[10, 20, 50, 100]"
    :total="page.totalItems"
    layout="total, sizes, prev, pager, next, jumper"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
  />
</template>

<script lang="ts" setup>
import {usePagination} from '@/components/pagination/index.ts';

const {page, setPageSize, setCurrentPageNo} = usePagination();

const handleSizeChange = (size: number) => {
  setPageSize(size);
};

const handleCurrentChange = (pageNo: number) => {
  setCurrentPageNo(pageNo);
};
</script>
