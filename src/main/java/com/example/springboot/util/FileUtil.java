package com.example.springboot.util;

import java.io.*;

/**
 * Created by ZhangPei on 2018/12/3.
 */
public class FileUtil {

    /**
     * 校验文件格式是否正确
     * @param inputStream
     * @return
     */
    public static boolean fileCheck(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[10];
        String s = bytesToHexString(bytes);
        System.out.println(s);
        return true;
    }

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("/Users/smile_mylife/Desktop/reload.png"));
        boolean b = fileCheck(fileInputStream);
    }

    public static String bytesToHexString(byte[] bArr) {
        StringBuffer sb = new StringBuffer(bArr.length);
        String sTmp;

        for (int i = 0; i < bArr.length; i++) {
            sTmp = Integer.toHexString(0xFF & bArr[i]);
            if (sTmp.length() < 2)
                sb.append(0);
            sb.append(sTmp.toUpperCase());
        }

        return sb.toString();
    }
}
