package com.example.springboot.thread2;

/**
 * Created by ZhangPei on 2019/4/2.
 */
public class ThreadDemo4 {
    public static void main(String[] args) throws InterruptedException {
        Thread4 thread4 = new Thread4();
        Thread thread = new Thread(thread4);
        thread.start();

        Thread.sleep(1000);

        thread4.flag = false;
    }
}

class Thread4 implements Runnable {

    volatile boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (true) {
            synchronized (this) {
                i++;
                System.out.println("线程自增结果：" + i);
                if (!flag) {
                    try {
                        wait();
                        System.out.println("线程休眠");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
