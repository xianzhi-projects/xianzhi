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
import type {UserPage, UserVO} from "@/types/system/user.ts";
import type {DepartmentVO} from "@/types/system/department.ts";
import {getDepartmentTree} from "@/api/system/departmentApi.ts";
import {pageUserList} from "@/api/system/userApi.ts";

export const dialogFormVisible = ref(false)
export const userList = ref<UserVO[]>()
export const loading = ref(false)

export const departmentList = ref<DepartmentVO[]>()
export const total = ref(0);
export const selectedNode = ref<DepartmentVO>({
  id: '',
  departmentName: '',
  departmentDesc: '',
  departmentSort: 0,
  children: []
})
export const userPage = ref<UserPage>({
  pageNo: 1,
  pageSize: 10,
  departmentId: ''
});

export const onSelect = (data: DepartmentVO) => {
  selectedNode.value = data

}

export async function refreshUserList(userPage: UserPage) {
  loading.value = true
  try {
    const  rep = await pageUserList(userPage);
    if (rep.code === '200' && rep.data) {
      userList.value = rep.data.list;
      total.value = rep.data.total;
    }else{
      userList.value = [];
    }
  }finally {
    loading.value = false
  }


}



export async function refreshDepartmentList() {
  const rep = await getDepartmentTree();
  if (rep.code === '200' && rep.data) {
    departmentList.value = rep.data;
    if (selectedNode.value.id === '') {
      selectedNode.value = rep.data[0];
      await refreshUserList(userPage.value);

    }
  } else {
    departmentList.value = [];
  }
}


export const append = (data: UserVO) => {

}
