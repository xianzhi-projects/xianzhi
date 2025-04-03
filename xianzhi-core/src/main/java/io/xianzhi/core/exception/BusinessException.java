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

package io.xianzhi.core.exception;

import io.xianzhi.core.result.Result;
import lombok.Getter;

/**
 * 自定义业务异常
 * Custom Business Exception
 * 该类定义了一个业务相关的运行时异常，用于抛出带有响应结果的异常信息。
 * This class defines a business-related runtime exception for throwing exception information with a response result.
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 响应结果
     * Response Result
     * 包含异常的状态码和提示信息。
     * Contains the status code and message of the exception.
     */
    private final Result result;

    /**
     * 构造函数
     * Constructor
     * 根据指定的响应结果构造一个业务异常，异常信息来源于结果的提示信息。
     * Constructs a business exception based on the specified response result, with the exception message derived from the result's message.
     *
     * @param result 响应结果 / Response result
     */
    public BusinessException(Result result) {
        super(result.message());
        this.result = result;
    }
}
