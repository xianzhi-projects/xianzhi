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

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * Dynamic message source for handling internationalization (i18n) messages.
 * This class integrates with an external i18n provider to retrieve localized messages.
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
public class DynamicMessageSource extends AbstractMessageSource {

    /**
     * Internationalization message provider, optional for dynamic message retrieval.
     */
    @Autowired(required = false)
    private I18nMessageProvider i18nMessageProvider;


    /**
     * Subclasses must implement this method to resolve a message.
     * <p>Returns a MessageFormat instance rather than a message String,
     * to allow for appropriate caching of MessageFormats in subclasses.
     * <p><b>Subclasses are encouraged to provide optimized resolution
     * for messages without arguments, not involving MessageFormat.</b>
     * See the {@link #resolveCodeWithoutArguments} javadoc for details.
     *
     * @param code   the code of the message to resolve
     * @param locale the locale to resolve the code for
     *               (subclasses are encouraged to support internationalization)
     * @return the MessageFormat for the message, or {@code null} if not found
     * @see #resolveCodeWithoutArguments(String, java.util.Locale)
     */
    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        if (null == i18nMessageProvider){
            log.warn("i18nMessageProvider is null");
            return null;
        }
        String i18nMessage = i18nMessageProvider.getDynamicMessage(locale, code);
        if (StringUtils.hasText(i18nMessage)) {
            return createMessageFormat(i18nMessage, locale);
        }
        return null;
    }

}
