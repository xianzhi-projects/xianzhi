/*
 *  Copyright 2024 XianZhi Group
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
 *
 */

package io.xianzhi.core.context;

/**
 * 上下文接口
 * <p>
 * 表示提供唯一标识符的通用上下文抽象。这可以通过各种类来实现,应用程序中的上下文信息
 * <p>
 * 常见用例包括：
 * -用户上下文
 * -客户端上下文
 * -特定于应用程序的上下文
 *
 * @author Max
 * @since 1.0.0
 */
public interface Context {

    /**
     * 获取唯一标识，例如用户ID
     *
     * @return 唯一标识
     * @since 1.0.0
     */
    String getId();
}
