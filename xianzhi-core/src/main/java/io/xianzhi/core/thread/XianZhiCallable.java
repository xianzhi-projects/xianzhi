package io.xianzhi.core.thread;

import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.content.Context;
import io.xianzhi.core.content.ContextHolder;
import io.xianzhi.core.exception.BusinessException;
import lombok.Getter;

import java.util.concurrent.Callable;

/**
 * Custom Callable
 * This class provides a customized implementation of the Callable interface, designed to wrap a
 * task (represented by another Callable) with additional context management. It ensures that a
 * specific context is set before executing the task and cleaned up afterward, enhancing the
 * task's execution with contextual information such as a unique identifier. This is particularly
 * useful in multi-threaded or asynchronous environments where context needs to be preserved.
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
public class XianZhiCallable<T> implements Callable<T> {

    /**
     * Task
     * This field holds the actual Callable task that this class wraps. It represents the core
     * computation or operation to be performed when the call() method is invoked. The generic
     * type T allows the task to return any type of result.
     */
    private final Callable<T> call;

    /**
     * Context
     * This field stores the context associated with the task. The context provides additional
     * information, such as a unique identifier, that may be required during the task's execution.
     * It is set during construction and managed during the task's lifecycle.
     */
    private final Context context;

    /**
     * Constructor
     * This constructor initializes a XianZhiCallable instance with a given Callable task. It
     * retrieves the current context from a ContextHolder, falling back to throwing an exception
     * if no context is available (indicating an unauthorized state). This ensures that the task
     * is always executed within a valid context.
     *
     * @param call The Callable task to be wrapped and executed by this instance.
     * @throws BusinessException if no context is available in the ContextHolder, using the
     *                           UNAUTHORIZED status from CommonCode.
     */
    public XianZhiCallable(Callable<T> call) {
        this.call = call;
        this.context = ContextHolder.getContent().orElseThrow(() -> new BusinessException(CommonCode.UNAUTHORIZED));
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     * This method implements the Callable interface's call() method. It sets the stored context
     * in the ContextHolder before executing the wrapped task, ensuring the task runs with the
     * appropriate contextual information. After execution (successful or not), it removes the
     * context from the ContextHolder to clean up, preventing context leakage in multi-threaded
     * scenarios. The result of the wrapped task is returned, or an exception is propagated if
     * the task fails.
     *
     * @return The result computed by the wrapped Callable task.
     * @throws Exception If the wrapped task is unable to compute a result, the exception is
     *                   thrown as-is to the caller.
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