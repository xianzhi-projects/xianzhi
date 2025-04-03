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

package io.xianzhi.common.security.properties;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Security Configuration Properties
 * This class defines configuration properties for security-related settings in the application,
 * bound to the "xianzhi.security" prefix in a configuration source (e.g., application.properties
 * or application.yml). It includes settings for token expiration times and a whitelist of
 * permitted resources that do not require authentication. The class implements InitializingBean
 * to perform validation and initialization after properties are set, and uses Lombok’s @Data to
 * generate getters, setters, and other utility methods.
 *
 * @author Max
 * @since 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "xianzhi.security")
public class SecurityProperties implements InitializingBean {

    /**
     * Token Expiration Time
     * This property specifies the duration (in hours) after which an access token expires. It
     * determines how long a token remains valid before requiring renewal or re-authentication.
     * The default value is 2 hours, providing a balance between security and usability.
     */
    private Integer tokenExpire = 2;

    /**
     * Refresh Token Expiration Time
     * This property specifies the duration (in hours) after which a refresh token expires. Refresh
     * tokens are used to obtain new access tokens without re-authentication, and this value is
     * typically longer than the access token expiration. The default value is calculated as twice
     * the tokenExpire value (e.g., 4 hours if tokenExpire is 2), ensuring a longer refresh window.
     */
    private Integer refreshTokenExpire = tokenExpire * 2;

    /**
     * Permit All List
     * This property defines a list of resource paths (e.g., URLs) that are whitelisted, meaning they
     * can be accessed without authentication. These are typically public endpoints or static
     * resources that do not require security checks. The list is initialized as an empty ArrayList
     * and can be populated via configuration, with additional defaults added during initialization.
     */
    private List<String> permitAllList = new ArrayList<>();

    /**
     * Perform Validation and Initialization After Properties Are Set
     * This method is invoked by the Spring BeanFactory after all properties have been set, as part
     * of the InitializingBean interface. It validates the configuration to ensure that token
     * expiration times are positive and that the refresh token expiration exceeds the access token
     * expiration. It also adds a default permitted path ("/favicon.ico") to the whitelist, ensuring
     * the favicon resource is accessible without authentication.
     *
     * @throws Exception If the configuration is invalid (e.g., tokenExpire or refreshTokenExpire
     *                   is not positive, or refreshTokenExpire is less than tokenExpire).
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        if (tokenExpire <= 0) {
            throw new IllegalArgumentException("tokenExpire must be greater than 0");
        }
        if (refreshTokenExpire <= 0) {
            throw new IllegalArgumentException("refreshTokenExpire must be greater than 0");
        }
        if (refreshTokenExpire < tokenExpire) {
            throw new IllegalArgumentException("refreshTokenExpire must be greater than tokenExpire");
        }
        permitAllList.add("/favicon.ico");
    }
}

