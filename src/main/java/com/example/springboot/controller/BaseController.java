package com.example.springboot.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ZhangPei on 2018/12/11.
 */
public class BaseController {
    @ExceptionHandler
    @ResponseBody
    public void handleException(Exception e) {
        
    }

}
