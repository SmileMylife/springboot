package com.example.springboot.util;

/**
 * Created by ZhangPei on 2019/5/27.
 */
public class Constants {

    //sql文件内容注释
    public static final String SQL_FILE_CONTENT = "/*\n" +
            "*地址：%s\n" +
            "*端口号：%s\n" +
            "*库名：%s\n" +
            "*用户名：%s\n" +
            "*操作数量：%s\n" +
            "*是否中间件：否\n" +
            "*数据库类型：mysql\n" +
            "*工单联系人电话：%s (%s)\n" +
            "*工单执行失败是否全部回滚：全部回滚\n" +
            "*/\n" +
            "\n";

    //sql文件名，参数依次为：库名、时间、jira号、联系人拼音、操作类型
    public static final String SQL_TEMPLATE = "洛阳-服务请求-生产-%s-%s-(%s-%s)-%s-sql.txt";

    //回滚sql文件名
    public static final String SQL_ROLLBACK_TEMPLATE = "洛阳-服务请求-生产-%s-%s-(%s-%s)-%s-rollback-sql.txt";

    //获取系统换行符
    public static final String LINE_BREAK = System.getProperty("line.separator");



































    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 31; i++) {
            if (i < 10) {
                sb.append("\"").append("0003000" + i).append("\"").append(", ");
            } else {
                sb.append("\"").append("000300" + i).append("\"").append(", ");
            }
        }

        String s = sb.toString();
        System.out.println(s.substring(0, s.length() - 2));

        System.out.println("123".substring(0, 2));
    }

}
