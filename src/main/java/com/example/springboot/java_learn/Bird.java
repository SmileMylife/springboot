package com.example.springboot.java_learn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Bird {
    private String size;
    private String color;

    public Bird(String size, String color) {
        this.size = size;
        this.color = color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, color);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Bird) {
            Bird newBird = (Bird) obj;
            if (newBird.color.equals(this.color) && newBird.size == this.size) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(Runtime.getRuntime().availableProcessors());

        Set<Bird> set = new HashSet<>();

        Bird bird1 = new Bird("middle", "red");
        Bird bird2 = new Bird("middle", "red");

        set.add(bird1);
        set.add(bird2);
        System.out.println(bird1.equals(bird2));
        System.out.println(set);

        Bird bird3 = new Bird("large", "red");

        System.out.println(bird1.equals(bird3));
        try(FileInputStream fileInputStream = new FileInputStream(new File("/User/smile_mylife/Desktop/服务请求ngwf系统配置平台推送申请_20201210.xlsx"))) {
            System.out.println("略。。。。");
        } catch (IOException e) {
            System.out.println("找不到文件");
        }
    }
}
