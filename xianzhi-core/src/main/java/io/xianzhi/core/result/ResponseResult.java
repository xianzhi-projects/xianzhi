package io.xianzhi.core.result;

import io.xianzhi.core.utils.SpringUtils;
import io.xianzhi.core.utils.TraceIdUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * 响应结果类
 *
 * @author Max
 * @since 1.0.0
 */

@Slf4j
@Getter
public class ResponseResult<R> implements Result, Serializable {


    /**
     * 返回的数据
     */
    private final R data;

    /**
     * 本次请求的TraceId
     */
    private final String traceId;
    /**
     * 自定义状态码
     */
    private final String code;
    /**
     * 自定义提示信息
     */
    private final String message;

    /**
     * 构造函数
     *
     * @param result 响应信息
     * @param data   返回的数据
     */
    public ResponseResult(Result result, R data) {
        this.code = result.code();
        String message = result.message();
        Map<String, MessageSource> messageSourceMap = SpringUtils.getBeansOfType(MessageSource.class);
        if (!ObjectUtils.isEmpty(messageSourceMap)) {
            MessageSource messageSource = messageSourceMap.get("dynamicMessageSource");
            if (null != messageSource) {
                message = messageSource.getMessage(result.message(), null, LocaleContextHolder.getLocale());
            }
        }
        this.message = message;
        this.data = data;
        this.traceId = TraceIdUtils.getTraceId();
    }

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
