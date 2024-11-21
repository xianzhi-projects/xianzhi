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

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * Data object representing a unique identifier.
 * This class serves as a base entity with an ID field,
 * which can be extended by other domain objects.
 *
 * @author Max
 * @since 1.0.0
 */
public class IdDO {

    /**
     * Unique identifier for the entity.
     * The ID is automatically assigned using the {@link IdType#ASSIGN_ID} strategy.
     *
     * @since 1.0.0
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

}