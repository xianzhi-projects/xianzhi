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


import type {Page} from "@/types/core.ts";
import type {DepartmentVO} from "@/types/system/department.ts";
import type {PostVO} from "@/types/system/post.ts";

/**
 * 用户信息
 */
export interface UserVO {
  /**
   * 用户ID
   */
  id: string
  /**
   * 用户名
   */
  username: string
  /**
   * 用户昵称
   */
  nickName: string
  /**
   * 用户真实姓名
   */
  realName: string
  /**
   * 邮箱地址
   */
  email: string
  /**
   * 手机号码
   */
  phone: string

  /**
   * 用户头像
   */
  avatar: string
  /**
   * 用户状态
   */
  userStatus: string
  /**
   * 用户工号
   */
  workNumber: string

  /**
   * 部门信息
   */
  department: DepartmentVO

  /**
   * 岗位信息
   */
  posts: PostVO[]


}


export interface UserDTO {
  id: string
  username: string
  password: string
  email: string
  phone: string
  status: number
  createTime: string
  updateTime: string
}


export interface UserPage extends Page {
  username: string
  nickName: string
  realName: string
  email: string
  phone: string
  userStatus: number
  department: string

}

