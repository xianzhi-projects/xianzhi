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

import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.linke.model.vo.ReleaseOrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发布单接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/l/releaseOrder")
public class ReleaseOrderController {

    /**
     * 查询发布单详情
     *
     * @param id 发布单ID
     * @return 响应信息
     */
    public ResponseResult<ReleaseOrderVO> getReleaseOrderById(Long id) {
        return ResponseResult.success();
    }
}
