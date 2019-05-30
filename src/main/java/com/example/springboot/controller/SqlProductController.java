package com.example.springboot.controller;

import com.example.springboot.annotations.InputObject;
import com.example.springboot.bean.OutputObject;
import com.example.springboot.service.ISqlProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

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
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping(value = "/productSqlFile", method = RequestMethod.GET)
    public void productSqlFile(HttpServletResponse response, @InputObject com.example.springboot.bean.InputObject inputObject, OutputObject outputObject) throws Exception {
        inputObject.getParams().put("dbKey", "ngwf");
        FileInputStream is = null;
        try {
            iSqlProductService.productSqlFile(inputObject, outputObject);
            Object obj = outputObject.getObject();
            if (obj instanceof FileInputStream) {
                is = (FileInputStream) obj;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            outputObject.setRtnCode("-9999");
        }

        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment; filename=" + "test.zip");

        if (is != null) {
            int lenth;
            while ((lenth = is.read()) > -1) {
                outputStream.write(lenth);
                outputStream.flush();
            }
            outputStream.close();
        }

        /*FileInputStream fileInputStream = new FileInputStream(new File(this.getClass().getResource("/").getPath() + "/" + "files/" + "template.txt"));

        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment; filename=" + "test.zip");

        if (fileInputStream != null) {
            int lenth;
            while ((lenth = fileInputStream.read()) > -1) {
                outputStream.write(lenth);
                outputStream.flush();
            }
            outputStream.close();
        }*/
    }

    @RequestMapping(value = "/showDownloadList", method = RequestMethod.POST)
    @ResponseBody
    public OutputObject showDownloadList(@InputObject com.example.springboot.bean.InputObject inputObject, OutputObject outputObject) throws Exception {
        inputObject.getParams().put("dbKey", "ngwf");
        iSqlProductService.showDownloadList(inputObject, outputObject);
        return outputObject;
    }
}
