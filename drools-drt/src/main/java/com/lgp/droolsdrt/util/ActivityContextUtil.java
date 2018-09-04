package com.lgp.droolsdrt.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring反射获得bean
 */
@Component
public class ActivityContextUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> classz) {
        return context.getBean(classz);
    }

    public static Object getBean(String paramString) {
        return context.getBean(paramString);
    }
}
