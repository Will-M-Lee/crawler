package com.itacasa.cloud.service.crawler2.common;

import org.springframework.context.ApplicationContext;

/**
 * @author will
 */
public abstract class BaseEntity
{
    // spring application context
    protected ApplicationContext applicationContext;

    public BaseEntity()
    {
        applicationContext = ApplicationContextKeeper.getApplicationContext();
    }

    /**
     * shortcut for get bean from application context
     */
    protected <T> T getBean(Class<T> beanClass)
    {
        return applicationContext.getBean(beanClass);
    }

    /**
     * shortcut for get bean from application context
     */
    protected <T> T getBean(String name, Class<T> beanClass)
    {
        return applicationContext.getBean(name, beanClass);
    }
}
