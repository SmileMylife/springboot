package com.example.springboot.thread;

/**
 * Created by ZhangPei on 2019/3/17.
 */
public class ThreadVarForCommon {
    public static void main(String[] args) {
        String name = Thread.currentThread().getName();
        System.out.println("当前线程名称：" + name);

        Thread thread = new Thread(new SaleTicket());
        thread.start();

        Thread thread1 = new Thread(new SaleTicket());
        thread1.start();
    }
}

//出售火车票的问题

class SaleTicket implements Runnable {
    private static int allTickets = 100;

    @Override
    public void run() {
        while (allTickets > 0) {
            System.out.println("当前线程名称：" + Thread.currentThread().getName() + "，" + allTickets--);
        }
    }
}
