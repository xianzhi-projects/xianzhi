package io.xianzhi.core.content;

import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.exception.BusinessException;

/**
 * 上下文处理
 *
 * @author Max
 * @since 1.0.0
 */
public class ContextHolder {

    /**
     * 上下文内容
     */
    private final static ThreadLocal<Content> CONTENT = new ThreadLocal<>();

    /**
     * 设置上下问内容
     *
     * @param content 上下文内容
     */
    public static void set(Content content) {
        if (null == content) {
            throw new BusinessException(CommonCode.PARAM_CHECK_ERROR);
        }
        CONTENT.set(content);
    }

    /**
     * 获取上下文内容
     *
     * @return 上下文内容
     */
    public static Content getContent() {
        return CONTENT.get();
    }

    /**
     * 移除上下文内容
     */
    public static void remove() {
        CONTENT.remove();
    }
}
