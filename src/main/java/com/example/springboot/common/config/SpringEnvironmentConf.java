package com.example.springboot.common.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
public class SpringEnvironmentConf implements EnvironmentAware {
    private static Environment env;

    @Override
    public void setEnvironment(Environment environment) {
        env = environment;
    }

    @PostConstruct
    public void testPostConstructAnnotation() {
        System.out.println("这段代码是在autowire之后执行的");
    }
}
