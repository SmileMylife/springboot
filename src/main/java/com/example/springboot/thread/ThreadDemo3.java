package com.example.springboot.thread;

/**
 * Created by ZhangPei on 2019/4/1.
 * java高并发编程需要保证三个特性，原子性、可见性、有序性
 * 原子性体现在某些操作要么同时成功，要么同时失败，而可见性是指当存在多个线程的时候，A线程的修改需要立即反映到B线程（或是对其他线程可见)
 * 有序性是指CPU可能会发生指令重排序，当存在多线程的时候可能会发生执行顺序不一致而导致的异常。
 * 使用volatile关键字可保证可见性和有序性，但不保证原子性，所以在使用了volatile关键字后进行i++操作还是会出现结果错误的情况。
 */
public class ThreadDemo3 {
    public static void main(String[] args) throws InterruptedException {
        /*Thread3 thread3 = new Thread3();
        thread3.start();
        TimeUnit.SECONDS.sleep(2);
        thread3.flag = false;
        System.out.println("主线程运行结束");*/

        /*Thread3_1 thread3_1 = new Thread3_1();
        Thread thread = new Thread(thread3_1);
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread3_1.flag = false;*/

        SaleTickets saleTickets = new SaleTickets("线程1");
        saleTickets.start();
        SaleTickets saleTickets1 = new SaleTickets("线程2");
        saleTickets1.start();


        /*SaleTickets_1 sale1 = new SaleTickets_1();
        Thread thread = new Thread(sale1);
        Thread thread1 = new Thread(sale1);
        Thread thread2 = new Thread(sale1);
        thread.start();
        thread1.start();
        thread2.start();*/


    }
}

class Thread3 extends Thread {
//    boolean flag = true;

    volatile boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            i++;
        }

        System.out.println("子线程执行结束");
    }
}

class Thread3_1 implements Runnable {

    boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            i++;
        }

        System.out.println("子线程执行结束");
    }
}

class SaleTickets extends Thread {
    volatile static int total = 100;
    private String s = new String();

    public SaleTickets(String s) {
        this.setName(s);
    }

    @Override
    public void run() {
        while (true) {

            synchronized (s) {
                if (total > 0) {
                    total--;
                    System.out.println(Thread.currentThread().getName() + "=" + total);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    break;
                }
            }
        }
    }
}

class SaleTickets_1 implements Runnable {
    volatile int total = 100;
    private String s = new String();

    @Override
    public void run() {
        while (true) {
            if (total > 0) {        //此写法存在问题，最后结果会出现-1和-2的情况，出现问题的原因如下：
                // 假设有A,B,C三个线程，A,B,C线程同时执行到了同步代码块部分，然后A线程执行到i=0的时候，由于B,C线程等待A线程释放锁，此时A线程已经将i更新为0，
                //但是B,C线程已经进入了i>0的判断，等到A释放锁之后，B线程会执行i自减为-1，然后刷新到主存，然后B线程释放锁，C线程-1再自减。
                synchronized (s) {

                    total--;
                    System.out.println(Thread.currentThread().getName() + "=" + total);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                break;
            }
        }
    }
}