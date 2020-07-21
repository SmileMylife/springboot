package com.example.springboot.thread;

/**
 * 线程池测试类
 * Created by ZhangPei on 2019/7/18.
 */
public class ThreadPoolTestServiceImpl implements Runnable{

    @Override
    public void run() {
        try {
//            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName() + "开始休眠");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
