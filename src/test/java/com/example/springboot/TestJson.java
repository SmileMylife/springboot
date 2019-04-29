package com.example.springboot;

import com.example.springboot.bean.OutputObject;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Created by ZhangPei on 2019/3/26.
 */
public class TestJson {
    public static void main(String[] args) {
        OutputObject outputObject = new OutputObject();
        List<HashMap<String, Object>> beans = outputObject.getBeans();
        List<HashMap<String, Object>> temp = outputObject.getBeans();

       /* HashMap<String, Object> map = new HashMap<>();
        map.put("username", "zhangpei");
        map.put("password", "123");
//        temp.add(map);
//        map.put("children", temp.toString());
//        beans.add(map);


//        String s = JSON.toJSONString(beans);
//        System.out.println(s);

        String s1 = beans.toString();

        List<Map<String, Object>> list = new ArrayList<>();

        list.add(map);
        list.add(map);

        System.out.println(list.toString());

        List<String> strings = Arrays.asList(list.toString().split(","));*/

        /*Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", "zhangpei");
        map.put("password", "123");

        ArrayList<Map<String, Object>> list = new ArrayList<>();
        list.add(map);
        list.add(map);

        String result = list.toString();


        for (int i = 0; i < 100; i++) {
            int a = (int) (Math.random() * 9 + 1);
            int b = (int) (Math.random() * 9);
            System.out.println(a);
            System.out.println(b);

            System.out.println("\n");
        }


        System.out.println(System.currentTimeMillis());

        *//*StringBuilder stringBuilder = new StringBuilder(new String("1"));
        System.out.println(stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "3").toString());*//*

        String s = "123";

        String s1 = s.substring(0, s.length() - 1) + "5";
        System.out.println(s1);*/

        /*String s = "<csvcid>123213213</csvcid>";

        String s1 = StringUtils.substringBetween(s, "<csvcid>", "</csvcid>");
        System.out.println(s1);*/


        String s = "<IndictSeq><IndictSeq></IndictSeq></IndictSeq>";


    }
}
