

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

import io.xianzhi.common.redis.RedisHandler;
import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.core.utils.JSONUtils;
import io.xianzhi.core.utils.ResponseUtils;
import io.xianzhi.system.model.dto.LoginLogDTO;
import io.xianzhi.system.model.vo.TokenVO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 认真成功处理
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class XianZhiAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    /**
     * RocketMQTemplate
     */
    private final RocketMQTemplate rocketMQTemplate;
    /**
     * 缓存处理
     */
    private final RedisHandler redisHandler;


    private TokenVO getTokenVO(OAuth2AccessTokenAuthenticationToken auth2AccessTokenAuthenticationToken, OAuth2AccessToken accessToken, OAuth2RefreshToken refreshToken) {
        Map<String, Object> additionalParameters = auth2AccessTokenAuthenticationToken.getAdditionalParameters();
        TokenVO tokenVO = new TokenVO();
        tokenVO.setAccessToken(accessToken.getTokenValue());
        if (null != refreshToken) {
            tokenVO.setRefreshToken(refreshToken.getTokenValue());
        }
        return tokenVO;
    }

    /**
     * Called when a user has been successfully authenticated.
     *
     * @param request        the request which caused the successful authentication
     * @param response       the response
     * @param authentication the Authentication object which was created during
     *                       the authentication process.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("认证成功处理:{}", JSONUtils.toJSONString(authentication));
        OAuth2AccessTokenAuthenticationToken auth2AccessTokenAuthenticationToken = (OAuth2AccessTokenAuthenticationToken) authentication;
        OAuth2AccessToken accessToken = auth2AccessTokenAuthenticationToken.getAccessToken();
        OAuth2RefreshToken refreshToken = auth2AccessTokenAuthenticationToken.getRefreshToken();
        TokenVO tokenVO = getTokenVO(auth2AccessTokenAuthenticationToken, accessToken, refreshToken);
        ResponseUtils.responseUtf8(ResponseResult.success(tokenVO), response);
        LoginLogDTO loginLogDTO = new LoginLogDTO();
        Message<LoginLogDTO> build = MessageBuilder.withPayload(loginLogDTO)
                .build();
        rocketMQTemplate.asyncSend("SYS_USER", build, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("发送成功:{}", JSONUtils.toJSONString(sendResult));
            }

            @Override
            public void onException(Throwable e) {
                log.error("发送失败:{}", e.getMessage());
            }
        });
    }


}