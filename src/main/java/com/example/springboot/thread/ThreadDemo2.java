package com.example.springboot.thread;

/**
 * Created by ZhangPei on 2019/4/1.
 */
public class ThreadDemo2 {
    public static void main(String[] args) {
        Thread2 thread1 = new Thread2();
        Thread2 thread2 = new Thread2();
        thread1.start();
        thread2.start();
    }
}

class Thread2 extends Thread {
    public static int total = 100;

    @Override
    public void run() {
        while (true) {
            if (total > 0) {
                total--;
                System.out.println(Thread.currentThread().getName() + "=" + total);
            } else {
                break;
            }
        }
    }
}