package com.example.springboot.util;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * Created by ZhangPei on 2019/7/4.
 * 经过测试异步方法的关键步骤就是@EnableAsync注解和@Async，没有第一个注解不会异步调用。和实现接口没关系。
 */
@Component
@EnableAsync
public class AsyncTaskServiceImpl implements AsyncTaskService {

    @Async
    public void testAsync() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100000; i++) {
            sum += i;
            if (i == 100) {
                throw new Exception("抛出异常");
            }
        }

        System.out.println(sum);
    }
}
