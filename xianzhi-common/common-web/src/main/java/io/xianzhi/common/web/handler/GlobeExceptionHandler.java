package io.xianzhi.common.web.handler;

import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.exception.BusinessException;
import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.core.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Objects;

/**
 * 全局异常处理
 *
 * @author Max
 * @since 1.0.0
 */

@Slf4j
@RestControllerAdvice
public class GlobeExceptionHandler {

    /**
     * 处理未知异常
     *
     * @param exception 未知异常
     * @return 响应结果
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseResult<Object> exception(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResponseResult.fail(CommonCode.ERROR);
    }

    /**
     * 处理业务异常
     *
     * @param exception 业务异常
     * @return 响应结果
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResponseResult<Object> businessException(BusinessException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseResult.fail(exception.getResult());
    }

    /**
     * 处理空指针异常
     *
     * @param exception 空指针异常
     * @return 响应结果
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseResult<Object> nullPointerException(NullPointerException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseResult.fail(CommonCode.ERROR);
    }

    /**
     * 请求方式不支持异常
     *
     * @param exception 请求方式不支持异常
     * @return 响应信息
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseResult<Object> httpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseResult.fail(CommonCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION);
    }

    /**
     * 无法读取请求体异常
     *
     * @param exception 无法读取请求体异常
     * @return 响应信息
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseResult<Object> httpMessageNotReadableException(HttpMessageNotReadableException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseResult.fail(CommonCode.HTTP_MESSAGE_NOT_READABLE_EXCEPTION);
    }

    /**
     * 请求不是上传文件处理
     *
     * @param exception exception
     * @return 响应信息
     */
    @ExceptionHandler(value = MultipartException.class)
    public ResponseResult<Object> multipartException(MultipartException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseResult.fail(CommonCode.REQUEST_NOT_MULTIPART);
    }

    /**
     * 请求参数异常
     *
     * @param exception exception
     * @return 响应信息
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseResult<Object> missingServletRequestParameterException(
            MissingServletRequestParameterException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseResult.fail(CommonCode.MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION);
    }

    /**
     * 未找到资源异常
     * @param exception 未找到资源异常
     * @return 响应信息
     */
    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseResult<Object> noResourceFoundException(NoResourceFoundException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseResult.fail(CommonCode.NO_RESOURCE_FOUND_EXCEPTION);
    }

    /**
     * 参数校验异常
     *
     * @param exception 参数校验异常
     * @return 响应信息
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseResult<Object> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage(), exception);
        String defaultMessage = Objects.requireNonNull(exception.getBindingResult().getFieldError())
                .getDefaultMessage();
        return ResponseResult.fail(new Result() {
            @Override
            public String code() {
                return CommonCode.FAIL.code();
            }


            @Override
            public String message() {
                return defaultMessage;
            }
        });
    }

    /**
     * 处理IllegalArgumentException异常
     *
     * @param exception 正则异常
     * @return 响应信息
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseResult<Object> IllegalArgumentException(IllegalArgumentException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseResult.fail(new Result() {
            @Override
            public String code() {
                return CommonCode.PARAM_CHECK_ERROR.code();
            }


            @Override
            public String message() {
                return CommonCode.PARAM_CHECK_ERROR.message();
            }
        });
    }
}
