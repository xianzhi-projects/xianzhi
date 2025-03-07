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

package io.xianzhi.system.bootstrap.service.impl;

import io.xianzhi.core.result.ListResult;
import io.xianzhi.system.bootstrap.dao.mapper.DictItemMapper;
import io.xianzhi.system.bootstrap.dao.mapper.DictMapper;
import io.xianzhi.system.bootstrap.service.DictService;
import io.xianzhi.system.model.dto.DictDTO;
import io.xianzhi.system.model.dto.DictItemDTO;
import io.xianzhi.system.model.page.DictPage;
import io.xianzhi.system.model.vo.DictItemVO;
import io.xianzhi.system.model.vo.DictVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典接口实现
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DictServiceImpl implements DictService {

    /**
     * 字典信息持久层
     */
    private final DictMapper dictMapper;
    /**
     * 字典项信息持久层
     */
    private final DictItemMapper dictItemMapper;

    /**
     * 分页查询字典列表
     *
     * @param dictPage 分页查询参数
     * @return 字典列表
     */
    @Override
    public ListResult<DictVO> pageDictList(DictPage dictPage) {
        return null;
    }

    /**
     * 新增字典信息
     *
     * @param dictDTO 字典信息入参
     * @return 字典ID
     */
    @Override
    public String createDict(DictDTO dictDTO) {
        return "";
    }

    /**
     * 更新字典信息
     *
     * @param dictDTO 字典信息入参
     */
    @Override
    public void updateDict(DictDTO dictDTO) {

    }

    /**
     * 删除字典
     *
     * @param ids 字典ID
     */
    @Override
    public void deletedDict(List<Long> ids) {

    }

    /**
     * 根据字典ID查询字典项
     *
     * @param dictId 字典ID
     * @return 字典项信息
     */
    @Override
    public List<DictItemVO> listItemByDictId(String dictId) {
        return List.of();
    }

    /**
     * 新增字典项
     *
     * @param dictItemDTO 字典项信息入参
     * @return 字典项ID
     */
    @Override
    public String createDictItem(DictItemDTO dictItemDTO) {
        return "";
    }

    /**
     * 更新字典项
     *
     * @param dictItemDTO 字典项信息入参
     */
    @Override
    public void updateDictItem(DictItemDTO dictItemDTO) {

    }

    /**
     * 删除字典项
     *
     * @param ids 字典项ID
     */
    @Override
    public void deletedDictItem(List<String> ids) {

    }
}
