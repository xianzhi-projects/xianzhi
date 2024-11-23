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

package io.xianzhi.core.i18n;

import java.util.Locale;

/**
 * 国际化消息提供者
 *
 * @author Max
 * @since 1.0.0
 */
public interface I18nMessageProvider {

    /**
     * 根据指定的区域设置和代码检索动态国际化消息。
     *
     * @param locale 要获取消息的区域.
     * @param code   国际化消息编码.
     * @return 动态国际化消息.
     * @since 1.0.0
     */
    String getDynamicMessage(Locale locale, String code);
}

