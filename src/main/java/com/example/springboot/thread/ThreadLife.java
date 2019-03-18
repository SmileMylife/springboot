package com.example.springboot.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by ZhangPei on 2019/3/17.
 */
public class ThreadLife {
    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(new ThreadTest());
//        thread.start();
//        TimeUnit.SECONDS.sleep(10);
//        thread.notify();

        //测试sleep方法是否会释放cpu资源
//        Thread thread = new Thread(new ThreadTest());
//        thread.start();
//
//        System.out.println("hahaha");


        Thread thread1 = new Thread(new ThreadTest1(), "线程名1");
        thread1.start();

        TimeUnit.SECONDS.sleep(3);

        Thread thread2 = new Thread(new ThreadTest1(), "线程名2");
        thread2.start();
    }
}

//测试线程被唤醒
class ThreadTest implements Runnable {
    private int num = 100;

    @Override
    public void run() {
        while (true) {
            System.out.println("当前执行的Num的值为" + num--);
            if (num == 50) {
                try {
                    TimeUnit.SECONDS.sleep(5);
//                    Thread.currentThread().wait();  //线程进入阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (num <= 0) {
                break;
            }
        }
    }
}


//测试sleep方法会不会释放同步的对象
class ThreadTest1 implements Runnable {
    private int num = 100;
    private static String comOjb = "";


    @Override
    public void run() {
        while (true) {
            if ("线程名2".equals(Thread.currentThread().getName())) {  //锁释放的瞬间可能存在两个线程执行顺序的问题。
                System.out.println("可以更改！");
                comOjb = "zhangpei";
            }
            synchronized (comOjb) {     //必须保证锁中的对象是唯一的
                System.out.println(Thread.currentThread().getName() + "当前执行的Num的值为" + num-- + "----" + comOjb);
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (num < 0) {
                break;
            } else {
                num --;
            }
            System.out.println();

            /*System.out.println(Thread.currentThread().getName());
            if (num == 50 && Thread.currentThread().getName().equals("线程名1")) {
                try {
                    System.out.println("线程1进入休眠");
//                    TimeUnit.SECONDS.sleep(5);
//                    Thread.currentThread().sleep(30000);  //线程进入阻塞
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (num == 50 && Thread.currentThread().getName().equals("线程名2")) {
                comOjb = "zhangpei";
            }
            if (num < 0) {
                break;
            }*/
        }
    }
}