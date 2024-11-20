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

package io.xianzhi.core.utils;

import io.xianzhi.core.trace.DefaultTraceProvider;
import io.xianzhi.core.trace.TraceProvider;
import lombok.Getter;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Utility class for managing Trace IDs and Span IDs in a distributed tracing system.
 * <p>
 * This class provides methods to retrieve the current Trace ID and Span ID for the current request.
 * It loads a {@link TraceProvider} implementation using {@link ServiceLoader} and falls back to a
 * default implementation if no custom ones are provided.
 * </p>
 *
 * @author Max
 * @since 1.0.0
 */
public class TraceIdUtil {

    /**
     * Trace ID key used in request headers or logs.
     *
     * @since 1.0.0
     */
    public static final String TRACE_ID = "XZ-TRACE-ID";

    /**
     * Trace provider used to obtain Trace ID and Span ID.
     * This can be a custom implementation loaded via ServiceLoader.
     *
     * @since 1.0.0
     */
    @Getter
    private static TraceProvider traceProvider;

    /*
     * Using ServiceLoader to load all TraceProvider implementations.
     */
    static {
        // Load all TraceProvider implementations using ServiceLoader
        ServiceLoader<TraceProvider> serviceLoader = ServiceLoader.load(TraceProvider.class);
        Iterator<TraceProvider> iterator = serviceLoader.iterator();
        if (iterator.hasNext()) {
            // If there are other implementations, use the first one found
            traceProvider = iterator.next();
        } else {
            // If no other implementation is found, use the default implementation
            traceProvider = new DefaultTraceProvider();
        }
    }

    /**
     * Private constructor to prevent instantiation.
     *
     * @since 1.0.0
     */
    private TraceIdUtil() {
    }

    /**
     * Retrieves the current Trace ID.
     * <p>
     * This method calls the {@link TraceProvider#getCurrentTraceId()} to retrieve the current Trace ID,
     * which is typically used for tracking the entire request flow in distributed systems.
     * </p>
     *
     * @return The current Trace ID as a string.
     * @since 1.0.0
     */
    public static String getTraceId() {
        return traceProvider.getCurrentTraceId();
    }

    /**
     * Retrieves the current Span ID.
     * <p>
     * This method calls the {@link TraceProvider#getCurrentSpanId()} to retrieve the current Span ID,
     * which represents a single unit of work within the broader Trace ID in a distributed trace.
     * </p>
     *
     * @return The current Span ID as a string.
     * @since 1.0.0
     */
    public static String getCurrentSpanId() {
        return traceProvider.getCurrentSpanId();
    }
}
