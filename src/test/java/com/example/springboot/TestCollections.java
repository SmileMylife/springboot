package com.example.springboot;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by ZhangPei on 2019/9/4.
 */
public class TestCollections {
    public static void main(String[] args) throws IOException {
        //生成5万文件
        /*for (int i = 0; i < 50000; i++) {
            File file = new File("/Users/smile_mylife/Desktop/ceshi/" + i + ".txt");
//            file.createNewFile();
            FileCopyUtils.copy(new String("测试").getBytes("UTF-8"), file);
        }*/

        FTPClient ftpClient = new FTPClient();
        ftpClient.disconnect();
    }
}
