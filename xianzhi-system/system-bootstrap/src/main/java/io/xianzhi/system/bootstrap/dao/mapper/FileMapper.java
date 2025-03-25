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
import io.xianzhi.system.bootstrap.dao.dataobj.FileDO;
import io.xianzhi.system.model.page.FilePage;
import io.xianzhi.system.model.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * 文件持久层
 *
 * @author Max
 * @since 1.0.0
 */
@Mapper
public interface FileMapper extends BaseMapper<FileDO> {


    /**
     * 根据文件ID查询文件信息
     *
     * @param id 文件ID
     * @return 文件信息
     */
    Optional<FileDO> selectFileById(@Param("id") String id);

    /**
     * 查询文件列表
     *
     * @param fileVOPage 分页条件
     * @param filePage   查询条件
     * @return 文件列表
     */
    IPage<FileVO> pageFileList(Page<FileVO> fileVOPage, @Param("filePage") FilePage filePage);
}
