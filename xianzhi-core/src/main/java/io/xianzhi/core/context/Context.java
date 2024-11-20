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

/**
 * Context Interface
 * <p>
 * Represents a generic context abstraction that provides a unique identifier.
 * This can be implemented by various classes to standardize the retrieval
 * of contextual information within the application.
 * <p>
 * Common use cases include:
 * - User context
 * - Client context
 * - Application-specific contexts
 *
 * @author Max
 * @since 1.0.0
 */
public interface Context {

    /**
     * Retrieves the unique identifier for this context.
     * <p>
     * The ID could represent various entities such as a session ID,
     * transaction ID, or other domain-specific identifiers.
     *
     * @return The unique ID associated with the context
     * @since 1.0.0
     */
    String getId();
}
