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

package io.xianzhi.code.bootstrap.filter;

import io.xianzhi.code.bootstrap.properties.CodeServerProperties;
import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.exception.BusinessException;
import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.system.facade.SystemParamFacade;
import io.xianzhi.system.model.enums.SystemParamEnum;
import io.xianzhi.system.model.vo.SystemParamVO;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.http.server.GitServlet;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class GitFilter implements Filter {
    /**
     * GitServlet 实例，用于处理 Git 请求
     */
    private final GitServlet gitServlet;
    /**
     * CodeServerProperties 实例，用于获取配置属性
     */
    private final CodeServerProperties codeServerProperties;
    /**
     * 系统服务参数
     */
    private final SystemParamFacade systemParamFacade;


    /**
     * The <code>doFilter</code> method of the Filter is called by the container each time a request/response pair is
     * passed through the chain due to a client request for a resource at the end of the chain. The FilterChain passed
     * in to this method allows the Filter to pass on the request and response to the next entity in the chain.
     * <p>
     * A typical implementation of this method would follow the following pattern:- <br>
     * 1. Examine the request<br>
     * 2. Optionally wrap the request object with a custom implementation to filter content or headers for input
     * filtering <br>
     * 3. Optionally wrap the response object with a custom implementation to filter content or headers for output
     * filtering <br>
     * 4. a) <strong>Either</strong> invoke the next entity in the chain using the FilterChain object
     * (<code>chain.doFilter()</code>), <br>
     * 4. b) <strong>or</strong> not pass on the request/response pair to the next entity in the filter chain to block
     * the request processing<br>
     * 5. Directly set headers on the response after invocation of the next entity in the filter chain.
     *
     * @param request  The request to process
     * @param response The response associated with the request
     * @param chain    Provides access to the next filter in the chain for this filter to pass the request and response
     *                 to for further processing
     * @throws IOException      if an I/O error occurs during this filter's processing of the request
     * @throws ServletException if the processing fails for any other reason
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String pathInfo = req.getPathInfo() != null ? req.getPathInfo() : req.getServletPath();
        log.info("GitFilter pathInfo: {}", pathInfo);
        ResponseResult<SystemParamVO> result = systemParamFacade.getSystemParamByParamCode(SystemParamEnum.CODE_SERVER_URL.getCode());
        String codeServerUrl = null;
        if (result.code().equals(CommonCode.SUCCESS.code()) && result.getData() != null) {
            codeServerUrl = result.getData().getParamValue();
        } else {
            throw new BusinessException(CommonCode.ERROR);
        }
        if (pathInfo != null && pathInfo.endsWith(".git")) {
            pathInfo = pathInfo.replace(".git", "");
            log.info("Forwarding Git request: {}", pathInfo);
            resp.sendRedirect(codeServerUrl + pathInfo);
            return;
        }


        if (pathInfo != null && pathInfo.endsWith(".git/info/refs")) {
            log.info("Forwarding Git request: {}", pathInfo);
            gitServlet.service(req, resp);
        } else {
            log.info("Not a Git request, passing to next filter: {}", pathInfo);
            chain.doFilter(request, response);
        }
    }
}
