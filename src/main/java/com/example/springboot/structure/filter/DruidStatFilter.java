package com.example.springboot.structure.filter;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * druid过滤器，使用注解方式进行配置
 * Created by ZhangPei on 2018/11/30.
 */
@WebFilter(filterName = "druidStatFilter", urlPatterns = "/*", initParams = {@WebInitParam(name = "DruidStatFilter", value = "DruidStatFilter")})
public class DruidStatFilter extends WebStatFilter {

}
