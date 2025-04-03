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
 * Custom Business Exception
 * This class represents a custom runtime exception specifically designed for business logic errors.
 * It extends the standard RuntimeException class and is used to encapsulate and throw exceptions
 * that include a detailed response result, providing a structured way to handle business-related errors.
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * Response Result
     * This field holds the result object associated with the exception. The result object typically
     * contains detailed information about the error, such as a status code (indicating the type
     * of error) and a human-readable message (describing the error in detail). This allows the
     * exception to provide meaningful feedback to the caller or end user.
     */
    private final Result result;

    /**
     * Constructor
     * This constructor initializes a new instance of BusinessException with a specified response
     * result. The exception's message, which is passed to the superclass (RuntimeException), is
     * extracted from the provided result object using its message() method. This ensures that
     * the exception carries both the detailed result and a concise error message.
     *
     * @param result The response result object that contains the status code and error message
     *               to be associated with this exception. It must not be null, as it provides
     *               the core information for the exception.
     */
    public BusinessException(Result result) {
        super(result.message());
        this.result = result;
    }


    /**
     * Constructor with Code and Message
     * This constructor initializes a BusinessException instance with a specific status code and
     * error message. It creates an anonymous Result object that encapsulates the provided code
     * and message, allowing the exception to carry structured response information. The message
     * is passed to the superclass (RuntimeException) for standard exception handling, while the
     * Result object provides additional context for error reporting.
     *
     * @param code    The custom status code representing the type of error.
     * @param message The human-readable error message describing the exception.
     */
    public BusinessException(String code, String message) {
        super(message); // Pass the message to RuntimeException's constructor
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
