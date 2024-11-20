/*
 *  Copyright 2024 XianZhi Group
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
 *
 */

package io.xianzhi.boot.security.constants;

/**
 * Security constants class.
 * Defines common constants used in security configurations, such as password encoding prefixes.
 *
 * @author Max
 * @since 1.0.0
 */
public class SecurityConstant {

    /**
     * Prefix for bcrypt encryption.
     * Used to indicate that the password is encrypted using the BCrypt algorithm.
     *
     * @since 1.0.0
     */
    String BCRYPT = "{bcrypt}";

    /**
     * Prefix for plain text passwords (noop encryption).
     * Used to indicate that the password is stored in plain text without encryption.
     *
     * @since 1.0.0
     */
    String NOOP = "{noop}";
}
