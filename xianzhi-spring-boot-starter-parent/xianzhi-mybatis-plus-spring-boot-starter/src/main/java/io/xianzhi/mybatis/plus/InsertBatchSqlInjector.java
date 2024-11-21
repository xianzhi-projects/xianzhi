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

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import org.apache.ibatis.session.Configuration;

import java.util.List;

/**
 * Custom SQL Injector for MyBatis Plus.
 * Extends the default SQL injector to add a custom batch insert method
 * while retaining the existing methods provided by MyBatis Plus.
 *
 * @author Max
 * @since 1.0.0
 */
public class InsertBatchSqlInjector extends DefaultSqlInjector {

    /**
     * Overrides the method list to include custom SQL methods.
     *
     * @param configuration MyBatis configuration
     * @param mapperClass   The mapper interface class
     * @param tableInfo     The table information
     * @return List of SQL methods, including MyBatis Plus default methods and the custom batch insert method
     */
    @Override
    public List<AbstractMethod> getMethodList(Configuration configuration, Class<?> mapperClass, TableInfo tableInfo) {
        // Retain the default MyBatis Plus methods
        List<AbstractMethod> methodList = super.getMethodList(configuration, mapperClass, tableInfo);

        // Add the custom batch insert method
        methodList.add(new InsertBatchSomeColumn());

        return methodList;
    }
}
