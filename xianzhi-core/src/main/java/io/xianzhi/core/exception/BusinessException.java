package io.xianzhi.core.exception;

import io.xianzhi.core.result.Result;

/**
 * 自定义业务异常
 *
 * @author Max
 * @since 1.0.0
 */
public class BusinessException extends RuntimeException {



    /**
     * 响应结果
     */
    private final Result result;


    /**
     * Constructs a new runtime exception with {@code null} as its detail message. The
     * cause is not initialized, and may subsequently be initialized by a call to
     * {@link #initCause}.
     */
    public BusinessException(Result result) {
        super(result.message());
        this.result = result;
    }


    public BusinessException(String message) {
        super(message);
        this.result = new Result() {
            @Override
            public String code() {
                return "500";
            }

            @Override
            public String message() {
                return message;
            }
        };
    }


    public BusinessException(String code, String message) {
        super(message);
        this.result = new Result() {
            @Override
            public String code() {
                return code;
            }

            @Override
            public String message() {
                return message;
            }
        };
    }
}
