package com.example.springboot.util;

import java.io.*;

/**
 * 深度克隆工具类
 * Created by ZhangPei on 2019/4/16.
 */
public class DeepCloneUtil {

    /**
     * 深度克隆方法
     * @param sourceObj
     * @param <T>
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T extends Serializable> T deepClone(T sourceObj) throws IOException, ClassNotFoundException {

        //先将对象信息写入二进制数组中存储，也可写入文件中
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        objectOutputStream.writeObject(sourceObj);

        //将二进制数组中的对象信息读取出来，然后再转化成对象
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

        return (T)objectInputStream.readObject();
    }
}
