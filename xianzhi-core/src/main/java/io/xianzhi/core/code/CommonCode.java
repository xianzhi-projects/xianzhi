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
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum CommonCode implements Result {

    /**
     * 操作成功
     */
    SUCCESS("200", "操作成功!"),
    /**
     * 操作失败
     */
    FAIL("500", "操作失败!"),
    /**
     * 系统错误
     */
    ERROR("-1", "服务正忙,请稍后重试!"),
    /**
     * 未授权，或者身份过期
     */
    UNAUTHORIZED("401", "未授权,或者身份过期,请重新登录!"),
    /**
     * 权限不足
     */
    FORBIDDEN("403", "没有权限访问该资源!"),
    /**
     * 访问资源不存在
     */
    NO_RESOURCE_FOUND_EXCEPTION("404", "访问资源不可用!"),
    /**
     * 服务不可用
     */
    SERVICE_UNAVAILABLE("503", "服务不可用,请稍后重试"),
    /**
     * 请求方式不支持
     */
    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION("405", "请求方式不支持!"),
    /**
     * 无法获取请求体，或者请求体为空
     */
    HTTP_MESSAGE_NOT_READABLE_EXCEPTION("400", "无法读取请求体!"),
    /**
     * 请求不是上传文件
     */
    REQUEST_NOT_MULTIPART("400", "请求不是上传文件!"),
    /**
     * HTTP媒体类型不支持异常
     */
    HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION("400", "媒体类型不支持!"),
    /**
     * 缺少请求参数异常
     */
    MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION("400", "缺少必要的请求参数,请检查!"),
    /**
     * 重复请求
     */
    DUPLICATE_REQUEST("409", "请求频繁,请稍后重试!"),
    /**
     * 标识参数错误，缺少参数
     */
    PARAM_CHECK_ERROR("-2", "参数检查失败!"),
    ;


    /**
     * 自定义状态码
     */
    private final String code;
    /**
     * 自定义提示信息
     */
    private final String message;

    /**
     * 获取自定义状态码
     *
     * @return 自定义状体码
     */
    @Override
    public String code() {
        return this.code;
    }

    /**
     * 获取自定义提示信息
     *
     * @return 自定义提示信息
     */
    @Override
    public String message() {
        return this.message;
    }
}
