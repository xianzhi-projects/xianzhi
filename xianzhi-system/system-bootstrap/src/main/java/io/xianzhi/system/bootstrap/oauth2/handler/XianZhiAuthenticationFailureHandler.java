
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

package io.xianzhi.system.bootstrap.oauth2.handler;


import io.xianzhi.common.oauth2.code.OAuth2Code;
import io.xianzhi.common.oauth2.exception.OAuth2Exception;
import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.core.utils.ResponseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 认证失败处理
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Component
public class XianZhiAuthenticationFailureHandler implements AuthenticationFailureHandler {


    /**
     * Called when an authentication attempt fails.
     *
     * @param request   the request during which the authentication attempt occurred.
     * @param response  the response.
     * @param exception the exception which was thrown to reject the authentication
     *                  request.
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("认证失败处理", exception);
        Throwable cause = exception.getCause();
        if (exception instanceof OAuth2AuthenticationException) {
            if (exception instanceof OAuth2Exception) {
                ResponseUtils.responseUtf8(ResponseResult.fail(((OAuth2Exception) exception).getResult()), response);
                return;
            }
            String message = exception.getMessage();
            if (message.equals("OAuth 2.0 Parameter: grant_type")) {
                ResponseUtils.responseUtf8(ResponseResult.fail(OAuth2Code.INVALID_GRANT_TYPE), response);
                return;
            }
            if (message.equals("Client authentication failed: client_secret")) {
                ResponseUtils.responseUtf8(ResponseResult.fail(OAuth2Code.INVALID_CLIENT_SECRET), response);
                return;
            }
        }

        ResponseUtils.responseUtf8(ResponseResult.fail(CommonCode.ERROR), response);

    }
}