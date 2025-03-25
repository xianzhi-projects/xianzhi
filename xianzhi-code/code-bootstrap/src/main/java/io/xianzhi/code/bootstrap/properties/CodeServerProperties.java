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

package io.xianzhi.code.bootstrap.properties;

import io.xianzhi.core.exception.BusinessException;
import lombok.Data;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * 代码托管服务配置类
 *
 * @author Max
 * @since 1.0.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "xianzhi.code")
public class CodeServerProperties implements InitializingBean {


    /**
     * 代码托管服务地址
     */
    private String externalUrl;

    /**
     * SSH服务器端口
     */
    private Integer sshServerPort = 22;

    /**
     * 仓库地址
     */
    private String repositoryDir = "/etc/xianzhi/code/repository";


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
        if (!StringUtils.hasText(externalUrl)) {
            throw new BusinessException("请配置托管地址");
        }
        if (!StringUtils.hasText(repositoryDir)) {
            repositoryDir = "/etc/xianzhi/code/repository";
        }
        if (sshServerPort <= 0 || sshServerPort >= 65535) {
            sshServerPort = 22;
        }
    }


}
