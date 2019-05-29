package com.example.springboot.controller;

import com.example.springboot.annotations.InputObject;
import com.example.springboot.bean.OutputObject;
import com.example.springboot.service.ISqlProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ZhangPei on 2019/5/28.
 */
@Controller
public class SqlProductController {
    @Autowired
    private ISqlProductService iSqlProductService;

    @RequestMapping(value = "/queryAllProv", method = RequestMethod.POST)
    @ResponseBody
    public OutputObject queryAllProv(@InputObject com.example.springboot.bean.InputObject inputObject, OutputObject outputObject) throws Exception {
        inputObject.getParams().put("dbKey", "ngwf");
        iSqlProductService.queryAllProv(inputObject, outputObject);
        return outputObject;
    }

    /**
     * 脚本文件生成
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping(value = "/productSqlFile", method = RequestMethod.POST)
    @ResponseBody
    public void productSqlFile(@com.example.springboot.annotations.InputObject com.example.springboot.bean.InputObject inputObject, OutputObject outputObject) throws Exception {
        iSqlProductService.productSqlFile(inputObject, outputObject);
    }
}
