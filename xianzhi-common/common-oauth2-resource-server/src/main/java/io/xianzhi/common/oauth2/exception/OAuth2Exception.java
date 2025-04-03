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

package io.xianzhi.common.oauth2.exception;

import io.xianzhi.core.result.Result;
import lombok.Getter;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

/**
 * OAuth2相关异常
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
public class OAuth2Exception extends OAuth2AuthenticationException {

    /**
     * 响应结果
     */
    private final transient Result result;

    /**
     * 构造方法
     *
     * @param result 响应结果
     */
    public OAuth2Exception(Result result) {
        super(result.code());
        this.result = result;
    }
}