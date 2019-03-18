package com.example.springboot.splittable;

import com.example.springboot.bean.InputObject;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by ZhangPei on 2019/1/31.
 */
@Aspect
@Component
public class SplitTableServiceImpl {
    @Autowired
    private Environment environment;

    @Pointcut("execution(* com.example.springboot.service.impl.*.*(..)))")
    public void splitTable() {

    }

    @Before(value = "splitTable()")
    public void before(JoinPoint joinPoint) throws ParseException {
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            if (args[0] instanceof InputObject) {
                InputObject inputObject = (InputObject) args[0];
                HashMap<String, Object> params = inputObject.getParams();
                if (params.containsKey("CRT_TIME") && StringUtils.isNotBlank(MapUtils.getString(params, "CRT_TIME"))) {
                    String tableName = MapUtils.getString(params, "table_name");
                    String crtTime = MapUtils.getString(params, "CRT_TIME");
                    String splitRule = environment.getProperty(tableName);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(splitRule);
                    Date date = DateUtils.parseDate(crtTime, "yyyy:MM:dd HH:mm:ss");
                    String extTalbeName = simpleDateFormat.format(date);
                    params.put("table_name", tableName + extTalbeName);
                }
            }
        }
    }
}
