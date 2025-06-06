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

package io.xianzhi.code.bootstrap.service;

import io.xianzhi.code.model.dto.LabelDTO;
import io.xianzhi.code.model.page.LabelPage;
import io.xianzhi.code.model.vo.LabelVO;
import io.xianzhi.core.result.ListResult;

/**
 * Label接口
 *
 * @author Max
 * @since 1.0.0
 */
public interface LabelService {
    /**
     * 分页查询标签列表
     *
     * @param labelPage 分页查询参数
     * @return 标签列表
     */
    ListResult<LabelVO> pageLabelList(LabelPage labelPage);

    /**
     * 创建标签  (幂等)
     *
     * @param labelDTO 标签信息入参
     * @return 标签ID
     */
    String createLabel(LabelDTO labelDTO);

    /**
     * 更新标签
     *
     * @param labelDTO 标签信息入参
     */
    void updateLabel(LabelDTO labelDTO);

    /**
     * 删除标签
     *
     * @param id 标签ID
     */
    void deletedLabel(String id);

}
