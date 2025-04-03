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

package io.xianzhi.core.result;

import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.utils.TraceIdUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 响应结果类
 * Response Result Class
 * 该类用于封装 API 的响应结果，包括状态码、提示信息、数据和追踪ID。
 * This class is used to encapsulate API response results, including status code, message, data, and trace ID.
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Getter
public class ResponseResult<R> implements Result, Serializable {

    /**
     * 返回的数据
     * Returned Data
     */
    private final R data;

    /**
     * 本次请求的TraceId
     * Trace ID of This Request
     */
    private final String traceId;

    /**
     * 自定义状态码
     * Custom Status Code
     */
    private final String code;

    /**
     * 自定义提示信息
     * Custom Message
     */
    private final String message;

    /**
     * 构造函数
     * Constructor
     * 根据响应信息和数据构造响应结果对象。
     * Constructs a response result object based on the response information and data.
     *
     * @param result 响应信息 / Response information
     * @param data   返回的数据 / Returned data
     */
    public ResponseResult(Result result, R data) {
        this.code = result.code();
        this.message = result.message();
        this.data = data;
        this.traceId = TraceIdUtils.getTraceId();
    }

    /**
     * 操作成功
     * Operation Successful
     * 返回一个表示成功的响应结果，不带数据。
     * Returns a successful response result without data.
     *
     * @param <T> 返回数据的泛型 / Generic type of returned data
     * @return 响应信息 / Response information
     */
    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>(CommonCode.SUCCESS, null);
    }

    /**
     * 操作成功
     * Operation Successful
     * 返回一个表示成功的响应结果，带有数据。
     * Returns a successful response result with data.
     *
     * @param data 返回的数据 / Returned data
     * @param <T>  返回数据的泛型 / Generic type of returned data
     * @return 响应信息 / Response information
     */
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(CommonCode.SUCCESS, data);
    }

    /**
     * 操作失败
     * Operation Failed
     * 返回一个表示失败的响应结果，不带数据。
     * Returns a failed response result without data.
     *
     * @param result 响应结果 / Response result
     * @param <T>    返回数据的泛型 / Generic type of returned data
     * @return 响应信息 / Response information
     */
    public static <T> ResponseResult<T> fail(Result result) {
        return new ResponseResult<>(result, null);
    }

    /**
     * 获取自定义状态码
     * Get Custom Status Code
     * 返回响应的状态码，实现 Result 接口的方法。
     * Returns the status code of the response, implementing the Result interface method.
     *
     * @return 自定义状态码 / Custom status code
     */
    @Override
    public String code() {
        return this.code;
    }

    /**
     * 获取自定义提示信息
     * Get Custom Message
     * 返回响应的提示信息，实现 Result 接口的方法。
     * Returns the message of the response, implementing the Result interface method.
     *
     * @return 自定义提示信息 / Custom message
     */
    @Override
    public String message() {
        return this.message;
    }
}
