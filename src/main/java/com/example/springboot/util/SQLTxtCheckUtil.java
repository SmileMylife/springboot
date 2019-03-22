package com.example.springboot.util;

import java.io.File;
import java.io.FileFilter;
import java.util.*;

/**
 * Created by ZhangPei on 2019/3/21.
 */

public class SQLTxtCheckUtil {
    public static void main(String[] args) {

        File file = new File("/Users/smile_mylife/Desktop/脚本");

        List<String> fileNameList = new ArrayList<>();

        if (file.isDirectory()) {
            File[] files = file.listFiles(new MyFileFilter());
            for (int i = 0; i < files.length; i++) {
                fileNameList.add(files[i].getName());
            }
        }

        fileNameList.sort((o1, o2) -> {
            if (o1.charAt(0) < o1.charAt(02)) {
                return -1;
            } else {
                return 1;
            }
        });
        
        List<Object> fileNames = groupByFileName(fileNameList);


        for (int i = 0; i < fileNames.size(); i++) {
            System.out.println(fileNames.get(i));
            System.out.println();
        }
    }


    //根据脚本名列表进行分组
    public static List<Object> groupByFileName(List<String> list) {
        String flag;
        String head = "";
        int num = 0;
        ArrayList<Object> resultList = new ArrayList<>();
        while (true) {
            Iterator<String> iterator = list.iterator();
            ArrayList<String> tempList = new ArrayList<>();
            while (iterator.hasNext()) {
                String next = iterator.next();
                if (num == 0) {
                    flag = next;
                    String[] split = flag.split("-");
                    head = join(split, 0, 5, "-");
                    tempList.add(next);
                    iterator.remove();
                } else if (next.startsWith(head)) {
                    tempList.add(next);
                    iterator.remove();
                }
                num++;
            }
            resultList.add(tempList);
            if (list.size() == 0) {
                break;
            }

            head = "";
            num = 0;
        }
        return resultList;
    }

    //将数组某几位按规则拼接成字符串
    public static String join(String[] arr, int start, int end, String splitCharset) {
        if (arr == null || arr.length == 0) {
            return "";
        } else if (start < 0 || end < 0 || start > end) {
            return "";
        } else if (end > arr.length - 1 && start >= arr.length - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i >= start && i <= end) {
                sb.append(arr[i]).append(splitCharset);
            }
        }
        String result = sb.toString();

        return result.substring(0, result.length() - 1);
    }

    //文件过滤器（过滤文件夹和隐藏文件）
    static class MyFileFilter implements FileFilter {

        @Override
        public boolean accept(File pathname) {
            if (pathname.isDirectory() || pathname.getName().equals(".DS_Store")) {
                return false;
            }
            return true;
        }
    }

    //文件名校验规则
    public static String checkFileNameRule(List<Object> list) {

        StringBuilder sb = new StringBuilder();

        if (list.size() == 0) {
            return "需要校验的文件个数为0";
        }
        for (int i = 0; i < list.size(); i++) {
            Object o = list.get(i);
            if (o instanceof ArrayList) {
                ArrayList fileNames = (ArrayList) o;

            }
        }
        return null;
    }


    //校验脚本名缺少什么脚本
    public static String checkNeeds(List<String> list) {

        StringBuilder sb = new StringBuilder();
        if (list.size() != 0 && (list.size() == 1)) {
            String s = list.get(0);
            if (s.indexOf("INSERT") != -1) {
                //脚本为insert脚本
            }
        }

        return null;
    }

    //测试split方法
    public static void test(String filename5) {
        String[] split = filename5.split("-");
        String join = join(split, 0, 6, "-");
    }
}