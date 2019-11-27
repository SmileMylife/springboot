package com.example.springboot.controller;

import com.example.springboot.common.annotations.InputObject;
import com.example.springboot.common.bean.OutputObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

/**
 * Created by ZhangPei on 2019/11/22.
 */
@Controller
public class DocQueryController {
    @RequestMapping(value = "/queryDocs", method = RequestMethod.POST)
    public void queryDocs(@InputObject com.example.springboot.common.bean.InputObject inputObject, OutputObject outputObject) {
        
    }

    @RequestMapping(value = "/uploadDoc", method = RequestMethod.POST)
    public void uploadDoc(@InputObject com.example.springboot.common.bean.InputObject inputObject, OutputObject outputObject) {

    }

    @RequestMapping(value = "/lookOnline", method = RequestMethod.POST)
    public void lookOnline(@InputObject com.example.springboot.common.bean.InputObject inputObject, OutputObject outputObject) {

    }
}
