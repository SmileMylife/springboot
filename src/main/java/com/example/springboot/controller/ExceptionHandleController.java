package com.example.springboot.controller;

import com.example.springboot.common.bean.OutputObject;
import com.example.springboot.common.bean.PageException;
import com.example.springboot.common.bean.TxtException;
import com.example.springboot.util.Constants;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by ZhangPei on 2018/12/11.
 */
@ControllerAdvice(basePackages = "com.example.springboot.controller")
public class ExceptionHandleController {

    /**
     * @controllerAdvice并不会被当成controller来进行处理，而且接收参数只能是exception和request
     * 当相应的异常处理机制处理完相应的异常之后，可返回json信息，也可以跳转至相应的视图层，返回modelAndView即可。
     * 异常处理机制，可返回视图层或者数据，当返回视图层时使用如下方法，当返回数据时使用responsebody
     * @param e
     * 异常处理器中如果也出现了异常，则返回为空，页面什么也不展示
     * @return
     */
    @ExceptionHandler(value = PageException.class)
    public ModelAndView handleException(Exception e) {
        System.out.println("程序发生异常，异常原因：" + e.toString());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/error/error");
//        modelAndView.addObject("errorDesc", e.getMessage().substring(3000));    //可测试异常处理器异常情况
        modelAndView.addObject("errorDesc", e.getMessage());
        return modelAndView;
    }

    //TODO 测试能否针对特定的异常进行处理

//    如果没有@responsebody字段，默认返回视图层
    @ExceptionHandler(value = TxtException.class)
    @ResponseBody
    public OutputObject handleException(HttpServletResponse response, Exception e) {
        response.setStatus(500);
        response.setCharacterEncoding("utf-8");
        OutputObject outputObject = new OutputObject();
        outputObject.setRtnCode(Constants.RTN_CODE_FAIL);
        outputObject.setRtnMsg("程序发生异常，异常原因：" + e.toString());
        return outputObject;
    }

}
