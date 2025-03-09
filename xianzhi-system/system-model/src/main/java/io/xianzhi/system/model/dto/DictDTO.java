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

package io.xianzhi.system.model.dto;

import io.xianzhi.core.validated.CreateGroup;
import io.xianzhi.core.validated.UpdateGroup;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * 字典入参
 *
 * @author Max
 * @since 1.0.0
 */
@Data
public class DictDTO implements Serializable {

    /**
     * 字典ID
     */
    @Length(max = 20,message = "字典ID长度为20位",groups = UpdateGroup.class)
    @NotBlank(message = "字典ID不能为空",groups = UpdateGroup.class)
    private String id;

    /**
     * 字典名称
     */
    @Length(max = 64,message = "字典名称长度为64位",groups = {CreateGroup.class,UpdateGroup.class})
    @NotBlank(message = "字典名称不能为空",groups = {CreateGroup.class,UpdateGroup.class})
    private String dictName;

    /**
     * 字典编码
     */
    @Length(max = 128,message = "字典编码长度为128位",groups = {CreateGroup.class,UpdateGroup.class})
    @NotBlank(message = "字典编码不能为空",groups = {CreateGroup.class})
    private String dictCode;

    /**
     * 字典描述
     */
    @Length(max = 255,message = "字典描述长度为255位",groups = {CreateGroup.class,UpdateGroup.class})
    private String dictDesc;
}
