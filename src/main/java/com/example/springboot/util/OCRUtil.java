package com.example.springboot.util;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;
import org.springframework.util.FileCopyUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.*;
import java.util.HashMap;

/**
 * Created by ZhangPei on 2019/11/19.
 */
public class OCRUtil {
    //图文识别配置
    public static final String APP_ID = "17799609";

    public static final String API_KEY = "WpE4wbXY7Hvko8SsWezevHG9";

    public static final String SECRET_KEY = "ZetzKeuneFMmviIwRNqpPrfDiEusRjNR";

    /**
     * 高精度图文识别
     */
    public static AipOcr getWordsByHighPrecision() {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//            client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//            client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//            System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
        return client;
    }

    /**
     * 通用图文识别
     *
     * @param files
     */
    public static JSONObject getWordsByGeneral(byte[] files) throws IOException {
        AipOcr client = getWordsByHighPrecision();

        HashMap<String, String> options = new HashMap<>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");


        // 参数为本地路径
        /*String image = "test.jpg";
        JSONObject res = client.basicGeneral(image, options);
        System.out.println(res.toString(2));*/

        // 参数为二进制数组
        JSONObject res = client.basicGeneral(files, options);
        System.out.println(res.toString(2));
        return res;
    }


}
