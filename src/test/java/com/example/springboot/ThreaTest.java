package com.example.springboot;

import java.util.concurrent.Callable;

public class ThreaTest implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("测试callable接口");
        Thread.sleep(3000);
        if ("ceshi".equals(Thread.currentThread().getName())) {
            Thread.sleep(10000);
            return "休眠了十秒";
        }
        return "休眠了4秒";
    }
}
