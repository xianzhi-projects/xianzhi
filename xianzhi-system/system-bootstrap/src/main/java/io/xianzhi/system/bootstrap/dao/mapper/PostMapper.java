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
import io.xianzhi.system.bootstrap.dao.dataobj.PostDO;
import io.xianzhi.system.model.page.PostPage;
import io.xianzhi.system.model.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * 岗位持久层
 *
 * @author Max
 * @since 1.0.0
 */
@Mapper
public interface PostMapper extends BaseMapper<PostDO> {

    /**
     * 根据ID查询岗位
     *
     * @param id 岗位ID
     * @return 岗位信息
     */
    Optional<PostDO> selectPostById(@Param("id") String id);

    /**
     * 判断岗位名称是否存在
     *
     * @param postName 岗位名称
     * @param id       岗位ID
     * @return 是否存在
     */
    boolean existsPostByPostNameAnIdNot(@Param("postName") String postName, @Param("id") String id);

    /**
     * 分页查询岗位列表
     * @param postVOPage 分页查询参数
     * @param page 查询条件
     * @return 岗位列表
     */
    IPage<PostVO> pagePostList(Page<PostVO> postVOPage, @Param("page") PostPage page);
}
