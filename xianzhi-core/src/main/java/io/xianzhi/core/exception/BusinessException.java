/*
 *  Copyright 2024 XianZhi Group
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
 *
 */

package io.xianzhi.core.exception;

import io.xianzhi.core.result.Result;
import lombok.Getter;


/**
 * 自定义业务异常，通常是指应用程序抛出的自定义业务异常
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 封装响应结果
     *
     * @since 1.0.0
     */
    private final Result result;

    /**
     * 使用指定的｛@link Result｝构造新的BusinessException。
     * 异常消息来源于{@link Result#message（）}方法。
     *
     * @param result 包含错误详细信息的{@link Result}对象
     * @since 1.0.0
     */
    public BusinessException(Result result) {
        super(result.message());
        this.result = result;
    }

}