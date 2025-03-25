/*
 *  Copyright 2025 XianZhi Group .
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.xianzhi.code.bootstrap.handler;

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
