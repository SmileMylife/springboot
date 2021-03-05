package com.example.springboot.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis配置
 * Created by ZhangPei on 2019/3/5.
 */
@Configuration
public class MyJedisPool {
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Autowired
    @Qualifier("localJedisPoolConfig")
    private redis.clients.jedis.JedisPoolConfig jedisPoolConfig;

    @Bean
    public JedisPool getJedisPool() {
        return new JedisPool(jedisPoolConfig, host, port);
    }

}
