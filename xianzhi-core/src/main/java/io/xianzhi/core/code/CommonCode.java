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

package io.xianzhi.core.code;

import io.xianzhi.core.result.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Common Response Status Codes
 * <p>
 * This enum defines the common status codes used across the application for indicating
 * the outcome of operations, such as success, failure, or errors. These codes are
 * non-HTTP and are used for custom application logic.
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum CommonCode implements Result {


    /**
     * Parameter check failure.
     * Indicates that the operation failed due to invalid parameters.
     *
     * @since 1.0.0
     */
    PARAMS_CHECK_FAIL("0000000", false, "params.check.fail"),


    /**
     * Success status code
     * Indicates that the operation was successful.
     *
     * @since 1.0.0
     */
    SUCCESS("200", true, "operation.success"),

    /**
     * Failure status code
     * Indicates that the operation failed.
     *
     * @since 1.0.0
     */
    FAILURE("500", false, "operation.failed"),

    /**
     * Error status code
     * Indicates a system error.
     *
     * @since 1.0.0
     */
    ERROR("-1", false, "system.error"),
    /**
     * Unauthorized or session expired
     * Indicates that the user is not authorized or the authentication token has expired.
     *
     * @since 1.0.0
     */
    UNAUTHORIZED("401", false, "unauthorized"),

    /**
     * Insufficient permissions
     * Indicates that the user does not have the necessary permissions to perform the operation.
     *
     * @since 1.0.0
     */
    FORBIDDEN("403", false, "forbidden"),

    /**
     * Resource not found
     * Indicates that the requested resource could not be located.
     *
     * @since 1.0.0
     */
    NO_RESOURCE_FOUND_EXCEPTION("404", false, "resource.not.found"),

    /**
     * Service unavailable
     * Indicates that the service is temporarily unavailable.
     *
     * @since 1.0.0
     */
    SERVICE_UNAVAILABLE("503", false, "service.unavailable"),

    /**
     * Unsupported HTTP request method
     * Indicates that the HTTP method used in the request is not supported.
     *
     * @since 1.0.0
     */
    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION("-1", false, "request.method.not.supported"),

    /**
     * Missing or empty request body
     * Indicates that the request body is either missing or empty.
     *
     * @since 1.0.0
     */
    HTTP_MESSAGE_NOT_READABLE_EXCEPTION("-1", false, "http.message.not.readable"),

    /**
     * Request is not a multipart request
     * Indicates that the request is expected to be a multipart request but is not.
     *
     * @since 1.0.0
     */
    REQUEST_NOT_MULTIPART("-1", false, "request.not.multipart"),

    /**
     * Unsupported HTTP media type
     * Indicates that the media type of the request is not supported.
     *
     * @since 1.0.0
     */
    HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION("-1", false, "http.media.type.not.supported"),

    /**
     * Missing request parameter
     * Indicates that a required request parameter is missing.
     *
     * @since 1.0.0
     */
    MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION("-1", false, "missing.servlet.request.parameter"),
    ;

    /**
     * The custom status code
     * This is a non-HTTP status code used for custom application responses.
     */
    private final String code;

    /**
     * Success flag
     * Indicates whether the operation was successful or not.
     */
    private final boolean success;

    /**
     * Custom message
     * Provides additional information about the operation.
     */
    private final String message;

    /**
     * Returns a custom status code (non-HTTP).
     *
     * @return The custom status code
     * @since 1.0.0
     */
    @Override
    public String code() {
        return this.code;
    }

    /**
     * Indicates whether the operation was successful or not.
     *
     * @return {@code true} if the operation was successful, {@code false} otherwise
     * @since 1.0.0
     */
    @Override
    public boolean success() {
        return this.success;
    }

    /**
     * Returns a custom message providing additional information about the operation.
     *
     * @return A custom message
     * @since 1.0.0
     */
    @Override
    public String message() {
        return this.message;
    }
}
