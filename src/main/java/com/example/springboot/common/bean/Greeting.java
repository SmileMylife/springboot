package com.example.springboot.common.bean;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by ZhangPei on 2018/11/20.
 */
public class Greeting {
    private AtomicLong counter;
    private String template;

    public Greeting(AtomicLong counter, String template) {
        this.counter = counter;
        this.template = template;
    }

    public AtomicLong getAtomicLong() {
        return counter;
    }

    public void setAtomicLong(AtomicLong atomicLong) {
        this.counter = atomicLong;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
