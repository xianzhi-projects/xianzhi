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

package io.xianzhi.core.thread;

import io.xianzhi.core.context.Context;
import io.xianzhi.core.context.ContextHolder;
import io.xianzhi.core.utils.TraceIdUtil;
import lombok.Getter;
import org.slf4j.MDC;

import java.util.concurrent.Callable;

/**
 * 自定义Callable，用于在任务执行前后设置和清理线程上下文。
 *
 * @param <T> 返回结果类型
 * @author Max
 * @since 1.0.0
 */
@Getter
public class XianZhiCallable<T> implements Callable<T> {

    /**
     * 自定义任务
     *
     * @since 1.0.0
     */
    private final Callable<T> call;

    /**
     * 上下文信息
     *
     * @since 1.0.0
     */
    private final Context context;

    /**
     * traceId
     *
     * @since 1.0.0
     */
    private final String traceId;

    /**
     * 构造方法, 用于初始化任务和上下文信息
     *
     * @param call 任务
     * @since 1.0.0
     */
    public XianZhiCallable(Callable<T> call) {
        this.call = call;
        this.context = ContextHolder.getContext();
        this.traceId = TraceIdUtil.getTraceId();
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public T call() throws Exception {
        try {
            // 设置上下文
            ContextHolder.setContext(context);
            // 执行任务
            return call.call();
        } finally {
            // 清除上下文
            ContextHolder.removeContext();
            // 清除traceId
            MDC.clear();
        }
    }
}

