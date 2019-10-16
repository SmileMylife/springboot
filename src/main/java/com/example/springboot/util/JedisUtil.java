package com.example.springboot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by ZhangPei on 2019/10/16.
 */
@Component
public class JedisUtil {
    private static JedisPool jedisPool;

    /**
     * 使用autowired注解可以声明初始化bean时所使用的构造器
     * @param jedisPool
     */
    @Autowired
    public JedisUtil(JedisPool jedisPool) {
        JedisUtil.jedisPool = jedisPool;
    }

    /**
     * 获取缓存中的值
     * @param key
     * @return
     */
    public static String getValByKey(String key) {
        Jedis jedis = jedisPool.getResource();
        String val = jedis.get(key);
        jedisPool.returnResource(jedis);
        return val;
    }

}
