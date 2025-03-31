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
import {ElMessage} from "element-plus";
import {getCurrentUserInfo} from "@/api/system/userApi.ts";
import {ref} from "vue";
import type {UserVO} from "@/types/system/user.ts";

export const userInfo = ref<UserVO>(<UserVO>{
  id: '',
  username: '',
  email: '',
  phone: '',
  userStatus: '',
  nickName: '',
  realName: '',
  avatar: 'http://pic.imeitou.com/uploads/allimg/2020073010/qb5ekx2qajk.jpg',
  workNumber: '',
  department: {}

})



export async function refreshUserInfo() {
  const rep = await getCurrentUserInfo();
  if (rep.code === '200' && rep.data) {
    userInfo.value = rep.data
    console.log(userInfo.value)
  } else {
    ElMessage.error(rep.message)
  }
}



