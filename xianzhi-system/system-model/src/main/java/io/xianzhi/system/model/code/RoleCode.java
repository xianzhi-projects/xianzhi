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

package io.xianzhi.system.model.code;

import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.result.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 角色编码
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum RoleCode implements Result {
    /**
     * 角色名称已经存在
     */
    ROLE_NAME_EXISTS(CommonCode.DATA_EXISTS.code(), "sys.role.name.exists"),
    /**
     * 角色编码已经存在
     */
    ROLE_CODE_EXISTS(CommonCode.DATA_EXISTS.code(), "sys.role.code.exists"),
    /**
     * 角色不存在
     */
    ROLE_NOT_EXISTS(CommonCode.DATA_NOT_EXISTS.code(), "sys.role.not.exists"),
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
     * This method retrieves the status code associated with the response result. The status code
     * is returned as a string, allowing for flexible, user-defined values that can represent
     * different outcomes of an operation. Developers can use this code to indicate whether the
     * operation succeeded or to identify a specific type of error, enabling precise error
     * handling and reporting.
     *
     * @return A string representing the custom status code defined for this response result.
     */
    @Override
    public String code() {
        return code;
    }

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
    @Override
    public String message() {
        return message;
    }
}
