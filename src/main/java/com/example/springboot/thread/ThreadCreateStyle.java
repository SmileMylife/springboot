package com.example.springboot.thread;

/**
 * 创建线程的两种方式，一种是使用继承thread类，一种是实现runnable接口，继承的方式不能多继承
 * 线程创建完毕之后直接调用run（）方法是同步执行的，而且通过runnable形式创建的线程必须要用Thread进行包装之后才能使用
 * Created by ZhangPei on 2019/3/17.
 */
public class ThreadCreateStyle {
    public static void main(String[] args) {
//        Thread1 thread1 = new Thread1();
//        thread1.run();
//        thread1.start();
        Thread thread2 = new Thread(new thread2());
        thread2.start();
        System.out.println("执行调用run方法是同步的");
    }
}


//线程创建方式
class Thread1 extends Thread {

    @Override
    public void run() {
        while (true) {

        }
    }
}

class thread2 implements Runnable {
    @Override
    public void run() {
        while (true) {

        }
    }
}