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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.xianzhi.core.exception.BusinessException;
import io.xianzhi.core.result.ListResult;
import io.xianzhi.system.bootstrap.dao.dataobj.DictDO;
import io.xianzhi.system.bootstrap.dao.dataobj.DictItemDO;
import io.xianzhi.system.bootstrap.dao.mapper.DictItemMapper;
import io.xianzhi.system.bootstrap.dao.mapper.DictMapper;
import io.xianzhi.system.bootstrap.dao.mapper.UserMapper;
import io.xianzhi.system.bootstrap.service.DictService;
import io.xianzhi.system.model.dto.DictDTO;
import io.xianzhi.system.model.dto.DictItemDTO;
import io.xianzhi.system.model.page.DictPage;
import io.xianzhi.system.model.vo.DictItemVO;
import io.xianzhi.system.model.vo.DictVO;
import io.xianzhi.system.model.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

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
     * 用户信息持久层
     */
    private final UserMapper userMapper;

    /**
     * 分页查询字典列表
     *
     * @param dictPage 分页查询参数
     * @return 字典列表
     */
    @Override
    public ListResult<DictVO> pageDictList(DictPage dictPage) {
        IPage<DictDO> result = dictMapper.pageDictList(new Page<>(dictPage.getPageNo(), dictPage.getPageSize()), dictPage);
        List<DictDO> records = result.getRecords();
        if (ObjectUtils.isEmpty(records)) {
            return ListResult.empty();
        }
        List<String> userIds = records.stream().map(DictDO::getCreateBy).distinct().toList();
        List<UserVO> users = userMapper.selectSimpleUserList(userIds);
        List<DictVO> list = records.stream().map(item -> {
            DictVO dictVO = new DictVO();
            dictVO.setId(item.getId());
            dictVO.setDictCode(item.getDictCode());
            dictVO.setDictName(item.getDictName());
            dictVO.setDictDesc(item.getDictDesc());
//            dictVO.setCreateBy(users.stream().filter(user -> user.getId().equals(item.getCreateBy())).findFirst().orElse(null));
            dictVO.setCreateAt(item.getCreateAt());
            return dictVO;
        }).toList();

        return ListResult.of(list, result.getTotal());
    }

    /**
     * 新增字典信息
     *
     * @param dictDTO 字典信息入参
     * @return 字典ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createDict(DictDTO dictDTO) {
        DictDO dictDO = checkedDictDTO(dictDTO);
        dictMapper.insert(dictDO);
        return dictDO.getId();
    }

    /**
     * 更新字典信息
     *
     * @param dictDTO 字典信息入参
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDict(DictDTO dictDTO) {
        DictDO dictDO = checkedDictDTO(dictDTO);
        dictMapper.updateById(dictDO);

    }

    /**
     * 删除字典
     *
     * @param ids 字典ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletedDict(List<String> ids) {

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
    @Transactional(rollbackFor = Exception.class)
    public String createDictItem(DictItemDTO dictItemDTO) {
        DictItemDO dictItemDO = checkedDictItemDTO(dictItemDTO);
        dictItemMapper.insert(dictItemDO);
        return dictItemDO.getId();
    }

    /**
     * 更新字典项
     *
     * @param dictItemDTO 字典项信息入参
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDictItem(DictItemDTO dictItemDTO) {
        DictItemDO dictItemDO = checkedDictItemDTO(dictItemDTO);
        dictItemMapper.updateById(dictItemDO);
    }

    /**
     * 删除字典项
     *
     * @param ids 字典项ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletedDictItem(List<String> ids) {
        if (!ObjectUtils.isEmpty(ids)) {
            dictItemMapper.deletedDictItem(ids);
        }
    }

    /**
     * 检查字典信息入参
     *
     * @param dictDTO 字典信息入参
     * @return 字典信息
     */
    private DictDO checkedDictDTO(DictDTO dictDTO) {
        DictDO dict;
        if (StringUtils.hasText(dictDTO.getId())) {
            dict = dictMapper.selectDictById(dictDTO.getId()).orElseThrow(() -> new BusinessException("字典不存在"));
        } else {
            dict = new DictDO();
            if (dictMapper.existsDictByDictCode(dictDTO.getDictCode())) {
                throw new BusinessException("字典编码已存在");
            }
            dict.setDictCode(dictDTO.getDictCode());
        }
        if (dictMapper.existsDictByDictNameAndIdNot(dictDTO.getDictName(), dict.getId())) {
            throw new BusinessException("字典名称已存在");
        }
        dict.setDictName(dictDTO.getDictName());
        dict.setDictDesc(dictDTO.getDictDesc());
        return dict;
    }

    /**
     * 检查字典项信息入参
     *
     * @param dictItemDTO 字典项信息入参
     * @return 字典项信息
     */
    private DictItemDO checkedDictItemDTO(DictItemDTO dictItemDTO) {
        DictDO dict = dictMapper.selectDictById(dictItemDTO.getId()).orElseThrow(() -> new BusinessException("字典不存在"));
        DictItemDO dictItem;
        if (StringUtils.hasText(dictItemDTO.getId())) {
            dictItem = dictItemMapper.selectDictItemById(dictItemDTO.getId()).orElseThrow(() -> new BusinessException("字典项不存在"));
        } else {
            dictItem = new DictItemDO();
            dictItem.setDictCode(dict.getDictCode());
        }
        if (dictItemMapper.existsDictItemByDictIdAndItemNameAndIdNot(dictItemDTO.getDictId(), dictItemDTO.getItemName(), dictItem.getId())) {
            throw new BusinessException("字典项值已存在");
        }
        dictItem.setDictId(dictItemDTO.getDictId());
        dictItem.setItemValue(dictItemDTO.getItemValue());
        dictItem.setItemName(dictItemDTO.getItemName());
        return dictItem;
    }
}
