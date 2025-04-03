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
 * 响应结果顶级接口
 * Top-level Interface for Response Results
 * 该接口定义了所有响应结果的基本结构，用于统一返回状态码和提示信息。
 * This interface defines the basic structure for all response results, used to standardize the return of status codes and messages.
 * 适用于各种 API 响应场景，提供标准化的成功或失败信息。
 * It is suitable for various API response scenarios, providing a consistent way to convey success or failure information.
 *
 * @author Max
 * @since 1.0.0
 */
public interface Result {

    /**
     * 获取自定义状态码
     * Get Custom Status Code
     * 返回一个字符串类型的值，表示响应的状态码。
     * Returns a string value representing the status code of the response.
     * 状态码由开发者自定义，用于标识操作的成功或具体错误类型。
     * The status code is user-defined and used to indicate the success of an operation or the specific type of error.
     *
     * @return 自定义状态码 / Custom status code
     */
    String code();

    /**
     * 获取自定义提示信息
     * Get Custom Message
     * 返回一个字符串类型的值，表示响应的提示信息。
     * Returns a string value representing the message of the response.
     * 该信息通常用于向用户或调用者说明操作的结果或错误原因。
     * This message is typically used to inform the user or caller about the result of the operation or the reason for an error.
     *
     * @return 自定义提示信息 / Custom message
     */
    String message();
}