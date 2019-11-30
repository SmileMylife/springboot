package com.example.springboot.common.bean;

/**
 * Created by ZhangPei on 2019/11/27.
 */
public class PageException extends RuntimeException {

    public PageException(String message) {
        super(message);
    }
}
