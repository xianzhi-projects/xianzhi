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

package io.xianzhi.core.utils;

import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.exception.BusinessException;
import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.core.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Utility class for handling HTTP responses and requests in a Spring web application.
 * <p>
 * This class provides methods for sending JSON responses to the frontend,
 * as well as retrieving the current HTTP request and response objects.
 * </p>
 *
 * <p>
 * It is designed to simplify common tasks in web application development,
 * such as serializing objects to JSON and handling HTTP headers.
 * </p>
 *
 * <p><strong>Note:</strong> This class cannot be instantiated.</p>
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
public class ResponseUtils {

    /**
     * Private constructor to prevent instantiation.
     */
    private ResponseUtils() {
    }

    /**
     * Sends a JSON-formatted response to the frontend with UTF-8 encoding.
     * <p>
     * If the provided {@link Result} object is not an instance of {@link ResponseResult},
     * it will be wrapped in a new {@link ResponseResult} object with a {@code null} data field.
     * </p>
     *
     * <p>Usage example:</p>
     * <pre>{@code
     * Result result = CommonCode.SUCCESS;
     * ResponseUtils.responseUtf8(result, response);
     * }</pre>
     *
     * @param result   The result object to be serialized as JSON and sent in the response
     * @param response The {@link HttpServletResponse} object used to send the response
     * @throws RuntimeException If an error occurs while writing to the response output stream
     */
    public static void responseUtf8(Result result, HttpServletResponse response) {
        try {
            if (!(result instanceof ResponseResult<?>)) {
                result = new ResponseResult<>(result, null);
            }
            // Set response headers and character encoding
            response.setContentType("application/json;charset=UTF-8");
            // Write the JSON string to the response body
            response.getWriter().write(JSONUtil.toJSONString(result));
        } catch (Exception e) {
            log.error("Failed to write JSON response: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the current {@link HttpServletResponse} object from the request context.
     * <p>
     * This method uses {@link RequestContextHolder} to fetch the {@link ServletRequestAttributes},
     * and then extracts the {@link HttpServletResponse}.
     * </p>
     *
     * @return The current {@link HttpServletResponse} object
     * @throws BusinessException If the response object cannot be retrieved
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = null;
        if (attributes != null) {
            response = attributes.getResponse();
        }
        if (response == null) {
            log.error("Failed to retrieve HttpServletResponse: Response object is null");
            throw new BusinessException(CommonCode.ERROR);
        }
        return response;
    }

    /**
     * Retrieves the current {@link HttpServletRequest} object from the request context.
     * <p>
     * This method uses {@link RequestContextHolder} to fetch the {@link ServletRequestAttributes},
     * and then extracts the {@link HttpServletRequest}.
     * </p>
     *
     * @return The current {@link HttpServletRequest} object
     * @throws BusinessException If the request object cannot be retrieved
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }
        if (request == null) {
            log.error("Failed to retrieve HttpServletRequest: Request object is null");
            throw new BusinessException(CommonCode.ERROR);
        }
        return request;
    }
}