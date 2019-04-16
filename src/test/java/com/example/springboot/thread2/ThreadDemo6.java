package com.example.springboot.thread2;

/**
 * Created by ZhangPei on 2019/4/2.
 */
public class ThreadDemo6 {
    public static void main(String[] args) throws InterruptedException {
        String s = new String();

        Thread6 thread6 = new Thread6(s);
        Thread thread = new Thread(thread6);
        Thread thread1 = new Thread(thread6);
        Thread thread2 = new Thread(thread6);

        thread.start();
        thread1.start();
        thread2.start();

        Thread.sleep(1000);

        synchronized (s) {
            s.notifyAll();
        }

    }
}

class Thread6 implements Runnable {
    private String s;

    public Thread6(String s) {
        this.s = s;
    }

    @Override
    public void run() {
        synchronized (s) {
            try {
                s.wait();
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
