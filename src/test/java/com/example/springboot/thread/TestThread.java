package com.example.springboot.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZhangPei on 2019/4/1.
 */
public class TestThread {
    public static void main(String[] args) throws InterruptedException {

        List<String> list = new ArrayList();

        Thread thread1 = new Thread(new MyThread1(list));
        thread1.start();
        Thread thread2 = new Thread(new MyThread2(list));
        thread2.start();


        TimeUnit.SECONDS.sleep(10);
        System.out.println(list);
    }
}
