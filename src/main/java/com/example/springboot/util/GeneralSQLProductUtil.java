package com.example.springboot.util;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * Created by ZhangPei on 2019/4/16.
 */
public class GeneralSQLProductUtil {


    /**
     * 将竖列文本按照sql中in的内容进行拼接
     * @param filePath
     * @throws IOException
     */
    public static String produceStringByRule(String filePath) throws IOException {

        //参数校验
        if (StringUtils.isBlank(filePath)) {
            throw new RuntimeException("参数校验异常");
        }
        //读取文本文件内容
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filePath)));
        String s = "";
        StringBuilder sb = new StringBuilder();
        sb.append("(");

        while ((s = bufferedReader.readLine()) != null) {
            sb.append("'").append(s).append("'").append(",");
        }

        String result = sb.substring(0, sb.length() - 1);

        return result + ")";

    }

    /**
     * 替换掉原字符串中的回车及换行
     * @param sourceStr
     * @return
     */
    public static String replaceEnterAndTabInString(String sourceStr) {
        return sourceStr.replace("\t\t", "").replace("\n", "");
    }


}
