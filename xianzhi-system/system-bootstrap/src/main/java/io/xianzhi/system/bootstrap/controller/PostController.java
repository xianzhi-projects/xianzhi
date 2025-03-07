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

package io.xianzhi.system.bootstrap.controller;

import io.xianzhi.common.idempotent.annotations.Idempotent;
import io.xianzhi.core.result.ListResult;
import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.core.validated.CreateGroup;
import io.xianzhi.core.validated.UpdateGroup;
import io.xianzhi.system.bootstrap.service.PostService;
import io.xianzhi.system.model.dto.PostDTO;
import io.xianzhi.system.model.page.PostPage;
import io.xianzhi.system.model.vo.PostVO;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 岗位接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/s/post")
public class PostController {
    /**
     * 岗位接口
     */
    private final PostService postService;

    /**
     * 分页查询岗位列表
     *
     * @param page 查询条件
     * @return 岗位列表
     */
    @PreAuthorize("@xz.hasPermission('system:post:list')")
    @PostMapping(value = "/pagePostList")
    public ResponseResult<ListResult<PostVO>> pagePostList(@RequestBody PostPage page) {
        return ResponseResult.success(postService.pagePostList(page));
    }

    /**
     * 新增岗位
     *
     * @param postDTO 岗位信息入参
     * @return 岗位ID
     */
    @Idempotent
    @PreAuthorize("@xz.hasPermission('system:post:create')")
    @PostMapping(value = "/createPost")
    public ResponseResult<String> createPost(@RequestBody @Validated(value = CreateGroup.class) PostDTO postDTO) {
        return ResponseResult.success(postService.createPost(postDTO));
    }

    /**
     * 修改岗位
     *
     * @param postDTO 岗位信息入参
     * @return 响应信息
     */
    @PreAuthorize("@xz.hasPermission('system:post:update')")
    @PostMapping(value = "/updatePost")
    public ResponseResult<Object> updatePost(@RequestBody @Validated(value = UpdateGroup.class) PostDTO postDTO) {
        postService.updatePost(postDTO);
        return ResponseResult.success();
    }

    /**
     * 删除岗位
     *
     * @param ids 岗位ID
     * @return 响应信息
     */
    @PreAuthorize("@xz.hasPermission('system:post:delete')")
    @PostMapping(value = "/deletedPost")
    public ResponseResult<Object> deletedPost(@RequestBody @NotEmpty(message = "岗位ID不能为空") List<String> ids) {
        postService.deletedPost(ids);
        return ResponseResult.success();
    }
}
