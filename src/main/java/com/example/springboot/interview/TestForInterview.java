package com.example.springboot.interview;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ZhangPei on 2019/10/10.
 */
public class TestForInterview {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(9);
        list.add(7);
        list.add(11);
        list.add(2);
        list.add(8);
        list.add(9);

        System.out.println("排序前" + list);


        list.sort((o1, o2) -> {     //o1为需要排序的数组右边一个数，o2为左边
            if (o1 > o2) {  //升序如果右边大于左边则不需要换顺序，返回1即可。
                return 1;
            } else {
                return -1;
            }
        });

        System.out.println(list.toString());

        list.sort((o1, o2) -> {     //o1为需要排序的数组右边一个数，o2为左边
            if (o1 > o2) {
                return -1;      //降序如果右边大于左边则需要换顺序，返回-1即可。
            } else {
                return 1;
            }
        });

        System.out.println(list.toString());

        //对象排序
        Person person1 = new Person(23, "zhangpei1");
        Person person2 = new Person(21, "zhangpei2");
        Person person3 = new Person(25, "zhangpei3");
        Person person4 = new Person(57, "zhangpei4");
        Person person5 = new Person(23, "zhangpei5");
        Person person6 = new Person(30, "zhangpei6");
        Person person7 = new Person(23, "zhangpei7");

        List<Person> arrayList = new ArrayList<>();

        arrayList.add(person1);
        arrayList.add(person2);
        arrayList.add(person3);
        arrayList.add(person4);
        arrayList.add(person5);
        arrayList.add(person6);
        arrayList.add(person7);

        arrayList.sort((o1, o2) -> {
            if (o1.getAge() > o2.getAge()) {
                return 1;
            } else {
                return -1;
            }
        });
        
        System.out.println(arrayList.toString());
    }
}

class Person {
    private int age;
    private String username;

    public Person(int age, String username) {
        this.age = age;
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return this.getUsername();
    }
}