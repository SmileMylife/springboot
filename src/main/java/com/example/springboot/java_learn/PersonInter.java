package com.example.springboot.java_learn;

public interface PersonInter {
    public static final String var1 = "变量1";
    public static final String var2 = "变量2";
    default public void eat() {
        System.out.println("吃饭默认方法");
    }
}
