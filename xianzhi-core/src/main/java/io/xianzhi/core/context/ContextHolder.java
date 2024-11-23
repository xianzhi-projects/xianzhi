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
 * 上下文持有者
 * 一个用于管理线程本地“Context”对象的实用类。
 *
 * @author Max
 * @since 1.0.0
 */
public class ContextHolder {

    /**
     * Context”对象的线程本地存储。
     *
     * @since 1.0.0
     */
    private static final ThreadLocal<Context> CONTEXT = new ThreadLocal<>();

    /**
     * 获取上下文
     *
     * @return 当前线程上下文，可能为空
     * @since 1.0.0
     */
    public static Context getContext() {
        return CONTEXT.get();
    }

    /**
     * 设置上下文
     *
     * @param context 上下文信息
     * @throws BusinessException 如果传递的上下文信息为空的话则抛出异常
     * @since 1.0.0
     */
    public static void setContext(Context context) {
        if (context == null) {
            throw new BusinessException(CommonCode.ERROR);
        }
        CONTEXT.set(context);
    }

    /**
     * 获取当前线程的上下文，如果为空的话则抛出异常
     *
     * @return 当前线程上下文
     * @throws BusinessException 如果当前线程没有关联上下文
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
     * 获取唯一标识
     *
     * @return 唯一标识
     * @throws BusinessException 如果当前线程没有关联上下文
     * @since 1.0.0
     */
    public static String getId() {
        return getContextOrThrow().getId();
    }

    /**
     * 清除上下文
     *
     * @since 1.0.0
     */
    public static void removeContext() {
        CONTEXT.remove();
    }
}
