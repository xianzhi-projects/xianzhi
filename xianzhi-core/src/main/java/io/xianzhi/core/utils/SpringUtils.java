package io.xianzhi.core.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Spring Utility Class
 * This class provides static utility methods for interacting with the Spring framework, such as
 * accessing the bean factory and application context, and retrieving beans by type. It implements
 * BeanFactoryPostProcessor and ApplicationContextAware to inject the necessary Spring components
 * during initialization, addressing scenarios where the ApplicationContext might not be available
 * (e.g., in @PostConstruct methods). The class is annotated as a Spring component and uses SLF4J
 * for logging.
 *
 * @author Max
 * @since 1.0.0
 */
@Slf4j
@Component
public class SpringUtils implements BeanFactoryPostProcessor, ApplicationContextAware {

    /**
     * ConfigurableListableBeanFactory Instance
     * This static field holds a reference to the ConfigurableListableBeanFactory, injected via
     * BeanFactoryPostProcessor. It is used to perform bean-related operations when the
     * ApplicationContext is not yet available (e.g., in early lifecycle phases like @PostConstruct).
     * The field is static to allow access from static utility methods.
     */
    private static ConfigurableListableBeanFactory beanFactory;

    /**
     * Spring Application Context
     * This static field holds a reference to the ApplicationContext, injected via ApplicationContextAware.
     * It provides access to the Spring application context environment, enabling retrieval of beans
     * and other context-related operations. The field is static for use in static utility methods
     * and includes a getter for external access.
     */
    @Getter
    private static ApplicationContext applicationContext;

    /**
     * Private Constructor
     * This constructor is private to prevent instantiation of the utility class from outside,
     * as all methods are static and intended for utility use only. The class is instantiated by
     * Spring as a component.
     */
    private SpringUtils() {
    }

    /**
     * Get ListableBeanFactory
     * This method retrieves the ListableBeanFactory, which could be either the injected
     * ConfigurableListableBeanFactory or the ApplicationContext, depending on availability.
     * It provides a fallback mechanism to ensure bean factory access even if one of the components
     * is not yet initialized. If neither is available, it throws a RuntimeException to indicate
     * that the method is likely being called outside a Spring environment.
     *
     * @return A ListableBeanFactory instance (either beanFactory or applicationContext).
     * @throws RuntimeException If neither beanFactory nor applicationContext is available.
     * @since 5.7.0
     */
    public static ListableBeanFactory getBeanFactory() {
        final ListableBeanFactory factory = beanFactory != null ? beanFactory : applicationContext;
        if (factory == null) {
            throw new RuntimeException(
                    "No ConfigurableListableBeanFactory or ApplicationContext injected, maybe not in the Spring environment?");
        }
        return factory;
    }

    /**
     * Get All Beans of a Specific Type
     * This method retrieves a map of all beans of the specified type, including subclasses, from
     * the bean factory. The map’s keys are the bean names, and the values are the bean instances.
     * If the type is null, it returns all beans in the context. This is useful for discovering
     * and accessing beans dynamically based on their type.
     *
     * @param <T>  The type of beans to retrieve.
     * @param type The class or interface type of the beans to find; null to retrieve all beans.
     * @return A Map where the key is the bean name and the value is the bean instance of type T.
     * @since 5.3.3
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getBeanFactory().getBeansOfType(type);
    }

    /**
     * Post-Process Bean Factory
     * This method is part of the BeanFactoryPostProcessor interface implementation. It is called
     * by Spring after the bean factory is created but before beans are instantiated, allowing
     * this class to capture a reference to the ConfigurableListableBeanFactory. This is necessary
     * for early access to the bean factory (e.g., in @PostConstruct methods) before the
     * ApplicationContext is fully available.
     *
     * @param beanFactory The ConfigurableListableBeanFactory to be processed and stored.
     * @throws BeansException If an error occurs during bean factory processing.
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringUtils.beanFactory = beanFactory;
    }

    /**
     * Set Application Context
     * This method is part of the ApplicationContextAware interface implementation. It is called
     * by Spring to inject the ApplicationContext into this object after bean properties are set
     * but before initialization callbacks (e.g., afterPropertiesSet or custom init methods).
     * It stores the ApplicationContext in a static field for later use in utility methods,
     * enabling access to Spring’s context environment.
     *
     * @param applicationContext The ApplicationContext object to be used by this utility class.
     * @throws BeansException If an error occurs while setting the application context.
     * @see BeanInitializationException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }
}