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

package io.xianzhi.system.bootstrap.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.GifCaptcha;
import io.xianzhi.common.redis.RedisHandler;
import io.xianzhi.core.exception.BusinessException;
import io.xianzhi.system.bootstrap.service.CaptchaService;
import io.xianzhi.system.model.vo.CaptchaVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 验证码接口实现
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CaptchaServiceImpl implements CaptchaService {

    /**
     * 缓存key
     */
    private final static String CACHE_KEY = "captcha:%s";
    /**
     * 缓存处理
     */
    private final RedisHandler redisHandler;

    /**
     * 获取登录验证码
     *
     * @return 登录验证码
     */
    @Override
    public CaptchaVO getLoginCaptcha() {
        String key = UUID.randomUUID().toString().replace("-", "");
        GifCaptcha gifCaptcha = CaptchaUtil.createGifCaptcha(300, 100, 6);
        String code = gifCaptcha.getCode();
        redisHandler.vSet(String.format(CACHE_KEY, key), code, 5L, TimeUnit.MINUTES);
        CaptchaVO vo = new CaptchaVO();
        vo.setKey(key);
        vo.setImage(gifCaptcha.getImageBase64Data());
        return vo;
    }

    /**
     * 检查验证码
     *
     * @param captchaKey 验证码key
     * @param captcha    验证码内容
     */
    @Override
    public void checkCaptcha(String captchaKey, String captcha) {
        String key = String.format(CACHE_KEY, captchaKey);
        String code = redisHandler.vGet(key, String.class);
        if (!StringUtils.hasText(code) || !captcha.equalsIgnoreCase(code)) {
            redisHandler.delete(key);
            throw new BusinessException("验证码错误");
        }
        redisHandler.delete(key);
    }
}
