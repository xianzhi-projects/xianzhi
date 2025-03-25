package io.xianzhi.code.bootstrap.server.http.hadler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.http.server.UploadPackErrorHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class XianZhiUploadPackErrorHandler implements UploadPackErrorHandler {
    /**
     * Upload pack
     *
     * @param req The HTTP request
     * @param rsp The HTTP response
     * @param r   A continuation that handles a git-upload-pack request.
     * @throws IOException if an IO error occurred
     * @since 7.0
     */
    @Override
    public void upload(HttpServletRequest req, HttpServletResponse rsp, UploadPackRunnable r) throws IOException {

    }
}
