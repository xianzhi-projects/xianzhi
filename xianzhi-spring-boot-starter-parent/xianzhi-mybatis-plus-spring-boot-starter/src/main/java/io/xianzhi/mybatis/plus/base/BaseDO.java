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

package io.xianzhi.mybatis.plus.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.xianzhi.mybatis.plus.MyBatisConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Base Data Object (DO) class with common fields for auditing and status tracking.
 * This class extends {@link IdDO} to include additional metadata such as
 * created/updated user and timestamps.
 *
 * It uses MyBatis annotations to automatically populate fields during
 * insert and update operations.
 *
 * @author Max
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseDO extends IdDO {

    /**
     * User who created the record.
     * This field is automatically populated during insert operations.
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * Timestamp when the record was created.
     * This field is automatically populated during insert operations.
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createAt;

    /**
     * User who last modified the record.
     * This field is automatically populated during both insert and update operations.
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * Timestamp when the record was last modified.
     * This field is automatically populated during both insert and update operations.
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateAt;

    /**
     * Flag indicating whether the record is marked as deleted.
     * This field uses the constant {@link MyBatisConstant#DELETED_FLAG} for column mapping.
     */
    @TableField(value = MyBatisConstant.DELETED_FLAG)
    private Boolean deletedFlag;
}

