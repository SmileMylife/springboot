package com.example.springboot.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * jedis连接池配置
 * Created by ZhangPei on 2019/10/2.
 */
@Configuration
public class JedisPoolConfig extends redis.clients.jedis.JedisPoolConfig {
    @Value("${local.redis.jedis.pool.max-active}")
    private int maxTotal;    //最大连接数

    @Value("${local.redis.jedis.pool.max-idle}")
    private int maxIdle; //最大空闲连接数

    @Value("${local.redis.jedis.pool.max-wait}")
    private int _maxWait;    //最大等待时长

    @Bean("localJedisPoolConfig")
    public redis.clients.jedis.JedisPoolConfig getJedisPoolConfig() {
        redis.clients.jedis.JedisPoolConfig jedisPoolConfig = new redis.clients.jedis.JedisPoolConfig();
        jedisPoolConfig.setMaxActive(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWait(_maxWait);
        jedisPoolConfig.setWhenExhaustedAction((byte) 2);
        return jedisPoolConfig;
    }
}
