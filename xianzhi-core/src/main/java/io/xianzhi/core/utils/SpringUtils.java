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

package io.xianzhi.core.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.*;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * A utility class to access and interact with Spring's application context and bean factory.
 * <p>
 * This class implements {@link BeanFactoryPostProcessor} to ensure that a {@link ConfigurableListableBeanFactory}
 * is injected into the context. It also implements {@link ApplicationContextAware} to allow access to the Spring
 * {@link ApplicationContext}. It provides static methods to access beans in the Spring context, making it easier
 * to work with Spring beans programmatically, especially in cases where direct injection is not available.
 * </p>
 * <p>
 * The utility class provides methods like {@link #getBeanFactory()} to get the bean factory and
 * {@link #getBeansOfType(Class)} to retrieve beans of a specific type.
 * </p>
 *
 * @since 1.0.0
 */
@Slf4j
@Component
public class SpringUtils implements BeanFactoryPostProcessor, ApplicationContextAware {
    /**
     * The {@link ConfigurableListableBeanFactory} used to interact with beans in the Spring context.
     * This is necessary because beans annotated with "@PostConstruct" may encounter a null pointer exception
     * due to the application context not being fully loaded at that point. Therefore, this class implements
     * {@link BeanFactoryPostProcessor} to inject a {@link ConfigurableListableBeanFactory}.
     */
    private static ConfigurableListableBeanFactory beanFactory;

    /**
     * The Spring application context environment.
     */
    @Getter
    private static ApplicationContext applicationContext;

    /**
     * Private constructor to prevent instantiation of the utility class.
     */
    private SpringUtils() {
    }

    /**
     * Returns the {@link ListableBeanFactory}, which can be either a
     * {@link ConfigurableListableBeanFactory} or {@link ApplicationContextAware}.
     *
     * @return The {@link ListableBeanFactory}.
     * @throws RuntimeException if neither the {@link ConfigurableListableBeanFactory} nor the {@link ApplicationContext}
     *                           is injected, indicating that the Spring environment may not be properly initialized.
     * @since 5.7.0
     */
    public static ListableBeanFactory getBeanFactory() {
        final ListableBeanFactory factory = (null == beanFactory) ? applicationContext : beanFactory;
        if (null == factory) {
            throw new RuntimeException(
                    "No ConfigurableListableBeanFactory or ApplicationContext injected, maybe not in the Spring environment?");
        }
        return factory;
    }

    /**
     * Retrieves all beans of the specified type, including subclasses.
     *
     * @param <T>  The bean type.
     * @param type The class or interface to get beans for. Passing {@code null} will retrieve all beans.
     * @return A map of beans of the specified type, where the key is the bean name and the value is the bean instance.
     * @since 5.3.3
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getBeanFactory().getBeansOfType(type);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringUtils.beanFactory = beanFactory;
    }

    /**
     * Set the ApplicationContext that this object runs in. Normally this call will be
     * used to initialize the object.
     * <p>
     * Invoked after population of normal bean properties but before an init callback such
     * as {@link InitializingBean#afterPropertiesSet()} or a custom init-method. Invoked
     * after {@link ResourceLoaderAware#setResourceLoader},
     * {@link ApplicationEventPublisherAware#setApplicationEventPublisher} and
     * {@link MessageSourceAware}, if applicable.
     *
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws ApplicationContextException in case of context initialization errors
     * @throws BeansException              if thrown by application context methods
     * @see BeanInitializationException
     */
    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext)
            throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }
}

