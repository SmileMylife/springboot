package com.example.springboot;

import com.example.springboot.bean.OutputObject;

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

       Map<String, Object> map = new HashMap<String, Object>();
       map.put("username", "zhangpei");
       map.put("password", "123");

        ArrayList<Map<String, Object>> list = new ArrayList<>();
        list.add(map);
        list.add(map);

        String result = list.toString();



    }
}
