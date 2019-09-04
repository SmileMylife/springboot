package com.example.springboot;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * Created by ZhangPei on 2019/8/15.
 */
public class ProductSql {
    public static void main(String[] args) throws IOException {
        String delSql = "delete from t_sr_service_request_type  where SRV_REQST_ID in (%s) and REC_ID >0;";

        String provName = "gd";

        String delRollBack = "INSERT INTO t_sr_service_request_type  SELECT * FROM zxdba_bak.zxdba_2019081401_t_sr_service_request_type_" + provName + " WHERE REC_ID >0;";

        String delBackup = "CREATE table zxdba_bak.zxdba_2019081401_t_sr_service_request_type_" + provName + " LIKE t_sr_service_request_type;\n" +
                "\n" +
                "INSERT INTO zxdba_bak.zxdba_2019081401_t_sr_service_request_type_" + provName + " SELECT * FROM t_sr_service_request_type  where SRV_REQST_ID in (%s);";


        File file = new File("/Users/smile_mylife/Desktop/ids.txt");

        FileReader fileReader = new FileReader(file);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String str = "";
        StringBuilder sb = new StringBuilder(str);
        while ((str = bufferedReader.readLine()) != null) {
            sb.append("'").append(str).append("'").append(",");
        }

        int index = sb.lastIndexOf(",");

        StringBuilder replace = sb.replace(index, index + 1, "");

        System.out.println(String.format(delSql, replace));
        
        
        System.out.println();
        System.out.println();
        System.out.println(delRollBack);

        System.out.println();
        System.out.println();
        
        System.out.println(String.format(delBackup, replace));
        
        fileReader.close();
    }
}
