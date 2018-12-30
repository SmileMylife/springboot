package com.example.springboot;

import java.io.UnsupportedEncodingException;

/**
 * Created by ZhangPei on 2018/12/1.
 */
public class TestClass {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "éå®c ççÊ";
        String s1 = new String(s.getBytes(), "ISO-8859-1");
        String s2 = new String(s.getBytes(), "UTF-8");
        System.out.println(s2);
        String s3 = "{\n" +
                "    \"object\":null,\n" +
                "    \"rtnCode\":\"0\",\n" +
                "    \"rtnMsg\":\"成功\",\n" +
                "    \"sn\":\"\",\n" +
                "    \"bean\":{\n" +
                "        \"total\":\"5\"\n" +
                "    },\n" +
                "    \"beans\":[\n" +
                "        {\n" +
                "            \"username\":\"zhangpei\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"age\":\"22\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }
}