package com.example.springboot.java_learn;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestJava {
    public static void main(String[] args) throws IOException {
        String s12 = null;
        boolean equals = StringUtils.equals(s12, "1");
        System.out.println(equals);

        String str = "ceshi/";

        String[] split = str.split("/");

        System.out.println(split.length);


        String s = null;
        String s1 = String.valueOf(s);
        System.out.println(s1);
        List<String> ips = Files.readAllLines(Paths.get("/Users/smile_mylife/Desktop/数据库ip.txt"));
        List<String> ports = Files.readAllLines(Paths.get("/Users/smile_mylife/Desktop/端口.txt"));
        List<String> database = Files.readAllLines(Paths.get("/Users/smile_mylife/Desktop/库名.txt"));

        if (ips.size() == ports.size()) {
            for (int i = 0; i < ips.size(); i++) {
                System.out.println(ips.get(i) + ":" + ports.get(i) + ":" + database.get(i));
            }
        }
    }
}
