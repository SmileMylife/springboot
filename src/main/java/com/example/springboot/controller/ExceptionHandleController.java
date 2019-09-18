package com.example.springboot.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @controllerAdvice并不会被当成controller来进行处理，而且接收参数只能是exception和request
 * Created by ZhangPei on 2018/12/11.
 */
@ControllerAdvice(basePackages = "com.example.springboot.controller")
public class ExceptionHandleController {

    /**
     * 异常处理机制，可返回视图层或者数据，当返回视图层时使用如下方法，当返回数据时使用responsebody
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleException(HttpServletRequest request, Exception e) {
        System.out.println(request);
        System.out.println("程序发生异常，异常原因：" + e.toString());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("errorDesc", e.toString());
        return modelAndView;
    }

    //TODO 测试能否针对特定的异常进行处理

}
