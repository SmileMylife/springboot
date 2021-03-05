package com.example.springboot;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;

public class TestInterClass implements TestInterface {
    @Override
    public void getConsumerInfo() {

    }

    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");


        ArrayList<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("2");
        list2.add("3");


        boolean b = CollectionUtils.containsAny(list1, list2);
        System.out.println(b);

    }
}
