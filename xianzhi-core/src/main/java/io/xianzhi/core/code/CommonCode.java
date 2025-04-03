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

package io.xianzhi.core.code;

import io.xianzhi.core.result.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 公共响应结果
 * Common Response Result
 * 该枚举类定义了一组通用的响应状态码和提示信息，用于标准化的 API 返回结果。
 * This enum class defines a set of common response status codes and messages for standardized API return results.
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum CommonCode implements Result {

    /**
     * 操作成功
     * Operation Successful
     */
    SUCCESS("200", "core.success"),

    /**
     * 操作失败
     * Operation Failed
     */
    FAIL("500", "core.fail"),

    /**
     * 系统错误
     * System Error
     */
    ERROR("XZ-01-00000001", "core.error"),

    /**
     * 未授权，或者身份过期
     * Unauthorized or Identity Expired
     */
    UNAUTHORIZED("XZ-01-00000002", "core.unauthorized"),

    /**
     * 权限不足
     * Insufficient Permissions
     */
    FORBIDDEN("XZ-01-00000003", "core.forbidden"),

    /**
     * 访问资源不存在
     * Resource Not Found
     */
    NO_RESOURCE_FOUND_EXCEPTION("XZ-01-00000004", "core.no.resource.found.exception"),

    /**
     * 服务不可用
     * Service Unavailable
     */
    SERVICE_UNAVAILABLE("XZ-01-00000005", "core.service.unavailable"),

    /**
     * 请求方式不支持
     * HTTP Request Method Not Supported
     */
    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION("XZ-01-00000006", "core.http.request.method.not.supported.exception"),

    /**
     * 无法获取请求体，或者请求体为空
     * HTTP Message Not Readable
     */
    HTTP_MESSAGE_NOT_READABLE_EXCEPTION("XZ-01-00000007", "core.http.message.not.readable.exception"),

    /**
     * 请求不是上传文件
     * Request Not Multipart
     */
    REQUEST_NOT_MULTIPART("XZ-01-000000008.01", "core.request.not.multipart"),

    /**
     * HTTP媒体类型不支持异常
     * HTTP Media Type Not Supported Exception
     */
    HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION("XZ-01-000000008.02", "core.http.media.type.not.supported.exception"),

    /**
     * 缺少请求参数异常
     * Missing Servlet Request Parameter Exception
     */
    MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION("XZ-01-000000008.03", "core.missing.servlet.request.parameter.exception"),

    /**
     * 重复请求
     * Duplicate Request
     */
    DUPLICATE_REQUEST("XZ-01-000000009", "core.duplicate.request"),

    /**
     * 请求重复，请稍后重试！
     * Idempotent Request, Please Retry Later!
     */
    IDEMPOTENT_REQUEST("XZ-01-000000010", "core.idempotent.request"),

    /**
     * 参数错误，缺少参数
     * Parameter Check Error, Missing Parameter
     */
    PARAM_CHECK_ERROR("XZ-01-000000011", "core.param.check.error"),

    ;

    /**
     * 自定义状态码
     * Custom Status Code
     */
    private final String code;

    /**
     * 自定义提示信息
     * Custom Message
     */
    private final String message;

    /**
     * 获取自定义状态码
     * Get Custom Status Code
     * 返回当前枚举实例的状态码，实现 Result 接口的方法。
     * Returns the status code of the current enum instance, implementing the Result interface method.
     *
     * @return 自定义状态码 / Custom status code
     */
    @Override
    public String code() {
        return this.code;
    }

    /**
     * 获取自定义提示信息
     * Get Custom Message
     * 返回当前枚举实例的提示信息，实现 Result 接口的方法。
     * Returns the message of the current enum instance, implementing the Result interface method.
     *
     * @return 自定义提示信息 / Custom message
     */
    @Override
    public String message() {
        return this.message;
    }
}
