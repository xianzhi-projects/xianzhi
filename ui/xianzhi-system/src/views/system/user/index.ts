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


export const userList = ref<UserVO[]>()


export const departmentList = ref<DepartmentVO[]>()

export const selectedNode = ref<DepartmentVO>({
  id: '',
  departmentName: '',
  departmentDesc: '',
  departmentSort: 0,
  children: []
})

export const onSelect = (data: DepartmentVO) => {
  selectedNode.value = data

}

export async function refreshUserList(userPage: UserPage) {
  const  rep = await pageUserList(userPage);
  if (rep.code === '200' && rep.data) {
    userList.value = rep.data.list;
  }else{
    userList.value = [];
  }

}

export async function refreshDepartmentList() {
  const rep = await getDepartmentTree();
  if (rep.code === '200' && rep.data) {
    departmentList.value = rep.data;
  } else {
    departmentList.value = [];
  }
}


export const append = (data: UserVO) => {

}
