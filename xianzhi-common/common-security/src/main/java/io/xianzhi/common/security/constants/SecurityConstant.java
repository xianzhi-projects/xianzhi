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

package io.xianzhi.common.security.constants;

/**
 * Security-Related Constants
 * This interface defines a set of constants related to security operations, specifically for
 * password encoding prefixes used in Spring Security. These constants represent identifiers
 * for different password encryption schemes, such as BCrypt and no-operation (noop), which
 * indicate how a password is stored or should be processed. The interface serves as a centralized
 * location for security-related string constants to ensure consistency across the application.
 *
 * @author Max
 * @since 1.0.0
 */
public interface SecurityConstant {

    /**
     * BCrypt Encryption Prefix
     * This constant represents the prefix used to identify passwords encrypted with the BCrypt
     * algorithm in Spring Security. When a password string begins with "{bcrypt}", it signals
     * that the subsequent value is a BCrypt-hashed password. This is commonly used in password
     * encoders like BCryptPasswordEncoder to distinguish encrypted passwords.
     */
    String BCRYPT = "{bcrypt}";

    /**
     * No-Operation Encryption Prefix
     * This constant represents the prefix used to identify passwords that are stored in plain
     * text (i.e., not encrypted) in Spring Security. When a password string begins with "{noop}",
     * it indicates that no encryption has been applied, and the password should be treated as-is.
     * This is typically used for testing or scenarios where encryption is intentionally bypassed.
     */
    String NOOP = "{noop}";
}
