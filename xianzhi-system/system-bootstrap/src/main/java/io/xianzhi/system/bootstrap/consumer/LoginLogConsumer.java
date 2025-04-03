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

package io.xianzhi.system.bootstrap.consumer;

import io.xianzhi.core.utils.JSONUtils;
import io.xianzhi.system.model.dto.LoginLogDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * 登录日志消费者
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "SYS_USER", consumerGroup = "C_system")
public class LoginLogConsumer implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt messageExt) {
        String msgId = messageExt.getMsgId();
        String body = new String(messageExt.getBody(), StandardCharsets.UTF_8);
        LoginLogDTO loginLogDTO = JSONUtils.parseObject(body, LoginLogDTO.class);
        log.info("收到消息 [MsgId: {}], String 格式: {}", msgId, body);

    }
}
