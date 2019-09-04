package com.example.springboot.thread;

/**
 * 唤醒和休眠都是针对锁对象的，而同步也是针对同一个对象才会有同步保护，例如，A线程synchronized的是a对象
 * 但是B线程synchronized的是b对象，这样要使A线程能影响B线程则必须同步的是同一对象。
 * Created by ZhangPei on 2019/4/2.
 */
public class ThreadDemo5 {
    public static void main(String[] args) throws InterruptedException {
        String s = new String();

        Thread thread = new Thread(new Thread5(s));
        //线程休眠10秒，不释放锁
        synchronized (s) {
            thread.start();
            Thread.sleep(Long.parseLong("1000"));
            s.wait();
            System.out.println("休眠结束，子线程唤醒了我");
        }
    }
}

class Thread5 implements Runnable {

    private String s;

    public Thread5(String s) {
        this.s = s;
    }

    @Override
    public void run() {
        System.out.println("子线程已执行");
        synchronized (s) {
            System.out.println("子线程执行代码");
            s.notify();
        }
    }
}