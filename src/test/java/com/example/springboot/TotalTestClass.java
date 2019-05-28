package com.example.springboot;

/**
 * Created by ZhangPei on 2019/5/28.
 */
public class TotalTestClass {
    public static void main(String[] args) {
        String s = "201809113333X123456788";
        char c = s.substring(s.length() - 1).charAt(0);

        if (c >= 97 && c <= 122) {
            System.out.println("最后一位是字母");
        }
    }
}