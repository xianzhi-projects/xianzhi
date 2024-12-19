package io.xianzhi.core.content;

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



    public static void set(Content content) {
        if (null == content){
            throw new Ill
        }
        CONTENT.set(content);
    }


}
