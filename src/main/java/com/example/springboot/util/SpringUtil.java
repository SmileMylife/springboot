package com.example.springboot.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 当spring容器初始化的时候，如果某个bean实现了ApplicationContextAware接口，则在初始化bean的时候为该
 * bean自动注入spring容器对象，前提是要有对象去接收他
 * Created by ZhangPei on 2019/10/16.
 */
@Component
public class SpringUtil<T> implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    /**
     * 根据bean名称获取bean
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBeanByName(String name) {
        return (T) applicationContext.getBean(name);
    }
}