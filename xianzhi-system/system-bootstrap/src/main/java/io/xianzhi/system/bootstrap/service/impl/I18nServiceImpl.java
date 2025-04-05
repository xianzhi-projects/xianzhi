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
import io.xianzhi.core.result.ListResult;
import io.xianzhi.system.bootstrap.dao.mapper.I18nMapper;
import io.xianzhi.system.bootstrap.service.I18nService;
import io.xianzhi.system.model.page.I18nPage;
import io.xianzhi.system.model.vo.I18nVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 国际化接口实现
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class I18nServiceImpl implements I18nService {

    /**
     * 国际化信息持久层
     */
    private final I18nMapper i18nMapper;

    /**
     * 分页查询国际化列表
     *
     * @param i18nPage 查询条件
     * @return 国际化列表
     */
    @Override
    public ListResult<I18nVO> pageI18nList(I18nPage i18nPage) {
        IPage<I18nVO> i18nPageResult = i18nMapper.pageI18nList(new Page<>(i18nPage.getPageNo(), i18nPage.getPageSize()), i18nPage);


        return null;
    }
}
