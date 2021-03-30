package com.example.springboot.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileReadUtil {
    private static String CSVC_NO_DUDGE = "<variable>${variable}</variable>";

    private static String CSVC_HAS_DUDGE = "#if($variable)\n" +
            "    <variable>${variable}</variable>\n" +
            "    #end";

    private static String CSSl_NO_DUDGE = "<item>\n" +
            "    <Name>variable</Name>\n" +
            "    <Value>${variable}</Value>\n" +
            "</item>";

    private static String CSSl_HAS_DUDGE = "#if($variable)\n" +
            "    <item>\n" +
            "        <Name>variable</Name>\n" +
            "        <Value>${variable}</Value>\n" +
            "    </item>\n" +
            "    #end";


    private static List<String> list = new ArrayList<>(){{
        add("phoneNet");
        add("voiceQuality");
        add("activityPub");
        add("payQuestion");
    }};

    public static void main(String[] args) throws IOException {
//        readFileIntoToCode();
        productCsvcBody("CSSL");
    }

    public static void readFileIntoToCode() throws IOException {
        File resourceAsFile = Resources.getResourceAsFile("files/market.txt");
        List<String> allLines = Files.readAllLines(Paths.get(resourceAsFile.getPath()));
        for (int i = 0; i < allLines.size(); i++) {
            String field = allLines.get(i);
            String[] split = field.split("\\s+");
            System.out.println("inMap.put(\""+ split[0] + "\"" + ", );\t\t//" + split[1]);
        }
    }

    /**
     * 生产一级二线报文
     * @param sysCode
     * @throws IOException
     */
    public static void productCsvcBody(String sysCode) throws IOException {
        if (StringUtils.isBlank(sysCode)) {
            return;
        }

        File resourceAsFile = Resources.getResourceAsFile("files/market.txt");
        List<String> allLines = Files.readAllLines(Paths.get(resourceAsFile.getPath()));

        List<String> phoneNet = new ArrayList<>();
        List<String> voiceQuality = new ArrayList<>();
        List<String> activityPub = new ArrayList<>();
        List<String> payQuestion = new ArrayList<>();

        List<String> tempList = new ArrayList<>();
        for (int i = 0; i < allLines.size(); i++) {
            String field = allLines.get(i);
            if ("phoneNet".equals(field)) {
                tempList = phoneNet;
                continue;
            } else if ("voiceQuality".equals(field)) {
                tempList = voiceQuality;
                continue;
            } else if ("activityPub".equals(field)) {
                tempList = activityPub;
                continue;
            } else if ("payQuestion".equals(field)) {
                tempList = payQuestion;
                continue;
            }
            if (StringUtils.isNotBlank(field)) {
                tempList.add(field);
            }
        }

        //筛选两个集合中公共字段
        List<String> baseCommon = selectCommmonElem(phoneNet, voiceQuality);
//        baseCommon = selectCommmonElem(activityPub, payQuestion);

        StringBuilder baseSb = new StringBuilder();
        baseSb.append("#if(phoneNet=='1' || voiceQuality=='1')").append("\r\n");
        for (int i = 0; i < baseCommon.size(); i++) {
            if ("CSVC".equals(sysCode.toUpperCase())) {
                String commonField = baseCommon.get(i);
                String[] split = commonField.split("\\s+");
                if (split.length == 1) {
                    baseSb.append("\t").append(CSVC_NO_DUDGE.replace("variable", split[0])).append("\r\n");
                } else if (split.length == 2) {
                    baseSb.append("\t").append(CSVC_HAS_DUDGE.replace("variable", split[0])).append("\r\n");
                }
            } else if ("CSSL".equals(sysCode.toUpperCase())) {
                String commonField = baseCommon.get(i);
                String[] split = commonField.split("\\s+");
                if (split.length == 1) {
                    baseSb.append("\t").append(CSSl_NO_DUDGE.replace("variable", split[0])).append("\r\n");
                } else if (split.length == 2) {
                    baseSb.append("\t").append(CSSl_HAS_DUDGE.replace("variable", split[0])).append("\r\n");
                }
            }
        }
        //处理非公共部分

        baseSb.append("#end");

        System.out.println(baseSb.toString());
    }

    /**
     * 挑选两个集合中相同的元素
     * @param list1
     * @param list2
     * @return
     */
    public static List<String> selectCommmonElem(List<String> list1, List<String> list2) {
        List<String> resultList = new ArrayList<>();

        if (list1 == null || list2 == null) {
            return new ArrayList<>();
        }

        List<String> foreachList = list1.size() >= list2.size() ? list1 : list2;

        for (int i = 0; i < foreachList.size(); i++) {
            String s = foreachList.get(i);
            if (list1.contains(s) && list2.contains(s)) {
                resultList.add(s);
            }
        }
        return resultList;
    }
}
