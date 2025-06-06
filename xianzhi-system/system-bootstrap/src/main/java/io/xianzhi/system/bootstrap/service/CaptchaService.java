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

package io.xianzhi.system.bootstrap.service;

import io.xianzhi.system.model.vo.CaptchaVO;

/**
 * 验证码接口
 *
 * @author Max
 * @since 1.0.0
 */
public interface CaptchaService {
    /**
     * 获取登录验证码
     *
     * @return 登录验证码
     */
    CaptchaVO getPasswordLoginCaptcha();

    /**
     * 检查验证码
     *
     * @param captchaKey 验证码key
     * @param captcha    验证码内容
     */
    void checkCaptcha(String captchaKey, String captcha);

}
