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

import io.xianzhi.core.result.ListResult;
import io.xianzhi.linke.bootstrap.service.AppService;
import io.xianzhi.linke.model.dto.AppDTO;
import io.xianzhi.linke.model.page.AppPage;
import io.xianzhi.linke.model.vo.AppVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 应用接口实现
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AppServiceImpl implements AppService {
    /**
     * 分页查询应用列表
     *
     * @param appPage 查询条件
     * @return 应用列表
     */
    @Override
    public ListResult<AppVO> pageAppList(AppPage appPage) {
        return null;
    }

    /**
     * 新增应用 (幂等)
     *
     * @param appDTO 应用信息入参
     * @return 应用id
     */
    @Override
    public String createApp(AppDTO appDTO) {
        return "";
    }

    /**
     * 更新应用
     *
     * @param appDTO 应用信息入参
     */
    @Override
    public void updateApp(AppDTO appDTO) {

    }

    /**
     * 删除应用
     *
     * @param id 应用id
     */
    @Override
    public void deletedApp(String id) {

    }

    /**
     * 下线应用
     *
     * @param id 应用id
     */
    @Override
    public void offlineApp(String id) {

    }

    /**
     * 应用持久层
     */
//    private final AppMapper appMapper;



}
