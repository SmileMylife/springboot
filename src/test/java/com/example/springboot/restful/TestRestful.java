package com.example.springboot.restful;

import com.example.springboot.common.bean.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by ZhangPei on 2018/11/20.
 */
@RestController
public class TestRestful {
    private AtomicLong counter = new AtomicLong();  //atomiclong是线程安全的long
    private String template = "hello, %s";

    //测试restful风格的访问方式
    @RequestMapping(value = "/greeting", method = RequestMethod.POST)
    public Greeting greeting(String name) {
        System.out.println(name);
        return new Greeting(counter, String.format(template, name));    //注意string.format的使用方法
    }


    //测试如何在代码里边进行调用和访问
    @RequestMapping(value = "/greedingRestfulByCode", method = RequestMethod.GET)
    public void greedingRestfulByCode() {
        RestTemplate restTemplate = new RestTemplate();
        Greeting greeting = restTemplate.getForObject("http://localhost:8080/greeting", Greeting.class);
        System.out.println(greeting.toString());

    }
}
