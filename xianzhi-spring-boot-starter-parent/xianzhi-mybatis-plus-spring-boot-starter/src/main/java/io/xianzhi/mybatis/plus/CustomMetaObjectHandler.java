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

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import io.xianzhi.core.context.ContextHolder;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * Custom data fill handler for MyBatis Plus.
 * This handler automatically populates common fields such as creator, modifier,
 * and timestamps during insert and update operations.
 *
 * @author Max
 * @since @since 1.0.0
 */
public class CustomMetaObjectHandler implements MetaObjectHandler {

    /**
     * Populates fields during an insert operation.
     * Automatically sets:
     * - Creator (`createBy`) and modifier (`updateBy`) to the current user.
     * - Creation (`createAt`) and update (`updateAt`) timestamps to the current time.
     *
     * @param metaObject the meta object representing the entity being operated on.
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date(); // Current timestamp
        String currentUserId = ContextHolder.getId(); // Retrieve the current user's ID

        // Set the fields for insert operation
        this.setFieldValByName(MyBatisConstant.CREATE_BY, currentUserId, metaObject);
        this.setFieldValByName(MyBatisConstant.UPDATE_BY, currentUserId, metaObject);
        this.setFieldValByName(MyBatisConstant.CREATE_AT, now, metaObject);
        this.setFieldValByName(MyBatisConstant.UPDATE_AT, now, metaObject);
    }

    /**
     * Populates fields during an update operation.
     * Automatically sets:
     * - Modifier (`updateBy`) to the current user.
     * - Update timestamp (`updateAt`) to the current time.
     *
     * @param metaObject the meta object representing the entity being operated on.
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // Set the fields for update operation
        this.setFieldValByName(MyBatisConstant.UPDATE_BY, ContextHolder.getId(), metaObject);
        this.setFieldValByName(MyBatisConstant.UPDATE_AT, new Date(), metaObject);
    }
}