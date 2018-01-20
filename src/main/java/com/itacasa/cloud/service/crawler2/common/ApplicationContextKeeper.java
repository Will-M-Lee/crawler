package com.itacasa.cloud.service.crawler2.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author will
 */
@Component
public class ApplicationContextKeeper implements ApplicationContextAware
{
    // spring application context
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        ApplicationContextKeeper.applicationContext = applicationContext;
    }


}
