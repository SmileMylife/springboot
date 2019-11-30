package com.example.springboot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baidu.aip.ocr.AipOcr;
import com.example.springboot.util.SQlReplaceUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Test
    public void productSql() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = "2019-11-01 00:00:00";
        Date parse = simpleDateFormat.parse(s);
        Calendar instance = Calendar.getInstance();
        instance.setTime(parse);

        String tempYearMonth = "";

        while (true) {
            String format = simpleDateFormat.format(instance.getTime());
            String yearMonth = format.substring(0, 10);
            String tableName = format.replace("-", "").substring(0, 6);
            if (!tableName.equals(tempYearMonth)) {
                System.out.println("-- " + (instance.get(Calendar.MONTH) + 1) + "月份");
                System.out.println();
            }

            tempYearMonth = format.replace("-", "").substring(0, 6);

            //数据回插
//            String sql = "INSERT INTO t_sr_mq_info_new SELECT * FROM t_sr_mq_info WHERE sd_time >= '" + yearMonth + " 00:00:00' AND sd_time <= '" + yearMonth + " 23:59:59' AND dsps_sts_cd IN('1', '3', '4', '2') AND id > 0;";

//            String sql = "DELETE FROM t_sr_mq_info_h_" + tableName + " WHERE sd_time >= '" + yearMonth + " 00:00:00' AND sd_time <= '" + yearMonth + " 23:59:59' AND dsps_sts_cd IN('1', '3', '4', '2') AND id > 0;";
            String sql = "INSERT INTO t_sr_mq_info_h_" + tableName + " SELECT * FROM t_sr_mq_info WHERE sd_time >= '" + yearMonth + " 00:00:00' AND sd_time <= '" + yearMonth + " 23:59:59" + "' AND dsps_sts_cd IN('1', '3', '4', '2');";


            //回滚
//            String sql = "INSERT INTO t_sr_mq_info SELECT * FROM zxdba_bak.zxdba_20191114_t_sr_mq_info_zp_gx WHERE sd_time >= '" + yearMonth + " 00:00:00' AND sd_time <= '" + yearMonth + " 23:59:59' AND dsps_sts_cd IN('1', '3', '4', '2');";
            //备份
//            String sql = "INSERT INTO zxdba_bak.zxdba_20191114_t_sr_mq_info_zp_gx SELECT * FROM t_sr_mq_info WHERE sd_time >= '" + yearMonth + " 00:00:00' AND sd_time <= '" + yearMonth + " 23:59:59' AND dsps_sts_cd IN('1', '3', '4', '2');";
//            String sql = "DELETE FROM t_sr_mq_info WHERE sd_time >= '" + yearMonth + " 00:00:00' AND sd_time <= '" + yearMonth + " 23:59:59' AND dsps_sts_cd IN('1', '3', '4', '2') AND id > 0;";

            instance.add(Calendar.DAY_OF_MONTH, 1);

            System.out.println(sql);
            System.out.println();

            if (instance.get(Calendar.MONTH) == 11) {
                break;
            }
        }
    }

    @Test
    public void testCalendar() {
        Calendar instance = Calendar.getInstance();
        Date date = new Date();
        instance.setTime(date);
        instance.add(Calendar.MONTH, 2);
        int i = instance.get(Calendar.MONTH);
        System.out.println(i);
    }

    @Test
    public void testFor() {
        for (int i = 0; i < 100; i++) {
            try {
                if (i == 50) {
                    throw new Exception("ceshi");
                }
            } catch (Exception e) {
                continue;
            }
            System.out.println("第" + i + "次循环");
        }
    }

    //设置APPID/AK/SK
    public static final String APP_ID = "17799609";
    public static final String API_KEY = "WpE4wbXY7Hvko8SsWezevHG9";
    public static final String SECRET_KEY = "ZetzKeuneFMmviIwRNqpPrfDiEusRjNR";

    @Test
    public void testImage() throws JSONException {
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

        // 调用接口
        String path = "/Users/smile_mylife/Desktop/test.png";
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        System.out.println(res.toString(2));

    }

    @Test
    public void testTxt() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/smile_mylife/Desktop/测试.txt")));

        String str;
        StringBuilder sb = new StringBuilder();
        while ((str = bufferedReader.readLine()) != null) {
            sb.append("'").append(str).append("'").append(",");
        }

        System.out.println(sb.toString());
        bufferedReader.close();
    }


    @Test
    public void testSql() throws IOException {
        String sql = "INSERT INTO `t_sr_cfg_code` (`TENANT_ID`, `CODE_ID`, `CODE_TYPE_CD`, `CODE_NM`, `CODE_FULL_NM`, `ARGE_SEQNO`, `LEAF_NODE_FLAG`, `VALID_FLAG`, `RMK`, `ORG_BRNCH_ID`, `OP_STAFF_ID`, `CRT_TIME`, `MODF_TIME`, `BIZ_CODE`, `SUPR_BIZ_CODE`, `CODE_TYPE_NM`, `cmos_modify_time`) VALUES ('100000', '%s', '%s', '北京老系统工单修复', '北京老系统工单修复', '1', '1', '1', '北京老系统工单修复', '001016', 'YX1000', now(), now(), '%s', '0', '北京老系统工单修复', now());";

        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/smile_mylife/Desktop/测试.txt")));
        BufferedReader bufferedReader2 = new BufferedReader(new FileReader(new File("/Users/smile_mylife/Desktop/测试1.txt")));

        String delSql = "DELETE FROM t_sr_cfg_code where code_id = '%s';";

        BigInteger bigInteger = new BigInteger("1911271150330146001");
        String str;
        String str2;
        while ((str = bufferedReader.readLine()) != null) {
            /*str2 = bufferedReader2.readLine();
            String format = String.format(sql, bigInteger.toString(), str, str2);*/
            String format = String.format(delSql, bigInteger.toString());

            System.out.println(format);
            System.out.println();
            bigInteger = bigInteger.add(new BigInteger("1"));
        }

        bufferedReader.close();
        bufferedReader2.close();
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
