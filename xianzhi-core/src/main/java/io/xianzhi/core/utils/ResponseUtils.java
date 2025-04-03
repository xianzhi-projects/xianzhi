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

package io.xianzhi.core.utils;

import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.core.result.Result;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Response Utility Class
 * This class provides static utility methods for handling HTTP responses, particularly for sending
 * JSON data to the client and retrieving the current HttpServletResponse object in a web context.
 * It is designed to simplify response handling in a servlet-based application, ensuring consistent
 * content type settings and error management. The class uses SLF4J logging for error tracking and
 * is implemented as a utility with a private constructor to prevent instantiation.
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
public class ResponseUtils {

    /**
     * Private Constructor
     * This constructor is private to prevent instantiation of the utility class, as all methods
     * are static and intended for utility use only.
     */
    private ResponseUtils() {
    }

    /**
     * Send JSON Response to Client
     * This method sends a JSON-formatted response to the client using the provided HttpServletResponse
     * object. It accepts a Result object, wraps it in a ResponseResult if necessary, sets the
     * appropriate content type and UTF-8 encoding, and writes the JSON string to the response
     * output. Exceptions during this process are logged and rethrown as a RuntimeException.
     *
     * @param result   The data to be sent in the response, typically a Result or ResponseResult object.
     * @param response The HttpServletResponse object used to write the response to the client.
     * @throws RuntimeException If an error occurs while writing the response (e.g., IOException).
     */
    public static void responseUtf8(Result result, HttpServletResponse response) {
        try {
            // Ensure the result is a ResponseResult; wrap it if it isn’t
            if (!(result instanceof ResponseResult<?>)) {
                result = new ResponseResult<>(result, null);
            }
            // Set the response headers for JSON content with UTF-8 encoding
            response.setContentType("application/json;charset=UTF-8");
            // Write the JSON string to the response body
            response.getWriter().write(JSONUtils.toJSONString(result));
        } catch (Exception e) {
            log.error("Failed to write JSON response to client", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieve Current HttpServletResponse
     * This method attempts to retrieve the current HttpServletResponse object from the Spring
     * RequestContextHolder, which holds the request attributes for the current thread in a web
     * application. If the response cannot be obtained (e.g., outside a web context), it logs an
     * error and returns null. This method is useful for accessing the response object when it is
     * not directly available (e.g., in a service layer).
     *
     * @return The current HttpServletResponse object, or null if it cannot be retrieved.
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            return attributes.getResponse();
        }
        log.error("Unable to retrieve HttpServletResponse object from request context");
        return null;
    }
}
