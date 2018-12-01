package com.example.springboot.controller;

import com.example.springboot.bean.InputObject;
import com.example.springboot.bean.OutputObject;
import com.example.springboot.service.ITestSpringBootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhangPei on 2018/11/15.
 */
@Controller
public class TestSpringBootController {
    @Autowired
    private ITestSpringBootService iTestSpringBootService;

    @RequestMapping(value = "/testRequest", method = RequestMethod.GET)
    @ResponseBody
    public String testRequest() {
        //application主函数要放在目录结构最外层
        System.out.println("测试springboot是否正常运行");
        return "";
    }

    @RequestMapping(value = "/testDaoOperation", method = RequestMethod.GET)
    @ResponseBody
    public List<HashMap<String, Object>> testDaoOperation() {
        List<HashMap<String, Object>> map = iTestSpringBootService.testDaoOperation();
        return map;
    }

    @RequestMapping(value = "/testParamsPackage", method = RequestMethod.GET)
    @ResponseBody
    public OutputObject testDaoOperation(@com.example.springboot.annotations.InputObject InputObject inputObject, OutputObject outputObject) {
        iTestSpringBootService.testParamsPackage(inputObject, outputObject);
        return outputObject;
    }
}
