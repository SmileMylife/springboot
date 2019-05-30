package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ZhangPei on 2019/5/28.
 */
@Controller
public class ToIndexController {
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
}
