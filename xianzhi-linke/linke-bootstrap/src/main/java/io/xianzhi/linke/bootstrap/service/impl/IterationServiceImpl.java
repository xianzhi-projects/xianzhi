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

package io.xianzhi.linke.bootstrap.service.impl;

import io.xianzhi.linke.bootstrap.dao.mapper.IterationMapper;
import io.xianzhi.linke.bootstrap.service.IterationService;
import io.xianzhi.linke.model.dto.IterationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 迭代接口实现
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class IterationServiceImpl implements IterationService {
    /**
     * 迭代持久层
     */
    private final IterationMapper iterationMapper;

    /**
     * 分页查询迭代列表
     *
     * @param iterationDTO 查询条件
     * @return 迭代列表
     */
    @Override
    public String createIteration(IterationDTO iterationDTO) {
        return "";
    }

    /**
     * 更新迭代
     *
     * @param iterationDTO 迭代信息
     */
    @Override
    public void updateIteration(IterationDTO iterationDTO) {

    }
}
