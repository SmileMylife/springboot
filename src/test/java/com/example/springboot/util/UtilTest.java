package com.example.springboot.util;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by ZhangPei on 2019/4/16.
 */
public class UtilTest {
    public static void main(String[] args) throws IOException {
        String filePath = "/Users/smile_mylife/DeskTop/test.txt";
        String s = GeneralSQLProductUtil.produceStringByRule(filePath);
        System.out.println(s);
    }
}
