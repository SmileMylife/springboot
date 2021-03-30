package com.example.springboot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baidu.aip.ocr.AipOcr;
import com.example.springboot.common.bean.InputObject;
import com.example.springboot.common.bean.OutputObject;
import com.example.springboot.util.SQlReplaceUtil;
import com.example.springboot.util.SpringUtil;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import fr.opensagres.xdocreport.document.preprocessor.sax.BufferedElement;
import lombok.SneakyThrows;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public void testIntegerChaiXiang() {
        //测试自动拆箱装箱
        Integer integer1 = 128;
        Integer integer2 = 128;

        System.out.println(integer1 == integer2);
        float a = 1e-6f;

        List<String> strings = Arrays.asList(new String[]{"1"});
        strings.add("ceshi");
    }

    @Test
    public void javaEnum() {
        double ceil = Math.ceil(11.2);
        System.out.println((int)ceil);
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
    public void testSwitch() {
        InputObject inputObject = new InputObject();
        int i = 0;
        switch (i) {
            case 1:
                Map<String, Object> params = inputObject.getParams();
                System.out.println("张佩");
            break;
            case 0:
                HashMap<Object, Object> params1 = new HashMap<>();
                params1.put("username", "zhangpei");
                String username = MapUtils.getString(params1, "username");
                System.out.println(username);
            break;
            default:
                break;
        }
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
    public void testObjectRequireNonNull() throws NoSuchFieldException {
        String s = "zhangpei";
        InputObject inputObject = new InputObject();
        Class<? extends String> aClass = s.getClass();
        Field provCode = aClass.getField("provCode");
        String s1 = provCode.toString();

    }

    @Test
    public void testCpu() {
        while (true) {
            System.out.println("测试cput系统");
        }
    }

    @Test
    public void testSub() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("ceshi", "false");
        boolean ceshi = MapUtils.getBooleanValue(map, "ceshi");

        System.out.println(ceshi);

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

        System.out.println(0 % 1000);
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
        double a = 37.2 % 10;
        System.out.println(a);

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

    @Test
    public void testCode() {
        String s = null;
        System.out.println(Integer.valueOf(s));
    }

    @Test
    public void testTryCatch() {
        try {
            throw new SQLException();
        } catch (Exception e) {
            System.out.println("出现了异常！");
        } finally {
            try {
                throw new SQLException();
            } catch (Exception e) {
                System.out.println("再次出现了异常");
            } finally {
                System.out.println("最终会不会执行");
            }
        }
    }

    @Test
    public void testMap() {
        int []ints  = new int[10];
        System.out.println(ints[2]);

        HashMap<Object, Object> map = new HashMap<>();
        try {
            int i = 10 / 0;
        } catch (Exception e) {

        } finally {
            /*if (MapUtils.getInteger(map, "username") == 0) {
                System.out.println("测试");
            }*/
        }
    }

    @Test
    public void testLong() {
        int a = 13 / 2;
        System.out.println(a);
        double time = new Date().getTime();
        double time1 = 1578303205843l;

        System.out.println(time / time1 > 1.0);
        double b = 10;
        System.out.println(b);

        File file = new File("/User/smile_mylife/Desktop/虚拟组织机构ID.xlsx");
    }

    @Test
    public void testWhat() {

    }

    @Test
    public void testSubString() throws InterruptedException, ParseException {
        /*String s = "[0871]";
        String substring = s.substring(1, s.length() - 1);
        System.out.println(substring);

        double time = (double)new Date().getTime();
        Thread.sleep(1111);
        double time1 = (double)new Date().getTime();
        System.out.println(String.valueOf(time1 - time / time));

        BigDecimal b1 = new BigDecimal(Double.toString(10.3));
        BigDecimal b2 = new BigDecimal(Double.toString(2.1));

        double v = b1.divide(b2, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(v);*/

        /*double b = 10.0;
        double a = 3.0;

        System.out.println(b / a);

        long time = new Date().getTime();
        Thread.sleep(5000);
        long time1 = new Date().getTime();

        System.out.println((time1 - time) / (24 * 3600 * 1000));*/

        /*long a = 10;
        double v = a + 7 / 24.0;

        System.out.println(v);*/

        long time = new Date().getTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = simpleDateFormat.parse("2020-01-08 00:00:00");


        long days = (time - parse.getTime()) / (24 * 3600 * 1000);

        System.out.println(days);

        long hour = ((time - parse.getTime()) % (24 * 3600 * 1000)) / (3600 * 1000);

        System.out.println(hour);


        String s = String.valueOf(days + hour / 24.0);
        double v = Double.parseDouble(s);
        System.out.println(v);


    }

    @Test
    public void testRetainAll() {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add("1");
        objects.add("2");
        objects.add("3");

        ArrayList<Object> objects1 = new ArrayList<>();
        objects1.add("1");
        objects1.add("2");

        boolean b = objects.retainAll(objects1);

        System.out.println(objects.size());

        System.out.println("2019-10-10 00:00:00".substring(0, 10).replace("-", ""));

    }

    @Test
    public void testControl() {
        boolean contains = Arrays.asList("".split(",")).contains("-69");

        System.out.println(contains);
    }

    @Test
    public void testBool() {
        Map<String, Object> map = new HashMap<>();
        map.put("isFlag", false);
        System.out.println(MapUtils.getBoolean(map, "isFlag"));
    }

    @Test
    public void testPoint() {

    }

    /**
     * 获取指定年月的第一天
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth1(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最小天数
        int firstDay = cal.getMinimum(Calendar.DATE);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH,firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    @Test
    public void testDate() {
        Date date = new Date();
        Date date2 = new Date(date.getTime());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);

        String dateStr2 = simpleDateFormat.format(date2);

        System.out.println(dateStr);
        System.out.println(dateStr2);

        Date dateTime = new Date();
        long time = dateTime.getTime();
        String timeStr = String.valueOf(time);
        Date date1 = new Date(Long.parseLong(timeStr));
        System.out.println(date1);
    }

    @Test
    public void testList() {
        String s = "";
        String s1 = "1;2;3";

        if (StringUtils.isNotBlank(s1)) {
            s += ";" + s1;
        }

        System.out.println(s);
    }

    @Test
    public void testSubStr() {
        String substring = StringUtils.substring("", 0, 2000);
        System.out.println(substring);
    }

    @Test
    public void closeInputStream() throws FileNotFoundException {

        FileInputStream fileInputStream = new FileInputStream(new File("/Users/smile_mylife/Desktop/报文.json"));
        FileOutputStream fileOutputStream = new FileOutputStream(new File("/Users/smile_mylife/Desktop/heihei.json"));
        try {
            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = fileInputStream.read()) != -1) {
                fileOutputStream.write(length);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fileInputStream.close();
                fileOutputStream.close();
            } catch (Exception e) {
                throw new RuntimeException("流关闭失败！");
            }
        }
    }

    @Test
    public void testTryWithResource() {
        try(FileInputStream fileInputStream = new FileInputStream(new File("/Users/smile_mylife/Desktop/报文.json"));
            FileOutputStream fileOutputStream = new FileOutputStream(new File("/Users/smile_mylife/Desktop/heihei.json"));) {
            int length = 0;
            while ((length =fileInputStream.read()) != -1) {
                fileOutputStream.write(length);
            }
        } catch (Exception e) {

        }
    }

    @Test
    public void testRemove() {
        ArrayList<Object> objects = new ArrayList<>();
        objects.removeAll(new ArrayList<>());
    }

    @Test
    public void testJsonStr() {
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("username", "zhangpei");
        list.add(map);

        Map<String, Object> params = new HashMap<>();
        params.put("file", JSON.toJSONString(list));

        Map<String, Object> contentMap = new HashMap<>();
        String s = JSON.toJSONString(params);
        contentMap.put("params", s);
        System.out.print(JSON.toJSONString(contentMap));

        Map map1 = JSON.parseObject(JSON.toJSONString(contentMap), Map.class);

        String paramsStr = MapUtils.getString(map1, "params");

        Map map2 = JSON.parseObject(paramsStr, Map.class);

        String file = MapUtils.getString(map2, "file");

        JSONArray jsonArray = JSON.parseArray(file);

        System.out.print(jsonArray);

    }

    @Test
    public void testTIimeConsumer() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 10000; j++) {
                ArrayList<Object> objects = new ArrayList<>();
                objects.add("zhangpei");
                objects.add("zhangpei");
                objects.add("zhangpei");
                objects.add("zhangpei");
                objects.add("zhangpei");
            }
        }
        long end = System.currentTimeMillis();

        System.out.print(end - start);
    }

    @Test
    public void testSubList() {
        int start = 0;
        int limit = 4;

        ArrayList<Object> objects = new ArrayList<>();
        objects.add("123");
        objects.add("456");
        objects.add("789");
        objects.add("101");
        List<Object> objects1 = null;
        List<Object> objects2 = objects.subList(3, 4);

        System.out.println(objects2);
        if (start >= 0 && start < objects.size() && limit > 0 && objects.size() > 0) {
            if (start + limit > objects.size()) {
                objects1 = objects.subList(start, objects.size());
            } else {
                objects1 = objects.subList(start, start + limit);
            }
        } else {
            System.out.print("分页数据有误");
        }

        System.out.println(objects1);
    }

    @Test
    public void testMd5() {
        ArrayList<Object> objects = new ArrayList<>();
        objects.addAll(null);
        boolean empty = CollectionUtils.isNotEmpty(objects);
        System.out.print(empty);
    }

    @Test
    public void testIndex() {
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("456");
        list.add("789");
        list.add("012");
        list.add("尼玛");

        List<String> temp = new ArrayList();
        for (int i = 1; i <= list.size(); i++) {
            temp.add(list.get(i - 1));
            if (i % 2 == 0) {
                System.out.println("这是一组数据" + temp);
                temp = new ArrayList();
            }
        }
        if (CollectionUtils.isNotEmpty(temp)) {
            System.out.println("剩余的集合：" + temp);
        }

        String str = "4";
        double v = Double.parseDouble(str);
        System.out.println(v);

    }

    @Test
    public void testMid() {
        JSONArray jsonArray = new JSONArray();
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        jsonObject.put("username", "zhangpei");
        jsonArray.add(jsonObject);
        String s = jsonArray.toString();

        System.out.print(s);
    }

    @Test
    public void testJsonSt() {
        HashMap<Object, Object> map = new HashMap<>();
        String[] arr = {"1", "2", "3"};
        map.put("jsonArr", arr);

        String json = JSON.toJSONString(map);
        System.out.println(json);

        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(json);
        Object jsonArr = jsonObject.get("jsonArr");
        if (jsonArr instanceof JSONArray) {
            System.out.println(jsonArr);
        }

        String[] strs = {"1", "2", "3"};

        System.out.println(JSON.toJSONString(strs));

    }
    @Test
    public void testDateStr() {
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH) + 1;
        if (month - 1 <= 0) {
            year = year - 1;
            month = 12;
        } else {
            month = month - 1;
        }

        String monthStr = String.valueOf(month).length() == 1 ? "0" + String.valueOf(month) : String.valueOf(month);

        System.out.println(year + "-" + monthStr + "-01 " + "00:00:00");
    }

    @Test
    public void testInteger() {
        Integer integer = new Integer(777777);

        int i = 777777;

        boolean equals = integer.equals(i);

        byte[] arr = new byte[2];
        Base64.Encoder encoder = Base64.getEncoder();
        String s = new String(encoder.encode(arr));
        System.out.println(s);


        System.out.println(Arrays.toString(arr));

        System.out.print(i == integer);
        System.out.print(integer.equals(i));
    }
    @Test
    public void testMapInt() {
        MapUtils.getIntValue(new HashMap(), "fctVal");
    }

    @Test
    public void testEquals() {
        //equals方法默认比较两个对象的地址值，源码为 this == obj，string和integer重写了equals方法，所以可以用来比较两个值。
        Car car = new Car("red", "laosiji");
        Car car1 = new Car("red", "laosiji");
        Car car2 = new Car("red", "hehe");

        System.out.print(car1.equals(car2));
        System.out.print(car.equals(car1));

        String s1 = new String();
        String s2 = new String();

        System.out.println(s1.equals(s2));


        String a = "通话";
        String b = "重地";
        System.out.println(String.format("str1: %d | str2: %d", a.hashCode(), b.hashCode()));

        long round = Math.round(-1.5);
        System.out.println(round);

        HashSet<Object> objects = new HashSet<>();
        HashSet<Object> objects1 = new HashSet<>();

        objects.equals(objects1);

    }
    @Test
    public void testJsonParse() {
        HashMap<Object, Object> map = new HashMap<>();
        HashMap<Object, Object> map1 = new HashMap<>();
        map1.put("username", "password");
        map.put("map", map1);
        System.out.println(JSON.toJSONString(map));

        String str = "2020-08-09 00:00:00.0";
        String substring = str.substring(0, str.indexOf(".0"));
        System.out.println(substring);
    }

    @Test
    public void testCompareParse() {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("nodeId", "03");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("nodeId", "02");
        Map<String, Object> map3 = new HashMap<>();
        map3.put("nodeId", "04");
        Map<String, Object> map4 = new HashMap<>();
        map4.put("nodeId", "01");

        ArrayList<Map<String, Object>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);

        System.out.println(Integer.parseInt("02"));

        Collections.sort(list, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> map1, Map<String, Object> map2) {
                return Integer.parseInt(MapUtils.getString(map1, "nodeId")) - Integer.parseInt(MapUtils.getString(map2, "nodeId"));
            }
        });

        Collections.reverse(list);
        System.out.println(list);
    }

    @Test
    public  void testSort() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("student1", 60);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("student2", 70);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("student3", 40);
        list.add(map1);
        list.add(map2);
        list.add(map3);
        Collections.sort(list, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> map1, Map<String, Object> map2) {
                int value0 = (int)map1.values().toArray()[0];
                int value1 = (int)map2.values().toArray()[0];
                return value1 - value0;
            }
        });

        System.out.println(list);
    }

    @Test
    public void testCompare() {

        System.out.println(false || true && false || true);

        String s1 = "2019-08-07 00:00:00";
        String s2 = "2019-09-09 00:00:00";
        int i = s1.compareTo(s2);

        System.out.println(i);

        HashMap<Object, Object> map = new HashMap<>();
//        ArrayList<Map<String, Object>> objects = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();

        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("password", "hehehe");
        jsonArray.add(map1);
        map.put("children", jsonArray);
        map.put("username", "zhangpei");

        Object obj = map;

        System.out.println(JSON.toJSONString(obj));
    }

    @Test
    public void testThread() {
        ArrayList<String> objects = new ArrayList<>();
        objects.add("1");
        objects.add("2");
        objects.add("3");
        List<String> strings = objects.subList(0, 3);
        System.out.println(strings);
    }

    @Test
    public void testParseDouble() {
        /*String[] s = "".split(",");
        System.out.println(Arrays.toString(s));

        ArrayList<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(2);
        list.add(3);

        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return t1 - integer;
            }
        });
        System.out.println(list);*/

        /*HashMap<String, Object> map = new HashMap<>();
        map.put("username", null);
        System.out.println(JSON.toJSONString(map));*/

//        String s = "2020-01-24 00:00:00.0";
//        System.out.println(s.substring(0, s.indexOf(".0")));
        String s = "000300300";
        String result = s.length() > 9 ? s.substring(0, 9) : s;

        System.out.println(result);
    }

    @Test
    public void testAddAll() {
        ArrayList<Map<String, Object>> llist1 = new ArrayList<>();
        ArrayList<Map<String, Object>> llist2 = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", "zhangpei");
        llist1.add(map);

        llist2.addAll(llist1);

        map.put("password", "123");
        System.out.println(llist2);
    }

    @Test
    public void testUnicode() throws UnsupportedEncodingException {
        Object obj = new String[]{"1", "2"};
        System.out.println(obj instanceof String[]);
    }

    @Test
    public void testDateStrTrans() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date parse = simpleDateFormat.parse("20200811131212");
        Map<String, Object> map = new HashMap<>();
        Boolean ceshi = MapUtils.getBoolean(map, "ceshi");
        if (ceshi) {
            System.out.println("123");
        }

        System.out.println(parse);
    }

    @Test
    public void testIterator() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("尼玛");
        }

        Iterator<String> iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (i == 5) {
                iterator.remove();
            }
            i++;
        }
    }

    @Test
    public void testHashMap() {
        HttpServlet httpServlet = new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                super.doGet(req, resp);
            }
        };
        HashMap<Object, Object> map = new HashMap<>();
        map.put(null, "test");
        map.put(null, "test2");
        System.out.println(map);
    }

    @Test
    public void testIteratorList() {
        List<String> lists = new ArrayList<>();
        lists.add("123");
        Iterator<String> iterator = lists.iterator();

        while (iterator.hasNext()) {
//            iterator.next();
            System.out.println("循环");
        }
    }

    @Test
    public void testSubStrTest() {
        String s = "zhangpei";
        String s1 = s.length() > 8 ? s.substring(0, 8) : s;
        System.out.println(s1);
    }

    @Test
    public void testSetStr() {
        String s = "ab";
        String substring = s.substring(2);

        System.out.println(substring);
    }

    @Test
    public void testFileSql() throws IOException {
        FileReader fileReaderWrkfmId = new FileReader(new File("/Users/smile_mylife/Desktop/工单主键.txt"));
        BufferedReader bufferedReaderWrkfmId = new BufferedReader(fileReaderWrkfmId);
        List<String> wrkfmIds = new ArrayList<>();
        String str;
        while ((str = bufferedReaderWrkfmId.readLine()) != null) {
            wrkfmIds.add(str);
        }
        bufferedReaderWrkfmId.close();

        FileReader fileReaderCsvc = new FileReader(new File("/Users/smile_mylife/Desktop/全网流水.txt"));
        BufferedReader bufferedReaderCsvc = new BufferedReader(fileReaderCsvc);
        List<String> csvcs = new ArrayList<>();
        String csvc;
        while ((csvc = bufferedReaderCsvc.readLine()) != null) {
            csvcs.add(csvc);
        }
        bufferedReaderCsvc.close();


        HashMap<String, Object> wrkfmIdMap = new HashMap<>();  //工单主键对应关系
        for (int i = 0; i < wrkfmIds.size(); i++) {
            wrkfmIdMap.put(csvcs.get(i), wrkfmIds.get(i));
        }






        FileReader fileReaderWrkfmId1 = new FileReader(new File("/Users/smile_mylife/Desktop/工单主键.txt"));
        BufferedReader bufferedReaderWrkfmId1 = new BufferedReader(fileReaderWrkfmId1);
        List<String> wrkfmIds1 = new ArrayList<>();
        String str1;
        while ((str1 = bufferedReaderWrkfmId1.readLine()) != null) {
            wrkfmIds1.add(str1);
        }
        bufferedReaderWrkfmId1.close();

        FileReader fileReaderCsvc1 = new FileReader(new File("/Users/smile_mylife/Desktop/全网流水.txt"));
        BufferedReader bufferedReaderCsvc1 = new BufferedReader(fileReaderCsvc1);
        List<String> csvcs1 = new ArrayList<>();
        String csvc1;
        while ((csvc1 = bufferedReaderCsvc1.readLine()) != null) {
            csvcs1.add(csvc1);
        }
        bufferedReaderCsvc1.close();


        HashMap<String, Object> wrkfmIdMap1 = new HashMap<>();  //工单主键对应关系
        for (int i = 0; i < wrkfmIds1.size(); i++) {
            wrkfmIdMap1.put(csvcs1.get(i), wrkfmIds1.get(i));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <wrkfmIds.size(); i++) {
            String sql = "update t_sr_problem_proces set biz_cntt = '' where wrkfm_id = '%s';";
            sql = String.format(sql, wrkfmIds.get(i));
            System.out.println();
            System.out.println(sql);
            sb.append("'").append(wrkfmIds.get(i)).append("'").append(",");
        }

        System.out.println(sb.toString());

        System.out.println("工单总量" + wrkfmIds.size());
    }

    @Test
    public void testStrInterview() {
        String s = "abc";
        String abc = new String("abc");
        System.out.println(s == abc);
    }

    @Test
    public void testCao() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("nodeId", "start");

        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("nodeId", "review");

        ArrayList<Map<String, Object>> objects = new ArrayList<>();
        objects.add(map2);
        objects.add(map);

        ArrayList<Map<String, Object>> returnList = new ArrayList<>();

        returnList = objects;

        for (Map<String, Object> temp :objects) {
            if ("start".equals(temp.get("nodeId"))) {
                returnList.set(0, map);
            } else if ("review".equals(temp.get("nodeId"))) {
                returnList.set(1, map);
            }
        }

        Map<String, Object> stringObjectMap = objects.get(objects.size() - 1);

        System.out.println(returnList);

    }

    @Test
    public void testSpeed() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String format = String.format("%s%s", "测试", "ceshi");
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        long startCopy = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append("测试").append("ceshi");
        }
        String s = sb.toString();
        long endCopy = System.currentTimeMillis();
        System.out.println(endCopy - startCopy);
    }

    @Test
    public void testCompate() {
        String crtTime = "2020-10-21 00:00:00";
        String crtTime2 = "2020-10-21 00:00:00";

        System.out.println(crtTime.compareTo(crtTime2));
    }

    @Test
    public void testCompareTo() {
        String s = "2020-12-10 00:00:00";
        String s1 = "2020-12-10 00:00:01";
        int i = s1.compareTo(s);
        System.out.println(i);
        Map<String, Object> map = new HashMap<>();
        map.put("ceshi", "18:00:00");
        Integer ceshi = MapUtils.getInteger(map, "ceshi");
        System.out.println(ceshi);

        List<List> lists = JSON.parseObject("['review']", List.class);
        System.out.println(lists);


    }


    @Test
    public void testLn() throws UnsupportedEncodingException {
        String s = "#鍚屾\uE11E瑕佺礌#鍙楃悊鍙风爜:13840214121涓氬姟鍚嶇О:瀹㈡埛鏇惧弽鏄犳笭閬�:10086闂\uE1C0\uE57D鎻忚堪:瀹㈡埛13840214121鍙嶆槧锛氭湰鏈哄彿鐮佸湪8鏈�31鏃ヤ笂鍗堟煡璇㈡瑺璐�690澶氬厓锛屽湪涓嬪崍瀹㈡埛鍏呭�间簡700鍏冿紝浣嗗\uE179鎴锋湁婊炵撼閲戯紝鍦�9鏈�1鏃ヨ\uE766閿�鎴凤紝鍛婄煡浠嶉渶瑕佷氦绾�100澶氬厓璐圭敤瀹㈡埛涓嶆弧锛屾姇璇夊悗鏈\uE044緱鍒拌В鍐筹紝璇锋牳瀹炲\uE629鐞嗐�傚\uE179鎴疯\uE6E6姹�:瑕佹眰鏍稿疄澶勭悊鑱旂郴鐢佃瘽:13238866027#鍚屾\uE11E瑕佺礌#\n";
        byte[] bytes = s.getBytes("GBK");
//        byte[] bytes = s.getBytes("ISO-8859-1");
        String s1 = new String(bytes, "UTF-8");
        System.out.println(s1);
    }

    @Test
    public void testJdk8() {
        /*List<String> list = new ArrayList<>();
        list.add("zhangpei");
        list.add("zhangxu");
        list.add("zhangxu");
        list.add("zhangxu");
        list.add("zhangxu");
        list.add("zhangxu");

        List<String> zhangxu = list.stream().filter(elem -> elem.equals("zhangxu")).collect(Collectors.toList());
        System.out.println(zhangxu);*/

        HashMap<Object, Object> map = new HashMap<>();
        map.put("test", "null");
        System.out.println(map.toString());
        Object test = map.get("test");

        System.out.println(test == null);
        System.out.println(test.equals("null"));

    }

    @Test
    public void testListIterator() throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("/Users/smile_mylife/Desktop/1012.txt"));
        System.out.println(new String(bytes, StandardCharsets.UTF_8));
    }

    @Test
    public void TestArraylist() {
        String s = new String();
        s.length();
    }

    @Test
    public void testNewNap() {
        boolean equals = "false".equals(true);
        System.out.println(equals);
        System.gc();
    }
    @Test
    public void testParse() {
        StringBuilder sb = new StringBuilder();
        StringBuilder ceshi = sb.append("ceshi");
        System.out.println(sb == ceshi);
    }
    @Test
    public void testPackage() {
        Integer integer1 = -129;
        Integer integer2 = -129;

        System.out.println(integer1 == integer2);

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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Car) {
            Car obj1 = (Car) obj;
            if (this.getColor().equals(obj1.getColor())) {
                return true;
            }
        }
        return false;
    }
}
