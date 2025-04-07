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

package io.xianzhi.linke.bootstrap.controller;

import io.xianzhi.core.enums.EnvEnum;
import io.xianzhi.core.result.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 环境接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/l/env")
public class EnvController {

    /**
     * 获取环境信息
     *
     * @return 环境信息
     */
    @GetMapping(value = "/getEnv")
    public ResponseResult<Map<String, String>> getEnv() {
        Map<String, String> result = new HashMap<>();
        EnvEnum[] values = EnvEnum.values();
        for (EnvEnum value : values) {
            result.put(value.getCode(), value.getDesc());
        }
        return ResponseResult.success(result);
    }

}
