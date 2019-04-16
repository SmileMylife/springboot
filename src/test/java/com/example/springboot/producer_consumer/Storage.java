package com.example.springboot.producer_consumer;

/**
 * Created by ZhangPei on 2019/4/2.
 */
public class Storage {
    private Product[] products = new Product[10];
    private int top = 0;

    public synchronized void push(Product product) {
        while (top == products.length) {
            try {
                System.out.println("仓库已满，进入等待状态");
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        products[top++] = product;
        System.out.println(Thread.currentThread().getName() + " 生产了" + product);
        System.out.println("唤醒所有生产者");
        notifyAll();
    }

    public synchronized Product pop () {
        while (products.length == 0) {
            try {
                System.out.println("仓库产品不足");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        --top;
        Product p = new Product(products[top].getId(), products[top].getName());
        products[top] = null;
        System.out.println(Thread.currentThread().getName() + "");
        System.out.println("consumer notifyAll");
        notifyAll();    //唤醒等待线程
        return p;
    }
}
