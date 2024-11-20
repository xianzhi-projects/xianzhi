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
 * Default implementation of the {@link TraceProvider} interface.
 * <p>
 * This class provides default methods for retrieving Trace ID and Span ID.
 * The Trace ID and Span ID are typically used in distributed tracing systems
 * to track the flow of requests across different services.
 * </p>
 *
 * @author Max
 * @since 1.0.0
 */
public class DefaultTraceProvider implements TraceProvider {

    /**
     * Retrieves the current Trace ID.
     * <p>
     * This method generates a new unique Trace ID for the current request.
     * The Trace ID is typically used to track the entire request flow across
     * multiple services in distributed tracing systems.
     * </p>
     *
     * @return The current Trace ID as a string.
     * @since 1.0.0
     */
    @Override
    public String getCurrentTraceId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Retrieves the current Span ID.
     * <p>
     * This method generates a new unique Span ID for the current request.
     * The Span ID represents a single unit of work within the broader Trace ID
     * and is used to track a specific operation or step in the request flow.
     * </p>
     *
     * @return The current Span ID as a string.
     * @since 1.0.0
     */
    @Override
    public String getCurrentSpanId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}