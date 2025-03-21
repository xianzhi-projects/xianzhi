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

package io.xianzhi.system.bootstrap.facade;

import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.system.facade.DictFacade;
import io.xianzhi.system.model.vo.DictItemVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 字典接口实现
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DictFacadeImpl implements DictFacade {
    /**
     * 根据字典编码获取字典项
     *
     * @param dictCode 字典编码
     * @return 字典项
     */
    @Override
    public ResponseResult<DictItemVO> getDictItemByDictCode(String dictCode) {
        return null;
    }
}
