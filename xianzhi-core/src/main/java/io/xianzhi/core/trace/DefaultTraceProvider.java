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

package io.xianzhi.core.trace;

import java.util.UUID;

/**
 * 默认traceId提供者
 *
 * @author Max
 * @since 1.0.0
 */
public class DefaultTraceProvider implements TraceProvider {

    /**
     * 获取当前TraceId
     *
     * @return 当前TraceId
     * @since 1.0.0
     */
    @Override
    public String getCurrentTraceId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取当前Span ID
     *
     * @return 当前Span ID
     * @since 1.0.0
     */
    @Override
    public String getCurrentSpanId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}