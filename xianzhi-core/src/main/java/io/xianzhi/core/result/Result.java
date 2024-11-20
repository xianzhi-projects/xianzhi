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

package io.xianzhi.core.result;

/**
 * Public response result
 *
 * @author Max
 * @since 1.0.0
 */
public interface Result {

    /**
     * Returns a custom status code (non-HTTP).
     *
     * @return The custom status code
     * @since 1.0.0
     */
    String code();

    /**
     * Indicates whether the operation was successful or not.
     *
     * @return {@code true} if the operation was successful, {@code false} otherwise
     * @since 1.0.0
     */
    boolean success();

    /**
     * Returns a custom message providing additional information about the operation.
     *
     * @return A custom message
     * @since 1.0.0
     */
    String message();
}
