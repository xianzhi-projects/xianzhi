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

package io.xianzhi.core.context;

import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.exception.BusinessException;

/**
 * ContextHolder
 * <p>
 * A utility class to manage thread-local `Context` objects.
 * It provides methods to set, retrieve, and clear the context associated
 * with the current thread, ensuring thread safety and isolation.
 * <p>
 * This is particularly useful in scenarios where context information
 * (like user details, transaction information, etc.) needs to be accessed
 * consistently within a thread.
 *
 * @author Max
 * @since 1.0.0
 */
public class ContextHolder {

    /**
     * Thread-local storage for the `Context` object.
     * Each thread will have its own isolated `Context` instance.
     *
     * @since 1.0.0
     */
    private static final ThreadLocal<Context> CONTEXT = new ThreadLocal<>();

    /**
     * Retrieves the context associated with the current thread.
     *
     * @return The current thread's context, or {@code null} if no context is set.
     * @since 1.0.0
     */
    public static Context getContext() {
        return CONTEXT.get();
    }

    /**
     * Sets the context for the current thread.
     *
     * @param context The `Context` object to be associated with the current thread.
     * @throws BusinessException If the provided context is {@code null}.
     * @since 1.0.0
     */
    public static void setContext(Context context) {
        if (context == null) {
            throw new BusinessException(CommonCode.ERROR);
        }
        CONTEXT.set(context);
    }

    /**
     * Retrieves the context associated with the current thread.
     * <p>
     * If no context is found, throws a {@link BusinessException}.
     *
     * @return The current thread's context.
     * @throws BusinessException If no context is associated with the current thread.
     * @since 1.0.0
     */
    public static Context getContextOrThrow() {
        Context context = CONTEXT.get();
        if (context == null) {
            throw new BusinessException(CommonCode.UNAUTHORIZED);
        }
        return context;
    }

    /**
     * Retrieves the unique ID from the current thread's context.
     *
     * @return The unique ID of the context.
     * @throws BusinessException If no context is associated with the current thread.
     * @since 1.0.0
     */
    public static String getId() {
        return getContextOrThrow().getId();
    }

    /**
     * Clears the context associated with the current thread.
     * <p>
     * This method should be called at the end of the thread's lifecycle to prevent memory leaks.
     *
     * @since 1.0.0
     */
    public static void removeContext() {
        CONTEXT.remove();
    }
}
