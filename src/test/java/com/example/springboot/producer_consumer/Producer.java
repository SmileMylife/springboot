package com.example.springboot.producer_consumer;

import java.util.Random;

/**
 * Created by ZhangPei on 2019/4/2.
 */
public class Producer implements Runnable {
    private Storage storage;
    public Producer(Storage storage) {
        this.storage = storage;
    }
    @Override
    public void run() {
        int i = 0;
        Random random = new Random();
        while (i < 10) {
            i++;
            Product product = new Product(i + "", "电话" + random.nextInt());
            storage.push(product);
        }
    }
}
