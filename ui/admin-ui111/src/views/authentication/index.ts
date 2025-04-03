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

import type {Rule} from 'ant-design-vue/es/form'


const loginRules: Record<string, Rule[]> = {
  username: [
    {required: true, message: '请输入用户名', trigger: 'change'},
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'change'},

  ],
  captcha: [
    {required: true, message: '请输入验证码', trigger: 'change'},
  ],
}
export default loginRules
