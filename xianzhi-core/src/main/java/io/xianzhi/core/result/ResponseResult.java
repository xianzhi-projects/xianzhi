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
 * 响应结果类
 *
 * @param <R> 响应结果泛型
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Getter
public class ResponseResult<R> implements Result {

    /**
     * 自定义状态码
     */
    private final String code;

    /**
     * 是否操作成功
     */
    private final boolean success;

    /**
     * 自定义提示信息
     */
    private final String message;

    /**
     * 返回的数据
     */
    private final R data;

    /**
     * traceId
     */
    private final String traceId;

    /**
     * 构建返回结果
     *
     * @param result 响应结果
     * @param data   查询的数据
     * @since 1.0.0
     */
    private ResponseResult(Result result, R data) {
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
     * 创建操作成功的响应结果
     *
     * @param <T> 返回参数泛型
     * @return 返回操作成功响应信息
     * @since 1.0.0
     */
    public static <T> ResponseResult<T> ok() {
        return new ResponseResult<>(CommonCode.SUCCESS, null);
    }

    /**
     * 创建操作成功的响应结果，并返回数据
     *
     * @param data 返回的数据
     * @param <T>  返回的数据泛型
     * @return 返回相应操作成功响应信息
     * @since 1.0.0
     */
    public static <T> ResponseResult<T> ok(T data) {
        return new ResponseResult<>(CommonCode.SUCCESS, data);
    }

    /**
     * 创建一个操作失败的响应结果
     *
     * @param result The result object describing the failure
     * @param <T>    The type of the response data
     * @return A {@code ResponseResult} instance with a failure status
     * @since 1.0.0
     */
    public static <T> ResponseResult<T> fail(Result result) {
        return new ResponseResult<>(result.success() ? CommonCode.FAILURE : result, null);
    }

    /**
     * 返回自定义状态码
     *
     * @return 自定义状态码
     * @since 1.0.0
     */
    @Override
    public String code() {
        return this.code;
    }

    /**
     * 表示操作是否成功
     *
     * @return {@code true} 表示成功, {@code false} 操作失败
     * @since 1.0.0
     */
    @Override
    public boolean success() {
        return this.success;
    }

    /**
     * 返回自定操作提示信息
     *
     * @return 自定义提示信息
     * @since 1.0.0
     */
    @Override
    public String message() {
        return this.message;
    }
}
