package com.example.springboot.controller;

import com.example.springboot.common.annotations.InputObject;
import com.example.springboot.common.bean.OutputObject;
import com.example.springboot.util.Constants;
import com.example.springboot.util.OCRUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Base64;
import java.util.HashMap;

/**
 * Created by ZhangPei on 2019/11/22.
 */
@Controller
public class OcrController {
    /**
     * 图片转文字
     *
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
