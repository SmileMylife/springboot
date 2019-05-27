package com.example.springboot;

import java.awt.*;

/**
 * Created by ZhangPei on 2019/5/11.
 */
public class AnJianJingLing {
    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();
        robot.mouseMove(100, 100);

        Long aLong = new Long(999999999);

        int i = aLong.intValue();

        System.out.println(i);
    }
}