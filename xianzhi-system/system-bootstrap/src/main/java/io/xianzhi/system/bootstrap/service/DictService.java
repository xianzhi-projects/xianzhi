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

package io.xianzhi.system.bootstrap.service;

import io.xianzhi.core.result.ListResult;
import io.xianzhi.system.model.dto.DictDTO;
import io.xianzhi.system.model.dto.DictItemDTO;
import io.xianzhi.system.model.page.DictPage;
import io.xianzhi.system.model.vo.DictItemVO;
import io.xianzhi.system.model.vo.DictVO;

import java.util.List;

/**
 * 字典接口
 *
 * @author Max
 * @since 1.0.0
 */
public interface DictService {
    /**
     * 分页查询字典列表
     *
     * @param dictPage 分页查询参数
     * @return 字典列表
     */
    ListResult<DictVO> pageDictList(DictPage dictPage);
    /**
     * 新增字典信息
     *
     * @param dictDTO 字典信息入参
     * @return 字典ID
     */
    String createDict(DictDTO dictDTO);
    /**
     * 更新字典信息
     *
     * @param dictDTO 字典信息入参
     */
    void updateDict(DictDTO dictDTO);
    /**
     * 删除字典
     *
     * @param ids 字典ID
     */
    void deletedDict(List<Long> ids);
    /**
     * 根据字典ID查询字典项
     *
     * @param dictId 字典ID
     * @return 字典项信息
     */
    List<DictItemVO> listItemByDictId(String dictId);
    /**
     * 新增字典项
     *
     * @param dictItemDTO 字典项信息入参
     * @return 字典项ID
     */
    String createDictItem(DictItemDTO dictItemDTO);
    /**
     * 更新字典项
     *
     * @param dictItemDTO 字典项信息入参
     */
    void updateDictItem(DictItemDTO dictItemDTO);
    /**
     * 删除字典项
     *
     * @param ids 字典项ID
     */
    void deletedDictItem(List<String> ids);
}
