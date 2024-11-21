/*
 * Copyright 2024 XianZhi Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.xianzhi.mybatis.plus;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * MyBatis Plus Configuration Class.
 * Configures MyBatis Plus components, such as interceptors, for the application.
 *
 * @author Max
 * @since 1.0.0
 */
public class MyBatisPlusConfig {

    /**
     * Configures the MyBatis Plus Interceptor.
     * <p>
     * Adds a pagination interceptor to handle pagination queries efficiently.
     * </p>
     *
     * @return mybatisPlusInterceptor The configured MyBatis Plus Interceptor
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mpInterceptor = new MybatisPlusInterceptor();
        // Add the pagination interceptor
        mpInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mpInterceptor;
    }
}