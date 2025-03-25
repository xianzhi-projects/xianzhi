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
import io.xianzhi.system.bootstrap.dao.dataobj.PostDO;
import io.xianzhi.system.bootstrap.dao.mapper.PostMapper;
import io.xianzhi.system.bootstrap.service.PostService;
import io.xianzhi.system.model.dto.PostDTO;
import io.xianzhi.system.model.page.PostPage;
import io.xianzhi.system.model.vo.PostVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 岗位接口实现
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    /**
     * 岗位持久层
     */
    private final PostMapper postMapper;


    /**
     * 分页查询岗位列表
     *
     * @param page 查询条件
     * @return 岗位列表
     */
    @Override
    public ListResult<PostVO> pagePostList(PostPage page) {
        IPage<PostVO> result = postMapper.pagePostList(new Page<PostVO>(page.getPageNo(),page.getPageSize()), page);
        if (ObjectUtils.isEmpty(result.getRecords())){
            return ListResult.empty();
        }
        return null;
    }

    /**
     * 新增岗位
     *
     * @param postDTO 岗位信息入参
     * @return 岗位ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createPost(PostDTO postDTO) {
        PostDO post = checkedPostDTO(postDTO);
        postMapper.insert(post);
        return post.getId();
    }

    /**
     * 修改岗位
     *
     * @param postDTO 岗位信息入参
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePost(PostDTO postDTO) {
        PostDO post = checkedPostDTO(postDTO);
        postMapper.updateById(post);
    }

    /**
     * 删除岗位
     *
     * @param ids 岗位ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletedPost(List<String> ids) {

    }

    /**
     * 检查岗位信息入参
     *
     * @param postDTO 岗位信息入参
     * @return 岗位信息
     */
    private PostDO checkedPostDTO(PostDTO postDTO) {
        PostDO post;
        if (StringUtils.hasText(postDTO.getId())) {
            post = postMapper.selectPostById(postDTO.getId()).orElseThrow(() -> new RuntimeException("岗位不存在"));
        } else {
            post = new PostDO();
        }
        return post;
    }
}
