package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ZhangPei on 2019/5/28.
 */
@Controller
public class ToIndexController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String toIndex() {
        System.out.println("ceshi");
        return "index";
    }
}
