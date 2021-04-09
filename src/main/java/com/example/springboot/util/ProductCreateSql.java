package com.example.springboot.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCreateSql {
    public static void main(String[] args) throws IOException {
//        productCreateSql();
        testStringBuilder();
    }

    public static void productCreateSql() throws IOException {
        File file = new File("/Users/smile_mylife/Desktop/ngwf_jzyz.sql");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        List<String> list = new ArrayList<>();
        String line;
        String nowTableNm;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains("Table structure for ")) {
                nowTableNm = StringUtils.substringAfterLast(line, "Table structure for ");  //现在读取的表名
                list.add(nowTableNm);
            }
        }
        bufferedReader.close();
        List<String> actList = new ArrayList<>();       //工作流所有表
        for (int i = 0; i < list.size(); i++) {
            String tempEle = list.get(i);
            if (tempEle.startsWith("act_") || tempEle.contains("_act_")
                    || tempEle.startsWith("t_wfe_task") || tempEle.startsWith("t_wf_")) {
                actList.add(list.get(i));
            }
        }

        BufferedReader bufferedReaderBak = new BufferedReader(new FileReader(file));
        String lineBak;
        List<String> drops = new ArrayList<>();
        List<String> creates = new ArrayList<>();
        StringBuilder sb = null;
        String tableNm = null;
        while ((lineBak = bufferedReaderBak.readLine()) != null) {
            if (lineBak.startsWith("-- ----------------------------")) {
                if (sb != null) {
                    creates.add(sb.toString());
                }
                sb = new StringBuilder();
                tableNm = bufferedReaderBak.readLine();//表名
                bufferedReaderBak.readLine();   //-- ----------------------------
                drops.add(bufferedReaderBak.readLine());  //drop语句
                continue;
            }
            if (!actList.contains(tableNm) && StringUtils.isNotBlank(tableNm)) {
                sb.append(lineBak);
            }
        }

        bufferedReaderBak.close();

        BufferedWriter drop = new BufferedWriter(new FileWriter(new File("/Users/smile_mylife/Desktop/drop.txt")));
        BufferedWriter create = new BufferedWriter(new FileWriter(new File("/Users/smile_mylife/Desktop/create.txt")));
        IOUtils.write(StringUtils.join(drops, "\n\n"), drop);
        IOUtils.write(StringUtils.join(creates, "\n\n"), create);

        drop.flush();
        create.flush();
        drop.close();
        create.close();

    }

    /**
     * 测试stringBuilder的内存溢出
     * @throws IOException
     */
    public static void testStringBuilder() throws IOException {
        File file = new File("/Users/smile_mylife/Desktop/ngwf_jzyz.sql");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String line;

        StringBuilder sb = new StringBuilder();
        int num = 0;
        List<String> list = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            if (num == 5933) {
                System.out.println("测试");
            }
            list.add(sb.toString());
            sb.append(line);
            num++;
        }
    }
}
