package com.example.springboot.util;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * Created by ZhangPei on 2019/7/4.
 */
@Component
public class AsyncTaskServiceImpl implements AsyncTaskService {

    @Async
    public void testAsync() {
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            sum += i;
        }

        System.out.println(sum);
    }
}
