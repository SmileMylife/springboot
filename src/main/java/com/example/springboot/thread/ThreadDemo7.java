package com.example.springboot.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class ThreadDemo7 {
    public static void main(String[] args) {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.initialize();
        threadPoolTaskExecutor.setCorePoolSize(4);
        threadPoolTaskExecutor.setMaxPoolSize(10);
        threadPoolTaskExecutor.setQueueCapacity(100);
        threadPoolTaskExecutor.setKeepAliveSeconds(10);

        List<Future<String>> result = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            CallableTest callableTest = new CallableTest();
            Future<String> submit = threadPoolTaskExecutor.submit(callableTest);
            result.add(submit);
            System.out.println("执行完了");
        }

        threadPoolTaskExecutor.shutdown();
        System.out.println("测试有没有阻塞");
        System.out.println(result);
    }
}

class CallableTest implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "测试";
    }
}
