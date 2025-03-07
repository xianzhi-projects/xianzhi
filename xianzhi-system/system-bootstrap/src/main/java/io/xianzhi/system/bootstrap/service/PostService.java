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
import io.xianzhi.system.model.dto.PostDTO;
import io.xianzhi.system.model.page.PostPage;
import io.xianzhi.system.model.vo.PostVO;

import java.util.List;

/**
 * 岗位接口
 *
 * @author Max
 * @since 1.0.0
 */
public interface PostService {
    /**
     * 分页查询岗位列表
     *
     * @param page 查询条件
     * @return 岗位列表
     */
    ListResult<PostVO> pagePostList(PostPage page);

    /**
     * 新增岗位
     *
     * @param postDTO 岗位信息入参
     * @return 岗位ID
     */
    String createPost(PostDTO postDTO);

    /**
     * 修改岗位
     *
     * @param postDTO 岗位信息入参
     */
    void updatePost(PostDTO postDTO);

    /**
     * 删除岗位
     *
     * @param ids 岗位ID
     */
    void deletedPost(List<String> ids);

}
