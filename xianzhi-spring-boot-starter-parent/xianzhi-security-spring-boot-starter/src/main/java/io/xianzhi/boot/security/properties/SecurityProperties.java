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

package io.xianzhi.boot.security.properties;

import lombok.Data;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * SecurityProperties is a configuration class that holds the security-related properties for the application.
 * These properties are used for token expiration and whitelisting URLs that do not require authentication.
 *
 * @author Max
 * @since 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "xianzhi.security")
public class SecurityProperties  implements InitializingBean {

    /**
     * Token expiration time in seconds.
     * Default is 2 hours (7200 seconds).
     */
    private Integer tokenExpire = 7200;

    /**
     * Refresh token expiration time in seconds.
     * Default is double the value of tokenExpire (14400 seconds).
     */
    private Integer refreshTokenExpire = tokenExpire * 2;

    /**
     * List of URLs that are whitelisted and do not require authentication.
     * These resources can be accessed without a login.
     */
    private List<String> permitAllList = new ArrayList<>();



    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // Validate that tokenExpire is greater than 0
        if (tokenExpire <= 0) {
            throw new IllegalArgumentException("tokenExpire must be greater than 0");
        }

        // Validate that refreshTokenExpire is greater than 0
        if (refreshTokenExpire <= 0) {
            throw new IllegalArgumentException("refreshTokenExpire must be greater than 0");
        }

        // Validate that refreshTokenExpire is greater than tokenExpire
        if (refreshTokenExpire < tokenExpire) {
            throw new IllegalArgumentException("refreshTokenExpire must be greater than tokenExpire");
        }

        // Add default whitelisted URL for favicon
        permitAllList.add("/favicon.ico");
    }
}
