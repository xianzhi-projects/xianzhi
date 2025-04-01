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

import type {UserDTO, UserPage, UserVO} from "@/types/system/user.ts";
import type {ListResult, ResponseResult} from "@/types/core.ts";
import http from "@/api";
import {BASE_SERVER} from "@/constants/ServerConstant.ts";

/**
 * 获取当前用户资源信息
 */
export function getCurrentUserInfo(): Promise<ResponseResult<UserVO>> {
  return http.request({
    url: BASE_SERVER + '/user/getCurrentUserInfo',
    method: 'GET',
  })
}

/**
 * 分页查询用户信息
 * @param page 用户查询条件
 */
export function pageUserList(page: UserPage): Promise<ResponseResult<ListResult<UserVO>>> {
  return http.request({
    url: BASE_SERVER + '/user/pageUserList',
    method: 'POST',
  })
}

/**
 * 新增用户
 * @param user 用户信息入参
 */
export function createUser(user: UserDTO): Promise<ResponseResult<any>> {
  return http.request({
    url: BASE_SERVER + '/user/createUser',
    method: 'POST',
    data: user,
  })
}

/**
 * 修改用户
 * @param user 用户信息入参
 */
export function updateUser(user: UserDTO): Promise<ResponseResult<any>> {
  return http.request({
    url: BASE_SERVER + '/user/updateUser',
    method: 'POST',
    data: user,
  })
}

/**
 * 删除用户
 * @param id 用户id
 */
export function deleteUser(id: string[]): Promise<ResponseResult<any>> {
  return http.request({
    url: BASE_SERVER + '/user/deleteUser',
    method: 'POST',
    data: id,
  })
}


export function getUserDetails(userId: string): Promise<ResponseResult<UserVO>> {
  return http.request({
    url: BASE_SERVER + '/user/getUserDetails',
    method: 'GET',
    params: {'userId': userId}
  })
}
