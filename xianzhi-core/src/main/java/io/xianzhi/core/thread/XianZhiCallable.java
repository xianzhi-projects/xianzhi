package io.xianzhi.core.thread;

import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.content.Context;
import io.xianzhi.core.content.ContextHolder;
import io.xianzhi.core.exception.BusinessException;
import lombok.Getter;

import java.util.concurrent.Callable;

/**
 * 自定义Callable
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
public class XianZhiCallable<T> implements Callable<T> {

    /**
     * 任务
     */
    private final Callable<T> call;
    /**
     * 上下文
     */
    private final Context context;


    /**
     * 构造方法
     *
     * @param call 任务
     */
    public XianZhiCallable(Callable<T> call) {
        this.call = call;
        this.context = ContextHolder.getContent().orElseThrow(() -> new BusinessException(CommonCode.UNAUTHORIZED));
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public T call() throws Exception {
        try {
            ContextHolder.set(context);
            return call.call();
        } finally {
            ContextHolder.remove();
        }
    }
}
