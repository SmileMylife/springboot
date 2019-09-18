package com.example.springboot.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by ZhangPei on 2018/12/11.
 */
@ControllerAdvice(basePackages = "com.example.springboot.controller")
public class ExceptionHandleController {


    /**
     * 异常处理机制，可返回视图层或者数据，当返回视图层时使用如下方法，当返回数据时使用responsebody
     * @param e
     * @return
     */
    @RequestMapping(value = "/error/{code}")
    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleException(Exception e, @PathVariable("code") String code) {
        System.out.println("程序发生异常，异常原因：" + e.toString());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("errorDesc", e.toString());
        return modelAndView;
    }

    //TODO 测试能否针对特定的异常进行处理
}
