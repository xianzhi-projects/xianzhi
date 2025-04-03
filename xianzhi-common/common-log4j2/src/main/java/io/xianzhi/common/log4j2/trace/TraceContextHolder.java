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

package io.xianzhi.common.log4j2.trace;


/**
 * Trace上下文处理
 *
 * @author Max
 * @since 1.0.0
 */
public class TraceContextHolder {

    // 使用 InheritableThreadLocal，确保子线程继承父线程的上下文
    private static final ThreadLocal<TraceContext> TRACE_CONTEXT = new InheritableThreadLocal<>();

    /**
     * 设置当前线程的追踪上下文。
     *
     * @param context 追踪上下文对象
     */
    public static void set(TraceContext context) {
        TRACE_CONTEXT.set(context);
    }

    /**
     * 获取当前线程的追踪上下文。
     *
     * @return 当前线程的 TraceContext，若未设置则返回 null
     */
    public static TraceContext get() {
        return TRACE_CONTEXT.get();
    }

    /**
     * 清理当前线程的追踪上下文。
     * 在请求处理完成后调用，避免内存泄漏。
     */
    public static void clear() {
        TRACE_CONTEXT.remove();
    }

}
