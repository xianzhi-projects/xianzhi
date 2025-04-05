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
import io.xianzhi.system.model.dto.I18nDTO;
import io.xianzhi.system.model.dto.I18nItemDTO;
import io.xianzhi.system.model.page.I18nPage;
import io.xianzhi.system.model.vo.I18nItemVO;
import io.xianzhi.system.model.vo.I18nVO;

import java.util.List;

/**
 * 国际化接口
 *
 * @author Max
 * @since 1.0.0
 */
public interface I18nService {
    /**
     * 分页查询国际化列表
     *
     * @param i18nPage 查询条件
     * @return 国际化列表
     */
    ListResult<I18nVO> pageI18nList(I18nPage i18nPage);

    /**
     * 新增国际化
     *
     * @param i18nDTO 国际化对象
     * @return 国际化ID
     */
    String createI18n(I18nDTO i18nDTO);

    /**
     * 更新国际化
     *
     * @param i18nDTO 国际化对象
     */
    void updateI18n(I18nDTO i18nDTO);

    /**
     * 删除国际化
     *
     * @param ids 国际化ID列表
     */
    void deletedI18n(List<String> ids);

    /**
     * 查询国际化项列表
     *
     * @param i18nId 国际化ID
     * @return 国际化项列表
     */
    ListResult<I18nItemVO> getI18nItemList(String i18nId);

    /**
     * 新增国际化项
     *
     * @param i18nItemDTO 国际化项对象
     * @return 国际化项ID
     */
    String createI18nItem(I18nItemDTO i18nItemDTO);

    /**
     * 更新国际化项
     *
     * @param i18nItemDTO 国际化项对象
     */
    void updateI18nItem(I18nItemDTO i18nItemDTO);

    /**
     * 删除国际化项
     *
     * @param ids 国际化项ID列表
     */
    void deletedI18nItem(List<String> ids);

}
