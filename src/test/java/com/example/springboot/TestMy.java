package com.example.springboot;

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
