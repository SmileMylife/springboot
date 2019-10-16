package com.example.springboot;

import com.example.springboot.util.SQlReplaceUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.IOException;
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
}
