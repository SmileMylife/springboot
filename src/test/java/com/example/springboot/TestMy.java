package com.example.springboot;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ZhangPei on 2019/1/29.
 */
public class TestMy {
    public static void main(String[] args) {

        /*HashMap<String, Object> map = new HashMap<>();
        long number = MapUtils.getLong(map, "number");

        System.out.println(number);*/

        Person person = new Person();
        Person person1 = new Person();

        person.add();

        System.out.println(person1.getNum());

        String s = null;
        System.out.println(s + "测试");

        String[] split = "".split(",");
        List<String> strings = Arrays.asList(split);
        System.out.println(strings);

        System.out.println("".charAt(16));
    }
}

class Person {
    public static int num = 100;

    public int getNum() {
        return num;
    }

    public void add() {
        num += 1;
    }
}
