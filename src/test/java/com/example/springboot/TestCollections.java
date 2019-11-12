package com.example.springboot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.springboot.util.SQlReplaceUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.*;

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

    @Test
    public void testMybatis() throws IOException {
        String sql = "insert into t_sr_proc_ele_data values(1, 2, 3, ? , ? , ? ,6)";
        String params = "zhangpei(String), lisi(String), 1(String),";
        SQlReplaceUtil.replaceSqlByParams(sql, params);
    }

    @Test
    public void testRedis() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String username = jedis.get("username");
        System.out.println(username);
    }

    @Test
    public void test() {
        String s = ",1,2,3,1";
        String[] split = s.split(",");
        System.out.println(Arrays.toString(split));
        System.out.println(split.length);
    }

    @Test
    public void testCollection() {
        Set<String> set = new HashSet<>();
        String s = null;
        set.add(s);

        boolean empty = CollectionUtils.isEmpty(set);

        System.out.println(empty);
    }

    @Test
    public void testArraylist() {
        List<String> list = new ArrayList<>();
        String s = null;
        list.add(s);

        boolean empty = CollectionUtils.isEmpty(list);

        System.out.println(empty);
    }

    @Test
    public void testCar() {
        Car car = new Car("蓝色", "张佩");
        Car car1 = new Car();

        System.out.println(car1.getColor() + car1.drive);
    }

    @Test
    public void testProductSql() throws IOException {
//        String sql = "SELECT * FROM t_sr_task_info WHERE PROV_NM like '%?%' ORDER BY publish_route;";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/smile_mylife/Desktop/省份简称.txt")));
        String already = "北京,天津,河北,黑龙江,辽宁,吉林,陕西,宁夏,新疆,云南,四川,重庆,湖北,江苏,福建,广西,贵州,海南";
        String str = "";
        while ((str = bufferedReader.readLine()) != null) {
            if (!already.contains(str)) {
                System.out.print(str + ",");
            }
        }
        bufferedReader.close();
    }

    @Test
    public void testCpu() {
        while (true) {
            System.out.println("测试cput系统");
        }
    }

    @Test
    public void testSub() {
        String s = "测试你是:a,a,a";
        String result = s.substring(5);

        System.out.println(result);

    }

    @Test
    public void testSet() {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("username", "zhangpei");
        map1.put("password", "123");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("username", "zhangpei");
        map2.put("password", "123");

        Set<Map<String, Object>> set = new HashSet<>();
        set.add(map1);
        set.add(map2);

        System.out.println(map1.equals(map2));
        System.out.println(set.toString());

    }

    @Test
    public void testJson() {
        Map<Object, Object> map = new HashMap<>();
        map.put("username", null);
        map.put("password", "zhangpei");
        String s = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);

        Map result = JSON.parseObject(s, Map.class);
        String username = MapUtils.getString(result, "username");
        System.out.println(username);
    }

    @Test
    public void testColl() {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add("1");
        objects.add("2");
       objects.add("3");
        objects.add("4");
        List<Object> objects1 = objects.subList(1, 4);
        System.out.println(objects1);
    }

    @Test
    public void testJoin() {
        ArrayList<String> list = new ArrayList<>();
        list.add("28");
        list.add("21");

        String join = StringUtils.join(list, ",");

        System.out.println(join);
    }
}

class Car {
    private static String color;

    public String getColor() {
        return Car.color;
    }

    public static String drive;

    public Car() {
        Car.color = color;
    }

    public Car(String color, String drive) {
        this.color = color;
        this.drive = drive;
    }

    public Car(String color) {
        Car.color = color;
    }
}
