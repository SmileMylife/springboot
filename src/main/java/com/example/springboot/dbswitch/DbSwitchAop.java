package com.example.springboot.dbswitch;

import com.example.springboot.bean.InputObject;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by ZhangPei on 2018/11/30.
 */
@Aspect
@Component
public class DbSwitchAop {
    @Value("${defaultDatasource}")
    private String defaultDatasource;

    @Pointcut("execution(* com.example.springboot.service.impl.*.*(..))")
    public void dbSwitchAop() {

    }

    /**
     * 根据dbKey字段进行切库
     */
    @Before(value = "dbSwitchAop()")
    public void setDbKey(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args != null && args[0] instanceof InputObject) {
            InputObject input = (InputObject) args[0];
            HashMap<String, Object> params = input.getParams();
            String dbKey = MapUtils.getString(params, "dbKey");
            if (StringUtils.isBlank(dbKey)) {
                DBHandleServiceImpl.setDbSource(defaultDatasource);
            } else {
                DBHandleServiceImpl.setDbSource(dbKey);
            }
        }
    }
}
