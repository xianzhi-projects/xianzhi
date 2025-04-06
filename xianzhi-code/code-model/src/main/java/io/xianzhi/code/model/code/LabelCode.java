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

package io.xianzhi.code.model.code;

import io.xianzhi.core.result.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Label状态码
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum LabelCode implements Result {
    ;

    /**
     * Custom Status Code
     * Stores the unique status code for each enum constant, used to identify the specific response scenario.
     */
    private final String code;

    /**
     * Custom Message
     * Holds the message associated with each enum constant, typically a key or identifier for a localized message.
     */
    private final String message;

    /**
     * Get Custom Status Code
     * Implements the code() method from the Result interface. This method returns the status code
     * associated with the current enum instance, providing a way to access the predefined code.
     *
     * @return A string representing the custom status code of this enum instance.
     */
    @Override
    public String code() {
        return this.code;
    }

    /**
     * Get Custom Message
     * Implements the message() method from the Result interface. This method returns the message
     * associated with the current enum instance, offering a way to retrieve the predefined message.
     *
     * @return A string containing the custom message of this enum instance.
     */
    @Override
    public String message() {
        return this.message;
    }
}