package com.example.springboot.producer_consumer;

/**
 * Created by ZhangPei on 2019/4/2.
 */
public class Consumer implements Runnable {
    private Storage storage;

    public Consumer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 10) {
            i++;
            storage.pop();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
