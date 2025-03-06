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
 *
 * @author Max
 * @since 1.0.0
 */

@Slf4j
@Getter
public class ResponseResult<R> implements Result, Serializable {


    /**
     * 操作成功状态码
     */
    public static final String SUCCESS_CODE = "200";


    /**
     * 返回的数据
     */
    private final R data;

    /**
     * 本次请求的TraceId
     */
    private final String traceId;
    /**
     * 自定义状态码
     */
    private final String code;
    /**
     * 自定义提示信息
     */
    private final String message;

    /**
     * 构造函数
     *
     * @param result 响应信息
     * @param data   返回的数据
     */
    public ResponseResult(Result result, R data) {
        this.code = result.code();
        this.message = result.message();
        this.data = data;
        this.traceId = TraceIdUtils.getTraceId();
    }

    /**
     * 操作成功
     *
     * @param <T> 返回数据的泛型
     * @return 响应信息
     */
    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>(CommonCode.SUCCESS, null);
    }

    /**
     * 操作成功
     *
     * @param data 返回的数据
     * @param <T>  返回数据的泛型
     * @return 响应信息
     */
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(CommonCode.SUCCESS, data);
    }



    /**
     * 操作失败
     *
     * @param result 响应结果
     * @param <T>    返回数据的泛型
     * @return 响应信息
     */
    public static <T> ResponseResult<T> fail(Result result) {
        return new ResponseResult<>(result, null);
    }


    /**
     * 获取自定义状态码
     *
     * @return 自定义状体码
     */
    @Override
    public String code() {
        return this.code;
    }

    /**
     * 获取自定义提示信息
     *
     * @return 自定义提示信息
     */
    @Override
    public String message() {
        return this.message;
    }
}
