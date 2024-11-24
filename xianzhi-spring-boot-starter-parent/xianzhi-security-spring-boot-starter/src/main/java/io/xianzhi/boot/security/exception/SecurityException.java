package io.xianzhi.boot.security.exception;

import io.xianzhi.core.result.Result;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

/**
 * 安全异常
 *
 * @author Max
 * @since 1.0.0
 */
@Getter
public class SecurityException extends AuthenticationException {

    /**
     * 响应信息
     *
     * @since 1.0.0
     */
    private final Result result;

    /**
     * 构造信息
     *
     * @param result 响应信息
     * @since 1.0.0
     */
    public SecurityException(Result result) {
        super(result.message());
        this.result = result;
    }
}
