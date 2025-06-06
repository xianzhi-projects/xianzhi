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

package io.xianzhi.system.bootstrap.business;

import io.xianzhi.common.redis.RedisHandler;
import io.xianzhi.system.bootstrap.dao.mapper.DictItemMapper;
import io.xianzhi.system.bootstrap.dao.mapper.DictMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 字典业务类
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DictBusiness {
    /**
     * 字典信息持久层
     */
    private final DictMapper dictMapper;
    /**
     * 字典项持久层
     */
    private final DictItemMapper dictItemMapper;

    /**
     * Redis操作类
     */
    private final RedisHandler redisHandler;

}
