package com.example.springboot.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by ZhangPei on 2019/7/18.
 */
@Configuration
public class ThreadPoolConfiguration {
    @Value("${threadpool.core-pool-size}")
    private int corePoolSize;

    @Value("${threadpool.max-pool-size}")
    private int maxPoolSize;

    @Value("${threadpool.queue-capacity}")
    private int queueCapacity;

    @Value("${threadpool.keep-alive-seconds}")
    private int keepAliveSeconds;


    @Bean
    public ThreadPoolTaskExecutor threadPoolCreater() {
        System.out.println("被bean注解打上的方法是不是会自动执行？");
        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        threadPoolExecutor.setCorePoolSize(this.corePoolSize);
        threadPoolExecutor.setMaxPoolSize(this.maxPoolSize);
        threadPoolExecutor.setQueueCapacity(queueCapacity);
        threadPoolExecutor.setKeepAliveSeconds(this.keepAliveSeconds);
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        return threadPoolExecutor;
    }

    //没打@bean注解的方法不会执行
    public Thread getThread() {
        System.out.println("没打bean注解会不会执行");
        return null;
    }
}
