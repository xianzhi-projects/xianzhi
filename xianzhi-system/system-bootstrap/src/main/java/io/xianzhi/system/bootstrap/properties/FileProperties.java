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

package io.xianzhi.system.bootstrap.properties;

import io.xianzhi.core.code.CommonCode;
import io.xianzhi.core.exception.BusinessException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件配置类
 *
 * @author Max
 * @since 1.0.0
 */
@Setter
@Getter
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "xianzhi.file")
public class FileProperties implements InitializingBean {


    private List<String> allowTypes = new ArrayList<>();
    /**
     * bucket名称
     */
    private String bucketName;

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
        if (!StringUtils.hasText(bucketName)) {
            throw new BusinessException(CommonCode.ERROR.code(), "bucketName不能为空");
        }
        allowTypes.add(".jpg");
        allowTypes.add(".jpeg");
        allowTypes.add(".png");
        allowTypes.add(".gif");
        allowTypes.add(".mp4");
        allowTypes.add(".avi");
        allowTypes.add(".mp3");
        allowTypes.add(".wav");
        allowTypes.add(".txt");
        allowTypes.add(".doc");
        allowTypes.add(".docx");
        allowTypes.add(".pdf");
        allowTypes.add(".zip");
        allowTypes.add(".rar");
        allowTypes.add(".xls");
        allowTypes.add(".xlsx");
        allowTypes.add(".ppt");
        allowTypes.add(".pptx");
        allowTypes.add(".csv");
        allowTypes.add(".xml");
        allowTypes.add(".html");
        allowTypes.add(".css");
        allowTypes.add(".js");
        allowTypes.add(".json");
        allowTypes.add(".bmp");
        allowTypes.add(".tiff");
        allowTypes.add(".svg");
        allowTypes.add(".webp");
        allowTypes.add(".mpg");
        allowTypes.add(".mpeg");
        allowTypes.add(".mov");
        allowTypes.add(".wmv");
        allowTypes.add(".flv");
        allowTypes.add(".mkv");
    }
}
