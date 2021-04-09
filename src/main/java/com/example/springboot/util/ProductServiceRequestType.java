package com.example.springboot.util;

import java.io.*;

public class ProductServiceRequestType {
    private static final String SQL_INFO = "/*\n" +
            "*地址：ngwf-xyge.mysql.svc.lyarmcore.hpc\n" +
            "*端口号：20001\n" +
            "*库名：ngwf_xyge\n" +
            "*用户名：ngwf_xyge\n" +
            "*是否中间件：否\n" +
            "*数据库类型：mysql\n" +
            "*工单联系人电话: 周亚坤 15238373357\n" +
            "*工单执行失败是否回滚:全部回滚\n" +
            "*/\n";

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/smile_mylife/Desktop/节点信息");
        File[] files = file.listFiles();
        if (files == null) {
            return;
        }

        String dbNm = "ngwf_xyge";
        int num = 1;
        int fileNum = 1;
        String fileNameTemplate = "洛阳-服务请求-生产-%s-20210415-36-%s-INSERT-sql.txt";
        BufferedWriter bufferedWriter =
                new BufferedWriter(
                        new FileWriter(
                                new File(
                                        "/Users/smile_mylife/Desktop/信用购集团节点最终版/" +
                                                String.format(fileNameTemplate, dbNm, fileNum))));
        bufferedWriter.write(SQL_INFO);
        for (int i = 0; i < files.length; i++) {
            File tempFile = files[i];
            BufferedReader bufferedReader = new BufferedReader(new FileReader(tempFile));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                if (num % 1000 == 0) {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    bufferedWriter = new BufferedWriter(new FileWriter(new File("/Users/smile_mylife/Desktop/信用购集团节点最终版/" + String.format(fileNameTemplate, dbNm, ++fileNum))));
                    bufferedWriter.write(SQL_INFO);
                }
                num++;
            }
            bufferedWriter.flush();
            bufferedReader.close();
        }
        bufferedWriter.close();
        System.out.println("总共有" + num + "条数据");
    }
}
