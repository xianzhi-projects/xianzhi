package io.xianzhi.code.bootstrap.server.http.hadler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.http.server.ReceivePackErrorHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 错误处理
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class XianZhiReceivePackErrorHandler implements ReceivePackErrorHandler {
    /**
     * Receive pack
     *
     * @param req The HTTP request
     * @param rsp The HTTP response
     * @param r   A continuation that handles a git-receive-pack request.
     * @throws IOException if an IO error occurred
     * @since 7.0
     */
    @Override
    public void receive(HttpServletRequest req, HttpServletResponse rsp, ReceivePackRunnable r) throws IOException {

    }
}
