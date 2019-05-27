package com.example.springboot.service.impl;

import com.example.springboot.bean.InputObject;
import com.example.springboot.bean.OutputObject;
import com.example.springboot.dao.ITestSpringBootDao;
import com.example.springboot.service.ITestSpringBootService;
import com.example.springboot.util.Constants;
import com.example.springboot.util.ExcelUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhangPei on 2018/11/15.
 */
@Service
public class ITestSpringBootServiceImpl implements ITestSpringBootService {
    @Autowired
    private ITestSpringBootDao iTestSpringBootDao;

    /**
     * 测试springboot使用mybatis
     * @return
     */
    public List<HashMap<String, Object>> testDaoOperation() {
        List<HashMap<String, Object>> list = iTestSpringBootDao.selectUsers();
        return list;
    }

    @Override
    public void testParamsPackage(@com.example.springboot.annotations.InputObject InputObject inputObject, OutputObject outputObject) {
        List<HashMap<String, Object>> maps = iTestSpringBootDao.selectUsers();
        outputObject.setBeans(maps);
    }

    @Override
    public void queryEmployees(InputObject inputObject, OutputObject outputObject) throws Exception {
        inputObject.getParams().put("tableName", "employees");
        List<HashMap<String, Object>> list = iTestSpringBootDao.selectEmployees(inputObject.getParams());
        int i = iTestSpringBootDao.selectEmplyeesCount(inputObject.getParams());

        outputObject.getBean().put("total", i);
        outputObject.setBeans(list);
    }

    @Override
    public void insertDbInfo(InputObject inputObject, OutputObject outputObject) throws Exception {
        String s = "/Users/smile_mylife/Desktop/test.xlsx";
        List<List<String>> lists = ExcelUtil.readXlsx(s);
        for (int i = 0; i < lists.size(); i++) {
            Map<String, Object> map = new HashMap();
            for (int j = 0; j < lists.get(i).size(); j++) {
                if (j == 0) {
                    map.put("ip", lists.get(i).get(j));
                } else if (j == 1) {
                    map.put("port", lists.get(i).get(j));
                } else if (j == 2) {
                    map.put("dbNm", lists.get(i).get(j));
                } else if (j == 3) {
                    map.put("user", lists.get(i).get(j));
                } else if (j == 4) {
                    map.put("provNm", lists.get(i).get(j));
                }
            }
            iTestSpringBootDao.insertDbInfo(map);
        }
    }

    @Override
    public void productSqlFile(InputObject inputObject, OutputObject outputObject) throws Exception {
        
        System.out.println("测试jrebel");
        HashMap<String, Object> params = inputObject.getParams();
        String provNm = MapUtils.getString(params, "provNm");
        String connUsername = MapUtils.getString(params, "connUsername");
        String connPhone = MapUtils.getString(params, "connPhone");
        String time = MapUtils.getString(params, "time");
        String jira = MapUtils.getString(params, "jira");
        String operation = MapUtils.getString(params, "operation");
        String username = MapUtils.getString(params, "username");      //英文名称

        if (StringUtils.isBlank(provNm)
                || StringUtils.isBlank(connPhone)
                || StringUtils.isBlank(connUsername)
                || StringUtils.isBlank(time)
                || StringUtils.isBlank(jira)
                || StringUtils.isBlank(operation)
                || StringUtils.isBlank(username)) {
            throw new Exception("参数校验失败！");
        }

        //查询数据库数据，生成文件
        Map<String, Object> map = new HashMap();
        if (!"全省".equals(provNm)) {
            map.put("provNm", MapUtils.getString(params, "provNm"));
        }
        List<Map<String, Object>> results = iTestSpringBootDao.selectDbInfos(map);  //查询数据库信息


        //*******************从文件中取模板*******************
        /*String resource = this.getClass().getClassLoader().getResource("//").getPath() + "files/template.txt";
        URL resource = this.getClass().getResource("/files/template.txt");
        File templateFile = new File(resource.getPath());   //一定要用getpath获取路径，直接使用tostring前缀会有file

        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(templateFile));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("/Users/smile_mylife/Desktop/test/test.txt"));

        byte[] bytes = new byte[new Long(templateFile.length()).intValue()];

        bufferedInputStream.read(bytes);
        bufferedOutputStream.write(bytes);

        bufferedInputStream.close();
        bufferedOutputStream.close();*/

        for (int i = 0; i < results.size(); i++) {
            
            Map<String, Object> result = results.get(i);

            String sql1 = "INSERT INTO t_sr_cfg_code(TENANT_ID, PROV_CODE, CODE_ID, CODE_TYPE_CD, CODE_NM, CODE_FULL_NM, ARGE_SEQNO, LEAF_NODE_FLAG, VALID_FLAG, RMK, ORG_BRNCH_ID, OP_STAFF_ID, CRT_TIME, MODF_TIME, BIZ_CODE, SUPR_BIZ_CODE, CODE_TYPE_NM, cmos_modify_time) VALUES('100000', '" + MapUtils.getString(result, "provCode") + "', '1905271703330146001', 'COMMON_CODE@CUSTOMER_IS_APPROVE', '是', '是', '1', '1', '1', '是', '001016', 'YX1000', now(), now(), '0', '0', '是', now());\n\n";

            String sql2 = "INSERT INTO t_sr_cfg_code(TENANT_ID, PROV_CODE, CODE_ID, CODE_TYPE_CD, CODE_NM, CODE_FULL_NM, ARGE_SEQNO, LEAF_NODE_FLAG, VALID_FLAG, RMK, ORG_BRNCH_ID, OP_STAFF_ID, CRT_TIME, MODF_TIME, BIZ_CODE, SUPR_BIZ_CODE, CODE_TYPE_NM, cmos_modify_time) VALUES('100000', '" + MapUtils.getString(result, "provCode") + "', '1905271703330146002', 'COMMON_CODE@CUSTOMER_IS_APPROVE', '否', '否', '1', '1', '1', '否', '001016', 'YX1000', now(), now(), '0', '0', '否', now());\n\n";

            String sql3 = "INSERT INTO t_sr_cfg_code(TENANT_ID, PROV_CODE, CODE_ID, CODE_TYPE_CD, CODE_NM, CODE_FULL_NM, ARGE_SEQNO, LEAF_NODE_FLAG, VALID_FLAG, RMK, ORG_BRNCH_ID, OP_STAFF_ID, CRT_TIME, MODF_TIME, BIZ_CODE, SUPR_BIZ_CODE, CODE_TYPE_NM, cmos_modify_time) VALUES('100000', '" + MapUtils.getString(result, "provCode") + "', '1905271703330146003', 'COMMON_CODE@CUSTOMER_IS_APPROVE', '未知', '未知', '1', '1', '1', '未知', '001016', 'YX1000', now(), now(), '0', '0', '未知', now());\n\n";


            String rollback = "DELETE FROM t_sr_cfg_code WHERE CODE_ID = '1905271703330146001';\n\nDELETE FROM " +
                    "t_sr_cfg_code WHERE CODE_ID = '1905271703330146002';\n\nDELETE FROM t_sr_cfg_code WHERE CODE_ID = '1905271703330146003';";

            //sql脚本文件名
            String fileName = String.format(Constants.SQL_TEMPLATE, result.get("user"), time, jira, username,
                    operation);

            //rollback脚本文件名
            String rollBackFileName = String.format(Constants.SQL_ROLLBACK_TEMPLATE, result.get("user"), time, jira, username, operation);

            //脚本内容注释
            String format = String.format(Constants.SQL_FILE_CONTENT, result.get("ip"), result.get("port"), result.get("dbNm"), result.get("user"), MapUtils.getString(params, "connUsername"), MapUtils.getString(params, "connPhone"));

            //回滚脚本
            FileWriter rollBackFileWriter = new FileWriter(new File("/Users/smile_mylife/Desktop/jiaoben/" + rollBackFileName));
            rollBackFileWriter.write(format + rollback);
            rollBackFileWriter.close();

            //脚本
            format += sql1 + sql2 + sql3;
            FileWriter fileWriter = new FileWriter(new File("/Users/smile_mylife/Desktop/jiaoben/" + fileName));
            fileWriter.write(format);
            fileWriter.close();
        }
    }


}
