package com.example.springboot;

import org.apache.commons.collections.MapUtils;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by ZhangPei on 2019/5/28.
 */
public class TotalTestClass {
    public static void main(String[] args) {
        /*String s = "201809113333X123456788";
        char c = s.substring(s.length() - 1).charAt(0);

        if (c >= 97 && c <= 122) {
            System.out.println("最后一位是字母");
        }*/

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        String[] useranmes = MapUtils.getString(objectObjectHashMap, "useranme", "").split(",");

        if (true == true) {
            System.out.println("ceshi");
        }

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
    }
}