package com.example.springboot.thread;

import org.apache.commons.collections.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ZhangPei on 2019/8/27.
 */
public class SrvRqstTypeThreadServiceImpl {
    public static void main(String[] args) {
//        Map<String, Object> flag = new HashMap<>();
        List<String> list = new ArrayList<>();

        ConcurrentHashMap<Object, Object> flag = new ConcurrentHashMap<>();


        Thread thread1 = new Thread(new ThreadOne(flag, list), "线程一");
        thread1.start();

        Thread thread2 = new Thread(new ThreadTwo(flag, list), "线程二");
        thread2.start();
        System.out.println("主线程执行结束");
    }
}

class ThreadOne implements Runnable {
    private volatile Map<String, Object> flag;
    private volatile List list;

    public ThreadOne(Map<String, Object> flag, List<String> list) {
        this.flag = flag;
        this.list = list;
    }

    public ThreadOne(ConcurrentHashMap flag, List<String> list) {
        this.flag = flag;
        this.list = list;
    }

    @Override
    public void run() {
        System.out.println("线程一执行开始");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.flag.put("flag", true);
        this.list.add("线程1执行结束了");
        System.out.println("线程一执行结束");

    }
}


class ThreadTwo implements Runnable {
    private volatile Map<String, Object> flag;
    private volatile List list;

    public ThreadTwo(Map<String, Object> flag, List<String> list) {
        this.flag = flag;
        this.list = list;
    }

    public ThreadTwo(ConcurrentHashMap flag, List<String> list) {
        this.flag = flag;
        this.list = list;
    }


    @Override
    public void run() {
        System.out.println("线程二执行开始");
        while (true) {
            if (MapUtils.getBooleanValue(flag, "flag")) {
                System.out.println(list);
                break;
            }
        }
    }
}

