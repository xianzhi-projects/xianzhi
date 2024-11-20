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

/**
 * Interface for providing trace information, such as Trace ID and Span ID.
 * <p>
 * Implementations of this interface should provide methods to retrieve the current Trace ID and Span ID,
 * which are typically used for distributed tracing.
 * </p>
 *
 * @author Max
 * @since 1.0.0
 */
public interface TraceProvider {

    /**
     * Retrieves the current Trace ID.
     * <p>
     * This method returns the Trace ID for the current request, typically used for tracking the entire
     * request flow across multiple services in distributed tracing systems.
     * </p>
     *
     * @return The current Trace ID.
     * @since 1.0.0
     */
    String getCurrentTraceId();

    /**
     * Retrieves the current Span ID.
     * <p>
     * This method returns the Span ID for the current request, which represents a single unit of work
     * within the broader trace. It is used to track a specific operation or step within the request flow.
     * </p>
     *
     * @return The current Span ID.
     * @since 1.0.0
     */
    String getCurrentSpanId();
}
