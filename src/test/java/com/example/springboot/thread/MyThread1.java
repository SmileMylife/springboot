package com.example.springboot.thread;

import java.util.List;

/**
 * Created by ZhangPei on 2019/4/1.
 */
public class MyThread1 implements Runnable {
    private List<String> list;

    public MyThread1(List<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            list.add(Thread.currentThread().getName() + i);
        }
    }
}
