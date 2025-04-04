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

import type {UserVO} from "@/types/user.ts";
import {ref} from 'vue';
import {resourceTree} from "@/api/resourceApi.ts";
import {ElMessage} from "element-plus";
import {dataSource, defaultNodeKey} from "@/views/system/resource/index.ts";


export const userList = ref<UserVO>()

export async function refreshUserList() {
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



export const append = (data: UserVO) => {

}
