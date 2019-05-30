package com.example.springboot.util;

import org.springframework.util.FileCopyUtils;

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
//        FileInputStream fileInputStream = new FileInputStream(new File("/Users/smile_mylife/Desktop/reload.png"));
//        boolean b = fileCheck(fileInputStream);

        delDirectory(new File("/Users/smile_mylife/Desktop/ceshi"));
    }


    /**
     * 二进制字节转16进制
     * @param bArr
     * @return
     */
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

    /**
     * @param dirFile
     * @return
     */
    public static boolean delDirectory(File dirFile) {
        //如果传入的是文件，直接返回
        if (dirFile.isFile()) {
            return false;
        }
        //如果是文件夹，则直接递归删除
        try {
            if (dirFile.isDirectory()) {
                File[] files = dirFile.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isFile()) {
                        files[i].delete();
                    } else if (files[i].isDirectory()) {
                        delDirectory(files[i]);
                        files[i].delete();
                    }
                }
                dirFile.delete();
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
