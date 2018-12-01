package com.example.springboot.paramspackage;

import com.example.springboot.annotations.InputObject;
import org.apache.commons.collections.MapUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 处理方法上的参数映射器
 * Created by ZhangPei on 2018/12/1.
 */
@Component
public class ParamsPackageServiceImpl implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(InputObject.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Map<String, String[]> parameterMap = webRequest.getParameterMap();
        com.example.springboot.bean.InputObject inputObject = new com.example.springboot.bean.InputObject();
        HashMap<String, Object> map = packageParams(parameterMap);
        inputObject.setParams(map);
        return inputObject;
    }

    /**
     * create by zhangpei
     * 入参封装
     *
     * @param map
     */
    public HashMap<String, Object> packageParams(Map<String, String[]> map) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        if (MapUtils.isEmpty(map)) {
            return params;
        }
        Set<Map.Entry<String, String[]>> entries = map.entrySet();
        String key;
        String[] arr;
        String value;
        for (Map.Entry<String, String[]> entry : entries) {
            key = entry.getKey();
            arr = entry.getValue();
            if (arr != null) {
                value = arr.length > 0 ? arr[0] : null;
            } else {
                value = null;
            }
            params.put(key, value);
        }
        return params;
    }
}
