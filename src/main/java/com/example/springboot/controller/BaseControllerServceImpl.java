package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

/**
 * Created by ZhangPei on 2019/10/19.
 */
public class BaseControllerServceImpl {
    @Autowired
    JedisPool jedisPool;    //要想使用spring管理的redis配置，该类必须也被spring所管理
}
