package com.example.springboot.util;

import java.io.*;
import java.util.UUID;

public class ProductServiceRequestType {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/smile_mylife/Desktop/节点/节点");
        File[] files = file.listFiles();
        if (files == null) {
            return;
        }

        String dbNm = "ngwf_zj";
        int num = 1;
        int fileNum = 1;
        String fileNameTemplate = "洛阳-服务请求-生产-%s-20210415-36-%s-INSERT-sql.txt";
        BufferedWriter bufferedWriter =
                new BufferedWriter(
                        new FileWriter(
                                new File(
                                        "/Users/smile_mylife/Desktop/信用购集团节点最终版/" +
                                                String.format(fileNameTemplate, dbNm, fileNum))));
        for (int i = 0; i < files.length; i++) {
            File tempFile = files[i];
            BufferedReader bufferedReader = new BufferedReader(new FileReader(tempFile));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                if (num % 1000 == 0) {
                    bufferedWriter.close();
                    bufferedWriter = new BufferedWriter(new FileWriter(new File("/Users/smile_mylife/Desktop/信用购集团节点最终版/" + String.format(fileNameTemplate, dbNm, ++fileNum))));
                }
                num++;
            }
            bufferedReader.close();
        }
        bufferedWriter.close();
        System.out.println("总共有" + num + "条数据");
    }
}
