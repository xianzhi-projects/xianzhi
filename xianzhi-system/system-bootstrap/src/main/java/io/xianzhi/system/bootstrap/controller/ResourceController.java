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
import io.xianzhi.core.result.ResponseResult;
import io.xianzhi.core.validated.CreateGroup;
import io.xianzhi.core.validated.UpdateGroup;
import io.xianzhi.system.bootstrap.service.ResourceService;
import io.xianzhi.system.model.dto.ResourceDTO;
import io.xianzhi.system.model.vo.ResourceVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资源接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/s/resource")
public class ResourceController {

    /**
     * 资源服务
     */
    private final ResourceService resourceService;


    /**
     * 查询资源树
     *
     * @return 资源树
     */
    @PreAuthorize("@xz.hasPermission('system:resource:tree')")
    @GetMapping(value = "/tree")
    public ResponseResult<List<ResourceVO>> tree() {
        return ResponseResult.success(resourceService.tree());
    }

    /**
     * 查询当前用户资源信息
     *
     * @return 当前用户资源信息
     */
    @GetMapping(value = "/getCurrentUserResource")
    public ResponseResult<List<ResourceVO>> getCurrentUserResource() {
        return ResponseResult.success(resourceService.getCurrentUserResource());
    }

    /**
     * 新增资源信息  (幂等)
     *
     * @param resourceDTO 资源信息入参
     * @return 资源ID
     */
    @Idempotent
    @PreAuthorize("@xz.hasPermission('system:resource:create')")
    @PostMapping(value = "/createResource")
    public ResponseResult<String> createResource(@RequestBody @Validated(value = CreateGroup.class) ResourceDTO resourceDTO) {
        return ResponseResult.success(resourceService.createResource(resourceDTO));
    }

    /**
     * 更新资源信息
     *
     * @param resourceDTO 资源信息入参
     * @return 资源ID
     */
    @PreAuthorize("@xz.hasPermission('system:resource:update')")
    @PostMapping(value = "/updateResource")
    public ResponseResult<Object> updateResource(@RequestBody @Validated(value = UpdateGroup.class) ResourceDTO resourceDTO) {
        resourceService.updateResource(resourceDTO);
        return ResponseResult.success();
    }

    /**
     * 删除资源
     *
     * @param id 资源ID
     * @return 响应信息
     */
    @PreAuthorize("@xz.hasPermission('system:resource:delete')")
    @PostMapping(value = "/deletedResource")
    public ResponseResult<Object> deletedResource(@RequestParam(value = "id") String id) {
        resourceService.deletedResource(id);
        return ResponseResult.success();
    }
}
