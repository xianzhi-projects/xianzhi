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
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
public class BusinessException extends RuntimeException {


    /**
     * 响应结果
     */
    private final Result result;


    /**
     * Constructs a new runtime exception with {@code null} as its detail message. The
     * cause is not initialized, and may subsequently be initialized by a call to
     * {@link #initCause}.
     */
    public BusinessException(Result result) {
        super(result.message());
        this.result = result;
    }


    public BusinessException(String message) {
        super(message);
        this.result = new Result() {
            @Override
            public String code() {
                return "500";
            }

            @Override
            public String message() {
                return message;
            }
        };
    }


    public BusinessException(String code, String message) {
        super(message);
        this.result = new Result() {
            @Override
            public String code() {
                return code;
            }

            @Override
            public String message() {
                return message;
            }
        };
    }
}
