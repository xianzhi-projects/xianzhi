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

package io.xianzhi.core.code;

import io.xianzhi.core.result.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 公共响应状态码
 * <p>
 * 此枚举定义了整个应用程序中用于指示的通用状态代码
 * 操作的结果，如成功、失败或错误。这些状态代码是 非HTTP 状态代码，用于自定义应用程序响应。
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum CommonCode implements Result {


    /**
     * 参数检查失败
     * 表示操作因参数无效而失败。
     *
     * @since 1.0.0
     */
    PARAMS_CHECK_FAIL("0000000", false, "params.check.fail"),


    /**
     * 操作成功状态码
     *
     * @since 1.0.0
     */
    SUCCESS("200", true, "operation.success"),

    /**
     * 操作失败状态码
     *
     * @since 1.0.0
     */
    FAILURE("500", false, "operation.failed"),

    /**
     * 系统错误状态码，表示未知错误
     *
     * @since 1.0.0
     */
    ERROR("-1", false, "system.error"),
    /**
     * 未经授权或会话已过期
     * 表示用户未获得授权或身份验证令牌已过期
     *
     * @since 1.0.0
     */
    UNAUTHORIZED("401", false, "unauthorized"),

    /**
     * 没有足够的权限
     * 表示用户没有执行该操作所需的权限.
     *
     * @since 1.0.0
     */
    FORBIDDEN("403", false, "forbidden"),

    /**
     * 资源不存在
     * 表示访问一个不存在的资源.
     *
     * @since 1.0.0
     */
    NO_RESOURCE_FOUND_EXCEPTION("404", false, "resource.not.found"),

    /**
     * 服务不可用
     * 表示服务暂时不可用.可能是在重启或者升级也有可能服务下线
     *
     * @since 1.0.0
     */
    SERVICE_UNAVAILABLE("503", false, "service.unavailable"),

    /**
     * 不支持的请求方式
     * 表示不支持请求中使用的HTTP方法.比如接口定义的是POST请求，但是实际请求使用的是GET请求
     *
     * @since 1.0.0
     */
    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION("-1", false, "request.method.not.supported"),

    /**
     * 请求正文缺失或为空
     * 没有传递请求参数，无法读取到请求体.
     *
     * @since 1.0.0
     */
    HTTP_MESSAGE_NOT_READABLE_EXCEPTION("-1", false, "http.message.not.readable"),

    /**
     * 请求不是上传文件
     * 表示当前请求的入参不是上传文件.
     *
     * @since 1.0.0
     */
    REQUEST_NOT_MULTIPART("-1", false, "request.not.multipart"),

    /**
     * 不支持的HTTP媒体类型
     * 表示不支持请求的媒体类型。.
     *
     * @since 1.0.0
     */
    HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION("-1", false, "http.media.type.not.supported"),

    /**
     * 缺少请求参数
     * 缺少必要的请求参数.
     *
     * @since 1.0.0
     */
    MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION("-1", false, "missing.servlet.request.parameter"),
    ;

    /**
     * 自定义响应状态码
     */
    private final String code;

    /**
     * 是否操作成功
     */
    private final boolean success;

    /**
     * 自定义提示信息
     */
    private final String message;

    /**
     * 返回自定义状态码
     *
     * @return 自定义状态码
     * @since 1.0.0
     */
    @Override
    public String code() {
        return this.code;
    }

    /**
     * 表示操作是否成功
     *
     * @return {@code true} 表示成功, {@code false} 操作失败
     * @since 1.0.0
     */
    @Override
    public boolean success() {
        return this.success;
    }

    /**
     * 返回自定操作提示信息
     *
     * @return 自定义提示信息
     * @since 1.0.0
     */
    @Override
    public String message() {
        return this.message;
    }
}
