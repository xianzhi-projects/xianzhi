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

package io.xianzhi.core.result;

/**
 * Top-level Interface for Response Results
 * This interface serves as the foundational contract for all response result objects in the system.
 * It establishes a standardized structure for conveying the outcome of operations, ensuring that
 * every response includes a status code and a message. This design promotes consistency across
 * various API response scenarios, making it easier to communicate success, failure, or specific
 * error conditions in a uniform manner.
 *
 * @author Max
 * @since 1.0.0
 */
public interface Result {

    /**
     * Get Custom Status Code
     * This method retrieves the status code associated with the response result. The status code
     * is returned as a string, allowing for flexible, user-defined values that can represent
     * different outcomes of an operation. Developers can use this code to indicate whether the
     * operation succeeded or to identify a specific type of error, enabling precise error
     * handling and reporting.
     *
     * @return A string representing the custom status code defined for this response result.
     */
    String code();

    /**
     * Get Custom Message
     * This method retrieves the message associated with the response result. The message is
     * returned as a string and provides a human-readable explanation of the operation's outcome.
     * It is intended to give the user or caller clear feedback, such as confirming a successful
     * action or detailing the reason for a failure, enhancing the usability and transparency
     * of the system.
     *
     * @return A string containing the custom message describing the result or error of the operation.
     */
    String message();
}