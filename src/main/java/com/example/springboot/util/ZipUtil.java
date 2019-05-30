package com.example.springboot.util;

/**
 * Created by ZhangPei on 2019/5/30.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZipUtil.class);

    /**
     * @param args 主方法
     */
    public static void main(String[] args) throws IOException {
        //第一个参数是需要压缩的源路径；第二个参数是压缩文件的目的路径，这边需要将压缩的文件名字加上去
        compress("/Users/smile_mylife/Desktop/ceshi", "/Users/smile_mylife/Desktop/test.zip");

        /*File file = new File("/Users/smile_mylife/Desktop/ceshi");

        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new File("/Users/smile_mylife/Desktop/test.zip")));

        if (file.isDirectory()) {
            for (File childFile : file.listFiles()) {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File("/Users/smile_mylife/Desktop/ceshi/" + childFile.getName())));
                byte[] bytes = new byte[new Long(file.length()).intValue()];
                bufferedInputStream.read(bytes);
                zipOutputStream.putNextEntry(new ZipEntry(childFile.getName()));
                zipOutputStream.write(bytes);

                bufferedInputStream.close();
            }
        }

        zipOutputStream.close();*/
    }

    /**
     * s
     * 压缩文件
     *
     * @param srcFilePath  压缩源路径
     * @param destFilePath 压缩目的路径
     */
    public static void compress(String srcFilePath, String destFilePath) {
        File srcFile = new File(srcFilePath);

        if (!srcFile.exists()) {
            throw new RuntimeException(srcFilePath + "不存在");
        }
        File targetZipFile = new File(destFilePath);

        try {

            FileOutputStream fos = new FileOutputStream(targetZipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);
            String baseDir = "";
            compressbyType(srcFile, zos, baseDir);
            zos.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * 按照原路径的类型就行压缩。文件路径直接把文件压缩，
     *
     * @param src
     * @param zos
     * @param baseDir
     */
    private static void compressbyType(File src, ZipOutputStream zos, String baseDir) {

        if (!src.exists())
            return;
        System.out.println("压缩路径" + baseDir + src.getName());
        //判断文件是否是文件，如果是文件调用compressFile方法,如果是路径，则调用compressDir方法；
        if (src.isFile()) {
            //src是文件，调用此方法
            compressFile(src, zos, baseDir);

        } else if (src.isDirectory()) {
            //src是文件夹，调用此方法
            compressDir(src, zos, baseDir);
        }

    }

    /**
     * 压缩文件
     */
    private static void compressFile(File file, ZipOutputStream zos, String baseDir) {
        try {
            if (!file.exists())
                return;
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            ZipEntry entry = new ZipEntry(baseDir + file.getName());
            zos.putNextEntry(entry);
            int count;
            byte[] buf = new byte[1024];
            while ((count = bis.read(buf)) != -1) {
                zos.write(buf, 0, count);
            }
            bis.close();

        } catch (Exception e) {
            LOGGER.error("压缩文件失败！");
        }
    }

    /**
     * 压缩文件夹
     */
    public static void compressDir(File dir, ZipOutputStream zos, String baseDir) {
        if (!dir.exists())
            return;
        File[] files = dir.listFiles();
        if (files.length == 0) {
            try {
                zos.putNextEntry(new ZipEntry(baseDir + dir.getName() + File.separator));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (File file : files) {
            compressbyType(file, zos, baseDir + dir.getName() + File.separator);
        }
    }
    
}
