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

package io.xianzhi.boot.web;

import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.exception.BusinessException;
import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.core.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.util.Objects;

/**
 * Global exception handler to manage and centralize the exception handling logic across the application.
 *
 * <p>This class uses {@link RestControllerAdvice} to intercept and process exceptions thrown by controllers.
 * It ensures consistent error responses and improves maintainability by consolidating exception handling logic.</p>
 *
 * <p>Handles validation errors, custom exceptions, and unexpected exceptions.</p>
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@RestControllerAdvice
public class GlobeExceptionHandler {

    /**
     * Handles unknown exceptions.
     *
     * @param exception the unknown exception
     * @return the response result
     * @since 1.0.0
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseResult<Object> exception(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResponseResult.fail(CommonCode.ERROR);
    }

    /**
     * Handles business exceptions.
     *
     * @param exception the business exception
     * @return the response result
     * @since 1.0.0
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResponseResult<Object> bizException(BusinessException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseResult.fail(exception.getResult());
    }

    /**
     * Handles NullPointerException.
     *
     * @param exception the null pointer exception
     * @return the response result
     * @since 1.0.0
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseResult<Object> nullPointerException(NullPointerException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseResult.fail(CommonCode.ERROR);
    }

    /**
     * Handles HttpRequestMethodNotSupportedException.
     *
     * @param exception the HTTP request method not supported exception
     * @return the response result
     * @since 1.0.0
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseResult<Object> httpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseResult.fail(CommonCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION);
    }

    /**
     * Handles HttpMessageNotReadableException.
     *
     * @param exception the exception indicating that the HTTP message is not readable
     * @return the response result
     * @since 1.0.0
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseResult<Object> httpMessageNotReadableException(HttpMessageNotReadableException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseResult.fail(CommonCode.HTTP_MESSAGE_NOT_READABLE_EXCEPTION);
    }

    /**
     * Handles exceptions for requests that are not multipart.
     *
     * @param exception the multipart exception
     * @return the response result
     * @since 1.0.0
     */
    @ExceptionHandler(value = MultipartException.class)
    public ResponseResult<Object> multipartException(MultipartException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseResult.fail(CommonCode.REQUEST_NOT_MULTIPART);
    }

    /**
     * Handles MissingServletRequestParameterException.
     *
     * @param exception the exception for missing servlet request parameters
     * @return the response result
     * @since 1.0.0
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseResult<Object> missingServletRequestParameterException(
            MissingServletRequestParameterException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseResult.fail(CommonCode.MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION);
    }

    /**
     * Handles validation exceptions for method arguments.
     *
     * @param exception the method argument not valid exception
     * @return the response result
     * @since 1.0.0
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseResult<Object> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage(), exception);
        String defaultMessage = Objects.requireNonNull(exception.getBindingResult().getFieldError())
                .getDefaultMessage();
        return ResponseResult.fail(new Result() {
            @Override
            public String code() {
                return CommonCode.FAILURE.code();
            }

            @Override
            public boolean success() {
                return false;
            }

            @Override
            public String message() {
                return defaultMessage;
            }
        });
    }

    /**
     * Handles IllegalArgumentException.
     *
     * @param exception the illegal argument exception
     * @return the response result
     * @since 1.0.0
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseResult<Object> IllegalArgumentException(IllegalArgumentException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseResult.fail(new Result() {
            @Override
            public String code() {
                return CommonCode.PARAMS_CHECK_FAIL.code();
            }

            @Override
            public boolean success() {
                return false;
            }

            @Override
            public String message() {
                return CommonCode.PARAMS_CHECK_FAIL.message();
            }
        });
    }
}
