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
import io.xianzhi.system.bootstrap.service.HostCertificateService;
import io.xianzhi.system.model.dto.HostCertificateDTO;
import io.xianzhi.system.model.page.HostCertificatePage;
import io.xianzhi.system.model.vo.HostCertificateVO;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 主机凭证接口
 *
 * @author Max
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/c/host_certificate")
public class HostCertificateController {

    /**
     * 主机凭证服务
     */
    private final HostCertificateService hostCertificateService;

    /**
     * 分页查询主机凭证
     *
     * @param hostCertificatePage 主机凭证接口
     * @return 主机凭证
     */
    @PreAuthorize("@xz.hasPermission('code:cert:page')")
    @PostMapping(value = "/pageHostCertificate")
    public ResponseResult<ListResult<HostCertificateVO>> pageHostCertificate(@RequestBody HostCertificatePage hostCertificatePage) {
        return ResponseResult.success(hostCertificateService.pageHostCertificate(hostCertificatePage));
    }

    /**
     * 创建主机凭证  (幂等)
     *
     * @param hostCertificateDTO 主机凭证入参
     * @return 主机凭证ID
     */
    @Idempotent
    @PreAuthorize("@xz.hasPermission('code:cert:create')")
    @PostMapping(value = "/createHostCertificate")
    public ResponseResult<String> createHostCertificate(@RequestBody @Validated(value = CreateGroup.class) HostCertificateDTO hostCertificateDTO) {
        return ResponseResult.success(hostCertificateService.createHostCertificate(hostCertificateDTO));
    }

    /**
     * 更新主机凭证
     *
     * @param hostCertificateDTO 主机凭证入参
     * @return 响应信息
     */
    @PreAuthorize("@xz.hasPermission('code:cert:update')")
    @PostMapping(value = "/updateHostCertificate")
    public ResponseResult<Object> updateHostCertificate(@RequestBody @Validated(value = UpdateGroup.class) HostCertificateDTO hostCertificateDTO) {
        hostCertificateService.updateHostCertificate(hostCertificateDTO);
        return ResponseResult.success();
    }

    /**
     * 查询主机凭证详情
     *
     * @param id 主机凭证ID
     * @return 主机凭证详情
     */
    @PreAuthorize("@xz.hasPermission('code:cert:details')")
    @GetMapping(value = "/getHostCertificateById")
    public ResponseResult<HostCertificateVO> getHostCertificateById(@RequestParam(value = "id") String id) {
        return ResponseResult.success(hostCertificateService.getHostCertificateById(id));
    }

    /**
     * 删除主机凭证
     *
     * @param ids 主机凭证ID
     * @return 响应信息
     */
    @PreAuthorize("@xz.hasPermission('code:cert:deleted')")
    @PostMapping(value = "/deleteHostCertificateById")
    public ResponseResult<Object> deleteHostCertificateById(@RequestBody @NotEmpty(message = "凭证ID不能为空") List<String> ids) {
        hostCertificateService.deleteHostCertificateById(ids);
        return ResponseResult.success();
    }
}
