package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 页面跳转控制器
 * Created by ZhangPei on 2019/5/28.
 */
@Controller
public class PageBreakController {

    //跳转至首页
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView toIndex(String isError) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        if ("true".equals(isError)) {
            //如果是错误页面中跳转过来的则需要告知页面回填数据
            modelAndView.addObject("isError", "true");
        } else {
            modelAndView.addObject("isError", "");
        }
        return modelAndView;
    }

    //跳转至sql转换页面
    @RequestMapping(value = "/transposition", method = RequestMethod.GET)
    public ModelAndView toTransposition() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transposition");
        return modelAndView;
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
