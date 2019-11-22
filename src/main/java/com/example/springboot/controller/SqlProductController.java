package com.example.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.springboot.common.annotations.InputObject;
import com.example.springboot.common.bean.OutputObject;
import com.example.springboot.service.ISqlProductService;
import com.example.springboot.util.Constants;
import com.example.springboot.util.OCRUtil;
import com.example.springboot.util.QRCodeUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
        inputObject.getParams().put("dbKey", "ngwf");
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
        inputObject.getParams().put("dbKey", "ngwf");
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
        inputObject.getParams().put("dbKey", "ngwf");
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

    /**
     * 登录逻辑验证，需要兼容二维码扫描登录
     * @param inputObject
     * @param outputObject
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginSqlProduct")
    public ModelAndView login(@InputObject com.example.springboot.common.bean.InputObject inputObject, OutputObject outputObject, HttpServletRequest request) {
        String username = MapUtils.getString(inputObject.getParams(), "username");
        String password = MapUtils.getString(inputObject.getParams(), "password");

        ModelAndView loginModelAndView = new ModelAndView();
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            loginModelAndView.setViewName("login");
            return loginModelAndView;
        } else if ("root".equals(username)) {
            loginModelAndView.setViewName("index");
        }
        loginModelAndView.addObject("isError", "false");
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60*30);     //60秒过期
        session.setAttribute(request.getSession().getId(), true);
        return loginModelAndView;
    }

    /**
     * 注销
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView distroySession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    /**
     * 刷新二维码
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping(value = "/refreshQRcode", method = RequestMethod.GET)
    public void refreshQRcode(@InputObject com.example.springboot.common.bean.InputObject inputObject, OutputObject outputObject, HttpServletResponse response) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        UUID uuid = UUID.randomUUID();
        QRCodeUtils.encode("http://172.18.255.19:8080/loginSqlProduct?uuid=" + uuid.toString(), byteArrayOutputStream);
        response.setContentType("image/png");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(byteArrayOutputStream.toByteArray());
        byteArrayOutputStream.close();
    }

    /**
     * 图片转文字
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping(value = "/imagToword", method = RequestMethod.POST)
    @ResponseBody
    public OutputObject imagToword(@InputObject com.example.springboot.common.bean.InputObject inputObject, OutputObject outputObject) throws Exception {
        HashMap<String, Object> params = inputObject.getParams();
        String base64Str = MapUtils.getString(params, "base64Str");

        if (StringUtils.isBlank(base64Str)) {
            outputObject.setRtnCode(Constants.RTN_CODE_FAIL);
            outputObject.setRtnMsg("图片数据为空！");
            return outputObject;
        }

        String[] split = base64Str.split(",");

        if (split.length > 1) {
            base64Str = split[1];
        } else {
            throw new Exception("图片文件数据格式有误！");
        }

        byte[] imgBytes = Base64.getDecoder().decode(base64Str);

        JSONObject json = OCRUtil.getWordsByGeneral(imgBytes);

        if (json.has("error_code")) {
            throw new Exception("图文解析出错！");
        }

        System.out.println(json.toString());

        org.json.JSONArray arr = json.getJSONArray("words_result");

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arr.length(); i++) {
            JSONObject jsonObject = arr.getJSONObject(i);
            String words = jsonObject.getString("words");
            if (i == arr.length() - 1) {
                sb.append(words);
            } else {
                sb.append(words + "\r\n");
            }
        }

        outputObject.getBean().put("word", sb.toString());
        outputObject.setRtnCode(Constants.RTN_CODE_SUCC);
        outputObject.setRtnMsg("识别成功！");
        return outputObject;

    }
}
