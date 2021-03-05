package com.example.springboot.java_learn;


import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.Assert;

import java.util.Calendar;
import java.util.Date;

public class Son implements PersonInter {
    public static String var1 = null;

    public static void main(String[] args) {
        String format = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(format);
        Date truncate = DateUtils.truncate(new Date(), Calendar.MONTH);
        String format1 = DateFormatUtils.format(truncate, "yyyy-MM-dd HH:mm:ss");
        System.out.println(format1);

    }

    public static void test(String s) {
        System.out.println(s);
    }

    @Override
    public String toString() {
        return this.var1 + this.var2;
    }
}
