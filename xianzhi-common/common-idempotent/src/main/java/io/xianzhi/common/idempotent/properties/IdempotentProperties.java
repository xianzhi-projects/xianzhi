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
 * Idempotent Configuration Properties
 * This class defines configuration properties for idempotency enforcement in the system. It is
 * annotated with @ConfigurationProperties to bind properties from a configuration source (e.g.,
 * application.properties or application.yml) with the prefix "xianzhi.idempotent". The properties
 * control whether idempotency is enabled, the HTTP request header for idempotency keys, the Redis
 * storage prefix, and the expiration time for stored idempotency markers. Lombok’s @Data annotation
 * generates getters, setters, and other utility methods.
 *
 * @author Max
 * @since 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "xianzhi.idempotent")
public class IdempotentProperties {

    /**
     * Enable Idempotency
     * This property determines whether idempotency enforcement is active. When set to true,
     * idempotency checks (e.g., via the IdempotentAspect) are performed; when false, such checks
     * are bypassed. The default value is true, enabling idempotency by default.
     */
    private boolean enable = true;

    /**
     * Request Header for Idempotency Key
     * This property specifies the name of the HTTP request header that contains the idempotency
     * key. The key is used to identify unique requests and enforce idempotency. The default value
     * is "idempotent", meaning the system expects the idempotency key in a header named "idempotent"
     * unless overridden in the configuration.
     */
    private String requestHeader = "idempotent";

    /**
     * Storage Prefix for Idempotency Keys
     * This property defines the prefix template used to construct keys in the storage system
     * (e.g., Redis) for idempotency markers. The format string includes a placeholder (%s) that
     * is replaced with the idempotency key. The default value is "idempotent:%s", resulting in
     * keys like "idempotent:abc123" when combined with a key value.
     */
    private String storePrefix = "idempotent:%s";

    /**
     * Storage Expiration Time
     * This property sets the expiration time (in seconds) for idempotency markers stored in the
     * system (e.g., Redis). After this duration, the marker is automatically removed, allowing
     * the same idempotency key to be reused. The default value is 5 seconds, providing a short
     * window for idempotency enforcement unless overridden.
     */
    private long storeExpire = 5L;
}
