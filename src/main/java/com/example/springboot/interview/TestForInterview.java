package com.example.springboot.interview;

import java.util.ArrayList;
import java.util.Comparator;

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
    }
}
