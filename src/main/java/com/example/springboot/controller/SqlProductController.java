package com.example.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.springboot.common.annotations.InputObject;
import com.example.springboot.common.bean.OutputObject;
import com.example.springboot.service.ISqlProductService;
import com.example.springboot.util.Constants;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;

/**
 * Created by ZhangPei on 2019/5/28.
 */
@Controller("/sqlProduct")
public class SqlProductController {
    @Autowired
    private ISqlProductService iSqlProductService;

    @RequestMapping(value = "/queryAllProv", method = RequestMethod.POST)
    @ResponseBody
    public OutputObject queryAllProv(@InputObject com.example.springboot.common.bean.InputObject inputObject, OutputObject outputObject) throws Exception {
        inputObject.getParams().put("dbKey", "proj_conf");
        iSqlProductService.queryAllProv(inputObject, outputObject);
        return outputObject;
    }

    /**
     * 脚本文件生成t
     * @param outputObject
     * @throws Exception
     */
//    @RequestMapping(value = "/productSqlFile", method = RequestMethod.GET)
    @RequestMapping(value = "/productSqlFile", method = RequestMethod.POST)
    public void productSqlFile(HttpServletResponse response, @InputObject com.example.springboot.common.bean.InputObject inputObject, OutputObject outputObject) throws Exception {
        inputObject.getParams().put("dbKey", "proj_conf");
        FileInputStream is = null;
        iSqlProductService.productSqlFile(inputObject, outputObject);
        Object obj = outputObject.getObject();
        if (obj instanceof FileInputStream) {
            is = (FileInputStream) obj;
        }

        ServletOutputStream outputStream = response.getOutputStream();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        //解决下载文件名显示下划线的问题
        response.addHeader("Content-Disposition", "attachment; filename=" + new String(MapUtils.getString(outputObject.getBean(), "fileName").getBytes("utf-8"), "iso-8859-1") + ".zip");

        if (is != null) {
            int lenth;
            while ((lenth = is.read()) > -1) {
                outputStream.write(lenth);
                outputStream.flush();
            }
            outputStream.close();
        }
    }

    @RequestMapping(value = "/showDownloadList", method = RequestMethod.POST)
    @ResponseBody
    public OutputObject showDownloadList(@InputObject com.example.springboot.common.bean.InputObject inputObject, OutputObject outputObject) throws Exception {
        inputObject.getParams().put("dbKey", "proj_conf");
        iSqlProductService.showDownloadList(inputObject, outputObject);
        return outputObject;
    }

    @RequestMapping(value = "/sqlReplaceByTemplate", method = RequestMethod.POST)
    @ResponseBody
    public String sqlReplaceByTemplate(@InputObject com.example.springboot.common.bean.InputObject inputObject, OutputObject outputObject) {
        String vars = MapUtils.getString(inputObject.getParams(), "vars");
        String varVals = MapUtils.getString(inputObject.getParams(), "varVals");
        String sql = MapUtils.getString(inputObject.getParams(), "sql");

        Object varsParse = JSON.parse(vars);    //解析传入的数据
        Object varValsParse = JSON.parse(varVals);    //解析传入的数据

        StringBuilder sb = new StringBuilder();
        if (varsParse instanceof JSONArray && varValsParse instanceof JSONArray) {
            JSONArray varsArr = (JSONArray) varsParse;
            JSONArray varValsParseArr = (JSONArray) varValsParse;
            if (varsArr.size() == varValsParseArr.size()) {
                for (int i = 0; i < varsArr.size(); i++) {
                    String result = sql.replaceAll("\\$\\{" + varsArr.get(i) + "\\}", varValsParseArr.getString(i));
                    sb.append(result + System.getProperty("line.separator") + Constants.LINE_BREAK);
                }
            }
        }

        return sb.toString();
    }

    @RequestMapping(value = "/testRequest", method = RequestMethod.POST)
    public String testRequest(@InputObject com.example.springboot.common.bean.InputObject inputObject, HttpServletRequest request) {
        return "index";
    }
}
