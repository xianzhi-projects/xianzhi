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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * trace上下文
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
public class TraceContext {

    /**
     * 全局唯一的追踪 ID，用于标识整个请求链路
     */
    private String traceId;
    /**
     * 当前 Span 的 ID，表示当前请求的某一段操作
     */
    private String spanId;
    /**
     * 父 Span 的 ID，用于构建调用层级关系
     */
    private String parentSpanId;
}
