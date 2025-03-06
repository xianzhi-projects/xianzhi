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

package io.xianzhi.core.result;

/**
 * 响应结果顶级接口
 *
 * @author Max
 * @since 1.0.0
 */
public interface Result {


    /**
     * 获取自定义状态码
     *
     * @return 自定义状体码
     */
    String code();

    /**
     * 获取自定义提示信息
     *
     * @return 自定义提示信息
     */
    String message();

}
