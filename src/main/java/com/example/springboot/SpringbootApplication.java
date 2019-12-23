package com.example.springboot;

import com.alibaba.druid.support.http.StatViewServlet;
import com.example.springboot.common.interceptor.ProjLoginInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@SpringBootApplication
@MapperScan(value = "com.example.springboot.dao")
@EnableTransactionManagement
public class SpringbootApplication extends WebMvcConfigurationSupport {

    @Autowired
    HandlerMethodArgumentResolver handlerMethodArgumentResolver;

    @Autowired
    ProjLoginInterceptor loginInterceptor;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    //druid连接池页面配置
    @Bean
    public ServletRegistrationBean<StatViewServlet> druidStatViewServlet() {
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        registrationBean.addInitParameter("allow", "127.0.0.1");// IP白名单 (没有配置或者为空，则允许所有访问)
        registrationBean.addInitParameter("deny", "");// IP黑名单 (存在共同时，deny优先于allow)
        registrationBean.addInitParameter("loginUsername", "root");
        registrationBean.addInitParameter("loginPassword", "1234");
        registrationBean.addInitParameter("resetEnable", "false");
        return registrationBean;
    }

    /**
     * 原先的参数映射器是加在xml中进行配置的，使用springboot的时候需要用这种方式进行配置
     * 此处使用webmvcConfigurerAdapter可以正常使用
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(handlerMethodArgumentResolver);
    }

    /**
     * 静态资源映射器
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 服务请求拦截器，通过excludePathPatterns过滤掉不需要拦截的请求，或者通过addPathPatterns添加需要拦截的请求
     * 设置为/*则拦截所有请求，如果只配置/则是精确匹配
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/loginPage").excludePathPatterns("/loginSqlProduct").excludePathPatterns("/refreshQRcode").excludePathPatterns("/error").addPathPatterns("/*");     //请求登录页面不做拦截
    }
}
