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

import java.util.concurrent.Callable;

/**
 * A wrapper for {@link Callable} that propagates a custom context and trace ID
 * across thread boundaries. This is particularly useful in multi-threaded environments
 * where thread-local context or trace information needs to be preserved.
 *
 * <p>The class ensures that the {@link Context} is set before the task execution
 * and cleaned up afterward to prevent any memory leaks or context pollution.</p>
 *
 * @param <T> the type of the result returned by this callable
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
public class XianZhiCallable<T> implements Callable<T> {

    /**
     * The actual task to be executed.
     */
    private final Callable<T> call;

    /**
     * The context associated with the current thread.
     * This is captured when the {@link XianZhiCallable} is created.
     */
    private final Context context;

    /**
     * The trace ID for the current task, useful for distributed tracing.
     */
    private final String traceId;

    /**
     * Constructs a new {@code XianZhiCallable} instance, capturing the current
     * {@link Context} and trace ID at the time of creation.
     *
     * @param call the task to be executed
     */
    public XianZhiCallable(Callable<T> call) {
        this.call = call;
        this.context = ContextHolder.getContext(); // Capture the current context
        this.traceId = TraceIdUtil.getTraceId();   // Capture the current trace ID
    }

    /**
     * Executes the task, ensuring that the captured {@link Context} is set
     * in the thread-local storage before execution and cleaned up afterward.
     *
     * @return the result of the task
     * @throws Exception if the task execution fails
     */
    @Override
    public T call() throws Exception {
        try {
            // Set the captured context in the current thread
            ContextHolder.setContext(context);
            // Execute the actual task
            return call.call();
        } finally {
            // Clean up the context after execution
            ContextHolder.removeContext();
        }
    }
}

