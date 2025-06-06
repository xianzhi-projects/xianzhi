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

package io.xianzhi.linke.bootstrap.controller;

import io.xianzhi.core.result.ListResult;
import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.linke.bootstrap.service.IterationService;
import io.xianzhi.linke.model.dto.IterationDTO;
import io.xianzhi.linke.model.page.IterationPage;
import io.xianzhi.linke.model.vo.IterationVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 迭代接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/l/iteration")
public class IterationController {
    /**
     * 迭代接口
     */
    private final IterationService iterationService;

    /**
     * 分页查询迭代列表
     *
     * @param iterationPage 查询条件
     * @return 迭代列表
     */
    @PostMapping(value = "/pageIterationList")
    public ResponseResult<ListResult<IterationVO>> pageIterationList(@RequestBody IterationPage iterationPage) {
        return ResponseResult.success();
    }

    /**
     * 创建迭代
     *
     * @param iterationDTO 迭代信息
     * @return 创建结果
     */
    @PostMapping(value = "/createIteration")
    public ResponseResult<String> createIteration(@RequestBody IterationDTO iterationDTO) {
        return ResponseResult.success(iterationService.createIteration(iterationDTO));
    }

    /**
     * 更新迭代
     *
     * @param iterationDTO 迭代信息
     * @return 更新结果
     */
    @PostMapping(value = "/updateIteration")
    public ResponseResult<Object> updateIteration(@RequestBody IterationDTO iterationDTO) {
        iterationService.updateIteration(iterationDTO);
        return ResponseResult.success();
    }


}
