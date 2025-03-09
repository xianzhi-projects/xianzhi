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
import http, {type ResponseResult} from '@/api/index'
import {useUserStore} from "@/stores/userStore.ts";

/**
 * token信息出参
 */
export interface TokenVO {
  /**
   * 用户ID
   */
  id: string
  /**
   * 用户头像
   */
  avatar: string
  /**
   * 用户昵称
   */
  nickName: string
  /**
   * 用户邮箱
   */
  accessToken: string
  /**
   * 刷新token
   */
  refreshToken: string
  /**
   * 用户工号
   */
  workNumber: string
  /**
   * 域账号
   */
  domainAccount: string
  /**
   * 真实姓名
   */
  realName: string
}

/**
 * 密码登录入参
 */
export interface PasswordLoginDTO {
  /**
   * 登录账号  手机号码/邮箱/工号/域账号
   */
  username: string
  /**
   * 密码
   */
  password: string
  /**
   * 记住我
   */
  remember: boolean
  /**
   * 验证码输入的值
   */
  captcha: string
  /**
   * 验证码唯一标识
   */
  captchaKey: string
}

/**
 * 刷新token
 * @param refreshToken 刷新token
 */
export async function refreshToken(refreshToken: string): Promise<ResponseResult<TokenVO>> {
  return http.request({
    url: '/oauth2/token',
    method: 'POST',
    params: {grant_type: 'refresh_token', refresh_token: refreshToken},
    headers: {
      Authorization: 'Basic ' + window.btoa('xianzhi:xianzhi'),
    },
  })
}

/**
 * 密码登录
 * @param loginDTO 登录入参
 */
export async function passwordLogin(loginDTO: PasswordLoginDTO): Promise<ResponseResult<TokenVO>> {
  useUserStore().removeUser()
  return http.request({
    url: '/oauth2/token',
    method: 'POST',
    params: {grant_type: 'password'},
    data: loginDTO,
    headers: {
      Authorization: 'Basic ' + window.btoa('xianzhi:xianzhi'),
    },
  })
}


