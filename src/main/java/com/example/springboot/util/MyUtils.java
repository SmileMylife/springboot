package com.example.springboot.util;

import java.io.*;
import java.math.BigInteger;

/**
 * Created by ZhangPei on 2019/3/6.
 */
public class MyUtils {
    public static void main(String[] args) throws IOException {
        fileInputToString();
    }

    public static void fileInputToString() throws IOException {

        BigInteger bigInteger = new BigInteger("1903201024330146001");

        //将文件中竖列的内容按照一定规则进行拼接
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/smile_mylife/Desktop/test.txt")));
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            String otherTemplate = "UPDATE t_sr_type_relation SET rlt_val = '1903191157390249748', app_clfc_cd = '19' WHERE srv_reqst_type_id = %s AND prov_code = '00030018' AND rl_id > 0";
            String template = "INSERT INTO t_sr_type_relation(RL_ID, SRV_REQST_TYPE_ID, TENANT_ID, PROV_CODE, APP_CLFC_CD, SEQPRC_TMPLT_ID, NODE_CODE, RLT_VAL, PAGE_ID, LIST_NAME, ORG_BRNCH_ID, OP_STAFF_ID, OP_TIME, OP_NO, VALID_FLAG) VALUES(%s, %s, '100000', '00030018', '19', null, null, '1903191157390249748', null, null, '00030018', 'CQT0001', now(), null, '1');";
            String delTmplt = "DELETE FROM t_sr_type_relation WHERE rl_id = %s AND prov_code = '00030018';";
            String format = String.format(template, "'" + bigInteger.toString() + "'", "'" + s + "'");

            String replace = s.replace(" ", "");

            sb.append("'" + replace + "'").append(",");

            String delResult = String.format(delTmplt, "'" + bigInteger.toString() + "'");

            String otherTemplateResult = String.format(otherTemplate, "'" + replace + "'");
//            System.out.println(delResult);
//            System.out.println();
//            System.out.println(format);

//            System.out.println(otherTemplateResult);
//            System.out.println();

            bigInteger = bigInteger.add(new BigInteger("1"));
        }

        String result = sb.toString();

        System.out.println("(" + result.substring(0, result.length() - 1) + ")");
        bufferedReader.close();
    }
}
