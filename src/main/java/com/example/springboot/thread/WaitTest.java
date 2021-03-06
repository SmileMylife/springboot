package com.example.springboot.thread;

/**
 * Created by ZhangPei on 2019/4/2.
 */
class ThreadA extends Thread {
    public ThreadA(String name) {
        super(name);
    }

    public void run() {
        System.out.println("线程开启");
        synchronized (this) {
            try {
                Thread.sleep(1000); //  使当前线阻塞 1 s，确保主程序的 t1.wait(); 执行之后再执行 notify()
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " call notify()");
            // 唤醒当前的wait线程
            this.notify();
        }
    }
}

public class WaitTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadA t1 = new ThreadA("t1");
        synchronized (t1) {
            // 启动“线程t1”
            System.out.println(Thread.currentThread().getName() + " start t1");
            t1.start();
            // 主线程等待t1通过notify()唤醒。
            System.out.println(Thread.currentThread().getName() + " wait()");
//            t1.wait();  //  不是使t1线程等待，而是当前执行wait的线程等待
            System.out.println(Thread.currentThread().getName() + " continue");
            Thread.sleep(10000);
        }
    }
}