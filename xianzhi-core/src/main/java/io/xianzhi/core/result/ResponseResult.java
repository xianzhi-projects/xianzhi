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

package io.xianzhi.core.result;

import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.utils.SpringUtils;
import io.xianzhi.core.utils.TraceIdUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * A generic class representing a unified response structure for API results.
 * <p>
 * This class implements the {@link Result} interface and provides additional fields and methods
 * to standardize API responses. It includes fields for status code, success flag, message, data, and trace ID.
 * </p>
 *
 * @param <R> The type of the response data
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Getter
public class ResponseResult<R> implements Result {

    /**
     * The custom status code.
     * This is a non-HTTP status code used for custom application responses.
     */
    private final String code;

    /**
     * Success flag.
     * Indicates whether the operation was successful or not.
     */
    private final boolean success;

    /**
     * Custom message.
     * Provides additional information about the operation.
     */
    private final String message;

    /**
     * The response data of type {@code R}.
     */
    private final R data;

    /**
     * The trace ID for the request.
     * Useful for logging and debugging.
     */
    private final String traceId;

    /**
     * Constructs a new {@code ResponseResult} object by copying the fields from an existing {@link Result} object
     * and adding additional response data.
     * <p>
     * If a dynamic message source bean is available in the Spring context, it attempts to localize
     * the message based on the current locale.
     * </p>
     *
     * @param result The result object containing status and message
     * @param data   The response data of type {@code R}
     */
    public ResponseResult(Result result, R data) {
        this.code = result.code();
        this.success = result.success();
        String oldMessage = result.message();
        try {
            Map<String, MessageSource> messageSourceMap = SpringUtils.getBeansOfType(MessageSource.class);
            if (!ObjectUtils.isEmpty(messageSourceMap)) {
                MessageSource messageSource = messageSourceMap.get("dynamicMessageSource");
                if (null != messageSource) {
                    oldMessage = messageSource.getMessage(result.message(), null, LocaleContextHolder.getLocale());
                }
            }
        } catch (NoSuchMessageException exception) {
            log.error(exception.getMessage(), exception);
        }
        this.message = oldMessage;
        this.data = data;
        this.traceId = TraceIdUtil.getTraceId();
    }

    /**
     * Creates a successful response without any data.
     *
     * @param <T> The type of the response data
     * @return A {@code ResponseResult} instance with a success status
     */
    public static <T> ResponseResult<T> ok() {
        return new ResponseResult<>(CommonCode.SUCCESS, null);
    }

    /**
     * Creates a successful response with the given data.
     *
     * @param data The data to include in the response
     * @param <T>  The type of the response data
     * @return A {@code ResponseResult} instance with a success status and data
     */
    public static <T> ResponseResult<T> ok(T data) {
        return new ResponseResult<>(CommonCode.SUCCESS, data);
    }

    /**
     * Creates a failed response with the given result.
     * If the given result indicates success, the failure response uses {@link CommonCode#FAILURE} instead.
     *
     * @param result The result object describing the failure
     * @param <T>    The type of the response data
     * @return A {@code ResponseResult} instance with a failure status
     */
    public static <T> ResponseResult<T> fail(Result result) {
        return new ResponseResult<>(result.success() ? CommonCode.FAILURE : result, null);
    }

    /**
     * Returns a custom status code (non-HTTP).
     *
     * @return The custom status code
     */
    @Override
    public String code() {
        return this.code;
    }

    /**
     * Indicates whether the operation was successful or not.
     *
     * @return {@code true} if the operation was successful, {@code false} otherwise
     */
    @Override
    public boolean success() {
        return this.success;
    }

    /**
     * Returns a custom message providing additional information about the operation.
     *
     * @return A custom message
     */
    @Override
    public String message() {
        return this.message;
    }
}
