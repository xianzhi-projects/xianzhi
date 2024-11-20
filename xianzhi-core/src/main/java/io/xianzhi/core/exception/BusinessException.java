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
 * 自定义业务异常
 *
 * @author Max
 * @since 1.0.0
 */

/**
 * Custom Business Exception
 * <p>
 * Represents an exception that encapsulates business logic errors, carrying a
 * {@link Result} object to provide detailed information about the error.
 * <p>
 * This exception is a runtime exception and is used for propagating business-specific
 * error states within the application.
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * Encapsulated response result
     * <p>
     * Holds the {@link Result} object that provides the error code, success status,
     * and message associated with this exception.
     *
     * @since 1.0.0
     */
    private final Result result;

    /**
     * Constructs a new BusinessException with the specified {@link Result}.
     * <p>
     * The exception message is derived from the {@link Result#message()} method.
     *
     * @param result The {@link Result} object containing details of the error
     */
    public BusinessException(Result result) {
        super(result.message());
        this.result = result;
    }

}