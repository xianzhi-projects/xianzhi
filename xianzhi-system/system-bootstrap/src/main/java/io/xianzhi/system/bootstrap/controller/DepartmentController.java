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
import io.xianzhi.system.bootstrap.service.DepartmentService;
import io.xianzhi.system.model.dto.DepartmentDTO;
import io.xianzhi.system.model.vo.DepartmentVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/s/department")
public class DepartmentController {

    /**
     * 部门接口
     */
    private final DepartmentService departmentService;


    /**
     * 查询部门树结构信息
     *
     * @return 树信息
     */
    @PreAuthorize("@xz.hasPermission('system:department:tree')")
    @GetMapping(value = "/tree")
    public ResponseResult<List<DepartmentVO>> tree() {
        return ResponseResult.success(departmentService.tree());
    }

    /**
     * 新增部门信息
     *
     * @param departmentDTO 部门信息入参
     * @return 部门ID
     */
    @Idempotent
    @PreAuthorize("@xz.hasPermission('system:department:create')")
    @PostMapping(value = "/createDepartment")
    public ResponseResult<String> createDepartment(@RequestBody @Validated(value = CreateGroup.class) DepartmentDTO departmentDTO) {
        return ResponseResult.success(departmentService.createDepartment(departmentDTO));
    }

    /**
     * 更新部门信息
     *
     * @param departmentDTO 部门信息入参
     * @return 响应信息
     */
    @PreAuthorize("@xz.hasPermission('system:department:update')")
    @PostMapping(value = "/updateDepartment")
    public ResponseResult<Object> updateDepartment(@RequestBody @Validated(value = UpdateGroup.class) DepartmentDTO departmentDTO) {
        departmentService.updateDepartment(departmentDTO);
        return ResponseResult.success();
    }

    /**
     * 删除部门
     *
     * @param id 部门ID
     * @return 响应信息
     */
    @PreAuthorize("@xz.hasPermission('system:department:deleted')")
    @PostMapping(value = "/deletedDepartment")
    public ResponseResult<Object> deletedDepartment(@RequestParam(value = "id") String id) {
        departmentService.deletedDepartment(id);
        return ResponseResult.success();
    }


}
