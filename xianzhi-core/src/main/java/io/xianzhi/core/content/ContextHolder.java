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

package io.xianzhi.core.content;

import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * 上下文处理
 * Context Handling
 * 该类用于管理线程级别的上下文内容，提供设置、获取和移除上下文的功能。
 * This class manages thread-level context content, providing functions to set, get, and remove the context.
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
public class ContextHolder {

    /**
     * 上下文内容
     * Context Content
     * 使用 ThreadLocal 存储当前线程的上下文对象。
     * Uses ThreadLocal to store the context object of the current thread.
     */
    private final static ThreadLocal<Context> CONTENT = new ThreadLocal<>();


    /**
     * 设置上下文内容
     * Set Context Content
     * 将指定的上下文对象存储到当前线程中，如果传入为空则抛出异常。
     * Stores the specified context object in the current thread, throwing an exception if the input is null.
     *
     * @param content 上下文内容 / Context content
     */
    public static void set(Context content) {
        if (null == content) {
            log.error("context is null");
            throw new BusinessException(CommonCode.PARAM_CHECK_ERROR);
        }
        CONTENT.set(content);
    }

    /**
     * 获取上下文内容，如果不存在则抛出异常
     * Get Context Content or Throw Exception
     * 返回当前线程的上下文对象，如果不存在则抛出未授权异常。
     * Returns the context object of the current thread, throwing an unauthorized exception if it does not exist.
     *
     * @return 上下文内容 / Context content
     */
    public static Context getContextOrThrow() {
        return Optional.ofNullable(CONTENT.get()).orElseThrow(() -> new BusinessException(CommonCode.UNAUTHORIZED));
    }

    /**
     * 获取上下文内容
     * Get Context Content
     * 返回当前线程的上下文对象，以 Optional 形式封装，可能为空。
     * Returns the context object of the current thread, wrapped in an Optional, which may be empty.
     *
     * @return 上下文内容 / Context content
     */
    public static Optional<Context> getContent() {
        return Optional.ofNullable(CONTENT.get());
    }

    /**
     * 移除上下文内容
     * Remove Context Content
     * 从当前线程中移除上下文对象，通常用于清理资源。
     * Removes the context object from the current thread, typically used for resource cleanup.
     */
    public static void remove() {
        CONTENT.remove();
    }
}
