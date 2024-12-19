package io.xianzhi.core.content;

import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.exception.BusinessException;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * trace上下文处理
 *
 * @author Max
 * @since 1.0.0
 */
public class TraceContextHolder {

    /**
     * 获取traceId
     */
    private final static ThreadLocal<String> TRACE_ID = new ThreadLocal<>();


    /**
     * 设置traceId
     *
     * @param traceId traceId
     */
    public static void set(String traceId) {
        if (StringUtils.hasText(traceId)) {
            TRACE_ID.set(traceId);
            return;
        }
        throw new BusinessException(CommonCode.PARAM_CHECK_ERROR);

    }

    /**
     * 获取traceId
     *
     * @return traceId
     */
    public static Optional<String> get() {
        return Optional.ofNullable(TRACE_ID.get());
    }


    /**
     * 移除traceId
     */
    public static void remove() {
        TRACE_ID.remove();
    }
}
