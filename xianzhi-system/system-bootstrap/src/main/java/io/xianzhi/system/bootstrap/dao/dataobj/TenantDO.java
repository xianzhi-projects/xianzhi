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

package io.xianzhi.system.bootstrap.dao.dataobj;

import com.baomidou.mybatisplus.annotation.TableName;
import io.xianzhi.common.mybatis.plus.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 租户实体
 *
 * @author Max
 * @since 1.0.0
 */
@Data
@TableName(value = "sys_tenant")
@EqualsAndHashCode(callSuper = true)
public class TenantDO extends BaseDO {

    /**
     * 租户名称
     */
    private String tenantName;
    /**
     * 租户编码
     */
    private String tenantCode;
    /**
     * 租户描述
     */
    private String tenantDesc;

    /**
     * 租户logo
     */
    private String tenantLogo;
    /**
     * 租户负责人
     */
    private String tenantOwner;
}
