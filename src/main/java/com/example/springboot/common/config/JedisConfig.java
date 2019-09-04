package com.example.springboot.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ZhangPei on 2019/3/5.
 */
@Configuration
public class JedisConfig {
    @Value("${spring.redis.host}")
    private String connectionIp;

    @Value("${spring.redis.port}")
    private String connectionPort;
}
