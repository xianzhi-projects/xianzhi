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
 * Interface for providing dynamic internationalization (i18n) messages.
 *
 * @author Max
 * @since 1.0.0
 */
public interface I18nMessageProvider {

    /**
     * Retrieves dynamic internationalization message based on the specified locale and code.
     *
     * @param locale The locale for which the message is to be fetched.
     * @param code The internationalization code to identify the message.
     * @return The dynamic internationalization message.
     * @since 1.0.0
     */
    String getDynamicMessage(Locale locale, String code);
}

