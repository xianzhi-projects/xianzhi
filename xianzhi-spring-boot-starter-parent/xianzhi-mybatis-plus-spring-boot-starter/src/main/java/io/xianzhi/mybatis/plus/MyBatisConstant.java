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

/**
 * Contains constants commonly used in MyBatis mappings and queries.
 * These constants represent frequently used database column names
 * and standard fields for auditing and status tracking.
 *
 * @author Max
 * @since 1.0.0
 */
public class MyBatisConstant {

    /**
     * Column name for the user who created the record.
     */
    public static final String CREATE_BY = "createBy";

    /**
     * Column name for the timestamp when the record was created.
     */
    public static final String CREATE_AT = "createAt";

    /**
     * Column name for the user who last modified the record.
     */
    public static final String UPDATE_BY = "updateBy";

    /**
     * Column name for the timestamp when the record was last modified.
     */
    public static final String UPDATE_AT = "updateAt";

    /**
     * Column name for the deletion flag indicating if the record is deleted.
     */
    public static final String DELETED_FLAG = "deleted_flag";

    /**
     * Column name for the default flag indicating if the record is marked as default.
     */
    public static final String DEFAULT_FLAG = "default_flag";

    /**
     * Column name for the enable flag indicating if the record is active or enabled.
     */
    public static final String ENABLE_FLAG = "enable_flag";

    /**
     * Column name for the system flag indicating if the record is a system-defined entry.
     */
    public static final String SYSTEM_FLAG = "system_flag";
}
