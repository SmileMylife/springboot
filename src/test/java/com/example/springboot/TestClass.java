package com.example.springboot;

import org.apache.commons.collections.MapUtils;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by ZhangPei on 2018/12/1.
 */
public class TestClass {
    public static void main(String[] args) throws IOException {
        /*String s = "éå®c ççÊ";
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
        HashMap<String, Object> map = new HashMap<>();
        boolean isInterface = MapUtils.getBooleanValue(map, "isInterface");
        System.out.println(isInterface);

        String s4 = new String("张佩".getBytes(), "ISO-8859-1");
        System.out.println(s4);

        String s5 = new String(s4.getBytes(), "utf-8");
        System.out.println(s5);


        String s6 = "";
        byte[] bytes = s6.getBytes();
        System.out.println(Arrays.toString(bytes));
        OutputStream outputStream = new FileOutputStream(new File("./test.png"));
        outputStream.write(bytes, 0, bytes.length);*/

        String[] split = "".split(",");


        System.out.println(split[0]);
    }
}