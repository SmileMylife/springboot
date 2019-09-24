package com.example.springboot.util;

/**
 * Created by ZhangPei on 2019/9/24.
 */
public class SQlReplaceUtil {
    public static void replaceSqlByParams(String sql, String params) {

        String[] sqlArr = sql.split("\\s\\?\\s");
        String[] paramsArr = params.split("\\(String\\),");


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sqlArr.length; i++) {
            if (sqlArr.length - 1 == paramsArr.length && i == sqlArr.length - 1) {
                sb.append(sqlArr[i]);
            } else {
                sb.append(sqlArr[i]).append("'").append(paramsArr[i]).append("'");
            }
        }
        
        System.out.println(sb.toString());
    }
}
