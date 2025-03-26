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

package io.xianzhi.system.bootstrap.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.xianzhi.system.bootstrap.dao.dataobj.OperationLogDO;
import io.xianzhi.system.model.page.OperationLogPage;
import io.xianzhi.system.model.vo.OperationLogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 操作日志持久层
 *
 * @author Max
 * @since 1.0.0
 */
@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLogDO> {
    /**
     * 分页查询操作日志列表
     *
     * @param page             分页查询参数
     * @param operationLogPage 操作日志查询条件
     * @return 操作日志列表
     */
    IPage<OperationLogVO> pageOperationLogList(Page<OperationLogVO> page, @Param("operationLogPage") OperationLogPage operationLogPage);
}
