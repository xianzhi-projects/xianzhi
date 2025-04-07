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

package io.xianzhi.core.code;

import io.xianzhi.core.result.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Common Response Result
 * This enumeration class provides a predefined collection of commonly used response status codes
 * and their associated messages. It implements the Result interface to ensure consistency in
 * API responses across the system. Each enum constant represents a specific response scenario,
 * such as success, failure, or various error conditions, enabling standardized communication
 * of operation outcomes.
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum CommonCode implements Result {

    /**
     * Operation Successful
     * Represents a successful operation with a standard HTTP-like success status code.
     */
    SUCCESS("200", "core.success"),

    /**
     * Operation Failed
     * Indicates a general failure of the operation with a standard HTTP-like server error code.
     */
    FAIL("500", "core.fail"),

    /**
     * System Error
     * Denotes an unexpected system-level error, typically used for unhandled exceptions or critical failures.
     */
    ERROR("XZ-01-00000001", "core.error"),

    /**
     * Unauthorized or Identity Expired
     * Indicates that the request lacks valid authentication or the user's session has expired.
     */
    UNAUTHORIZED("XZ-01-00000002", "core.unauthorized"),

    /**
     * Insufficient Permissions
     * Signals that the user does not have the necessary permissions to perform the requested action.
     */
    FORBIDDEN("XZ-01-00000003", "core.forbidden"),

    /**
     * Resource Not Found
     * Represents a situation where the requested resource could not be located on the server.
     */
    NO_RESOURCE_FOUND_EXCEPTION("XZ-01-00000004", "core.no.resource.found.exception"),

    /**
     * Service Unavailable
     * Indicates that the service is temporarily unavailable, often due to maintenance or overloading.
     */
    SERVICE_UNAVAILABLE("XZ-01-00000005", "core.service.unavailable"),

    /**
     * HTTP Request Method Not Supported
     * Occurs when the HTTP method used in the request (e.g., GET, POST) is not supported by the endpoint.
     */
    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION("XZ-01-00000006", "core.http.request.method.not.supported.exception"),

    /**
     * HTTP Message Not Readable
     * Signals that the request body could not be parsed or understood by the server.
     */
    HTTP_MESSAGE_NOT_READABLE_EXCEPTION("XZ-01-00000007", "core.http.message.not.readable.exception"),

    /**
     * Request Not Multipart
     * Indicates that the request was expected to be a multipart request (e.g., file upload) but was not.
     */
    REQUEST_NOT_MULTIPART("XZ-01-000000008.01", "core.request.not.multipart"),

    /**
     * HTTP Media Type Not Supported Exception
     * Occurs when the media type (e.g., application/json) of the request is not supported by the server.
     */
    HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION("XZ-01-000000008.02", "core.http.media.type.not.supported.exception"),

    /**
     * Missing Servlet Request Parameter Exception
     * Indicates that a required parameter is missing from the request.
     */
    MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION("XZ-01-000000008.03", "core.missing.servlet.request.parameter.exception"),

    /**
     * Duplicate Request
     * Signals that the request is a duplicate of a previous one, often used to prevent redundant processing.
     */
    DUPLICATE_REQUEST("XZ-01-000000009", "core.duplicate.request"),

    /**
     * Idempotent Request, Please Retry Later!
     * Indicates that the request is idempotent and currently being processed, suggesting a retry after a delay.
     */
    IDEMPOTENT_REQUEST("XZ-01-000000010", "core.idempotent.request"),

    /**
     * Parameter Check Error, Missing Parameter
     * Represents an error due to invalid or missing parameters in the request.
     */
    PARAM_CHECK_ERROR("XZ-01-000000011", "core.param.check.error"),
    /**
     * Data Not Exists
     */
    DATA_NOT_EXISTS("XZ-01-000000012", "core.data.not.exists"),
    /**
     * Data Exists
     */
    DATA_EXISTS("XZ-01-000000013", "core.data.exists"),
    /**
     * Dictionary Check Failed
     */
    DICT_CHECK_FAIL("XZ-01-000000014", "core.dict.check.fail"),


    ;

    /**
     * Custom Status Code
     * Stores the unique status code for each enum constant, used to identify the specific response scenario.
     */
    private final String code;

    /**
     * Custom Message
     * Holds the message associated with each enum constant, typically a key or identifier for a localized message.
     */
    private final String message;

    /**
     * Get Custom Status Code
     * Implements the code() method from the Result interface. This method returns the status code
     * associated with the current enum instance, providing a way to access the predefined code.
     *
     * @return A string representing the custom status code of this enum instance.
     */
    @Override
    public String code() {
        return this.code;
    }

    /**
     * Get Custom Message
     * Implements the message() method from the Result interface. This method returns the message
     * associated with the current enum instance, offering a way to retrieve the predefined message.
     *
     * @return A string containing the custom message of this enum instance.
     */
    @Override
    public String message() {
        return this.message;
    }
}
