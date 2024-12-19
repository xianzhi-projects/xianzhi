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
    SUCCESS("200", "common:operation.success"),
    /**
     * 操作失败
     */
    FAIL("500", "common:operation.fail"),
    /**
     * 系统错误
     */
    ERROR("-1", "common:system.error"),
    /**
     * 未授权，或者身份过期
     */
    UNAUTHORIZED("401", "common:unauthorized"),
    /**
     * 权限不足
     */
    FORBIDDEN("403", "common:forbidden"),
    /**
     * 访问资源不存在
     */
    NO_RESOURCE_FOUND_EXCEPTION("404", "common:resource.not.found"),
    /**
     * 服务不可用
     */
    SERVICE_UNAVAILABLE("503", "common:service.unavailable"),
    /**
     * 请求方式不支持
     */
    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION("405", "common:request.method.not.supported"),
    /**
     * 无法获取请求体，或者请求体为空
     */
    HTTP_MESSAGE_NOT_READABLE_EXCEPTION("400", "common:http.message.not.readable"),
    /**
     * 请求不是上传文件
     */
    REQUEST_NOT_MULTIPART("400", "common:request.not.multipart"),
    /**
     * HTTP媒体类型不支持异常
     */
    HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION("400", "common:http.media.type.not.supported"),
    /**
     * 缺少请求参数异常
     */
    MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION("400", "common:missing.servlet.request.parameter"),
    /**
     * 重复请求
     */
    DUPLICATE_REQUEST("409", "common:duplicate.request"),
    /**
     * 标识参数错误，缺少参数
     */
    PARAM_CHECK_ERROR("-2", "common:param.check.error"),
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
