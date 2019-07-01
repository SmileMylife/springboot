package com.example.springboot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ZhangPei on 2019/6/10.
 */
public class MyTestClass {
    public static void main(String[] args) {
        /*String s = "213123213213";

        System.out.println(Arrays.toString(s.split(",")));

        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("username", "zhangpei");
        map1.put("password", "123");

        map.put("test", map1);

        HashMap test = (HashMap) map.get("test");

        test.put("username", "zhangxu");

        System.out.println(test);

        List<String> list = new ArrayList<>();

        System.out.println(list instanceof ArrayList);


        ArrayList<Object> arrayList = new ArrayList<>();

        System.out.println(arrayList instanceof ArrayList);*/

        String s = "${wrkfm}12321312312312${count}";

        /*String replace = s.replace("${wrkfm}", "工单").replace("${haha}", "123").replace("${count}", "数量");
        System.out.println(replace);*/

        /*ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add("12332321323");

        for (int i = 0; i < arrayList.size(); i++) {

        }*/

    }
}
