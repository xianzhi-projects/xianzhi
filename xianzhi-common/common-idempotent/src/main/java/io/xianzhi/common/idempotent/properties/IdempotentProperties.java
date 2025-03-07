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

package io.xianzhi.common.idempotent.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 幂等配置类
 *
 * @author Max
 * @since 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "xianzhi.idempotent")
public class IdempotentProperties {

    /**
     * 是否开启幂等性
     */
    private boolean enable = true;


    /**
     * 幂等性存储前缀
     */
    private String requestHeader = "idempotent";

    /**
     * 幂等性存储前缀
     */
    private String storePrefix = "idempotent:%s";

    /**
     * 幂等性存储过期时间
     */
    private long storeExpire = 5L;

}
