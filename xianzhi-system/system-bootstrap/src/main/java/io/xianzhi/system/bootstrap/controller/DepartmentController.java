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
 * Department Controller
 * This class serves as a controller for handling HTTP requests related to department management
 * within the application. It provides endpoints for querying, creating, updating, and deleting
 * department information, interacting with the department service layer to perform these operations.
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/s/department")
public class DepartmentController {

    /**
     * Department Service
     * This field provides access to the department service layer, which encapsulates the business
     * logic for managing department data.
     */
    private final DepartmentService departmentService;

    /**
     * Query Department Tree Structure
     * This method retrieves the hierarchical tree structure of departments.
     *
     * @return A response containing the list of department tree nodes.
     */
    @PreAuthorize("@xz.hasPermission('system:department:tree')")
    @GetMapping(value = "/tree")
    public ResponseResult<List<DepartmentVO>> tree() {
        return ResponseResult.success(departmentService.tree());
    }

    /**
     * Get User-Authorized Department Tree Structure
     * This method retrieves the hierarchical tree structure of departments that the current user
     * has permission to access.
     *
     * @return A response containing the list of department tree nodes accessible to the user.
     */
    @GetMapping(value = "/getDepartmentTree")
    public ResponseResult<List<DepartmentVO>> getDepartmentTree() {
        return ResponseResult.success(departmentService.getDepartmentTree());
    }

    /**
     * Create Department (Idempotent)
     * This method creates a new department with the provided information, ensuring idempotency
     * to prevent duplicate submissions.
     *
     * @param departmentDTO The department information to be created.
     * @return A response containing the ID of the newly created department.
     */
    @Idempotent
    @PreAuthorize("@xz.hasPermission('system:department:create')")
    @PostMapping(value = "/createDepartment")
    public ResponseResult<String> createDepartment(@RequestBody @Validated(value = CreateGroup.class) DepartmentDTO departmentDTO) {
        return ResponseResult.success(departmentService.createDepartment(departmentDTO));
    }

    /**
     * Update Department
     * This method updates the information of an existing department.
     *
     * @param departmentDTO The updated department information.
     * @return A success response indicating the operation was completed.
     */
    @PreAuthorize("@xz.hasPermission('system:department:update')")
    @PostMapping(value = "/updateDepartment")
    public ResponseResult<Object> updateDepartment(@RequestBody @Validated(value = UpdateGroup.class) DepartmentDTO departmentDTO) {
        departmentService.updateDepartment(departmentDTO);
        return ResponseResult.success();
    }

    /**
     * Delete Department
     * This method deletes a department based on the provided ID.
     *
     * @param id The ID of the department to be deleted.
     * @return A success response indicating the operation was completed.
     */
    @PreAuthorize("@xz.hasPermission('system:department:deleted')")
    @PostMapping(value = "/deletedDepartment")
    public ResponseResult<Object> deletedDepartment(@RequestParam(value = "id") String id) {
        departmentService.deletedDepartment(id);
        return ResponseResult.success();
    }
}
