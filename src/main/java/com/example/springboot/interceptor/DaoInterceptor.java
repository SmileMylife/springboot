package com.example.springboot.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@Intercepts(
        {
                @Signature(type = Executor.class,method = "query",args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class}),
                @Signature(type = Executor.class,method = "update",args = {MappedStatement.class,Object.class})
        })
public class DaoInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println(invocation);
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        return invocation.proceed();    //执行被拦截的方法
    }

    @Override
    public Object plugin(Object o) {
        System.out.println(o);
        return Plugin.wrap(o,this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println(properties);
    }
}
