package com.example.springboot.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ZhangPei on 2018/12/11.
 */
@ControllerAdvice
public class BaseController {


    /**
     * 异常处理机制，可返回视图层或者数据，当返回视图层时使用如下方法，当返回数据时使用responsebody
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleException(Exception e) {
        System.out.println("测试异常处理机制是否生效");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        return modelAndView;
    }


    //TODO 测试能否针对特定的异常进行处理
}
