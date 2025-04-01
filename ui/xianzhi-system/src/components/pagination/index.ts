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

export function usePagination(initialPageNo = 1, initialPageSize = 10) {
  // 定义分页状态
  const page = ref({
    pageNo: initialPageNo,     // 当前页码
    pageSize: initialPageSize, // 每页条目数
    totalItems: 0,            // 总条目数
  });

  // 更新当前页码
  const setCurrentPageNo = (pageNo: number) => {
    page.value.pageNo = pageNo;
  };

  // 更新每页条目数
  const setPageSize = (pageSize: number) => {
    page.value.pageSize = pageSize;
  };

  // 更新总条目数
  const setTotalItems = (totalItems: number) => {
    page.value.totalItems = totalItems;
  };

  // 返回状态和操作函数
  return {
    page,
    setCurrentPageNo,
    setPageSize,
    setTotalItems,
  };
}

