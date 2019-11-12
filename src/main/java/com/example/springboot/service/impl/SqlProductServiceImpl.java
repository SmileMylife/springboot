package com.example.springboot.service.impl;

import com.example.springboot.common.bean.InputObject;
import com.example.springboot.common.bean.OutputObject;
import com.example.springboot.dao.ITestSpringBootDao;
import com.example.springboot.service.ISqlProductService;
import com.example.springboot.util.Constants;
import com.example.springboot.util.FileUtil;
import com.example.springboot.util.ZipUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhangPei on 2019/5/28.
 */
@Service
public class SqlProductServiceImpl implements ISqlProductService {
    @Autowired
    private ITestSpringBootDao iTestSpringBootDao;

    /**
     * 查询省份下拉值
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    public void queryAllProv(InputObject inputObject, OutputObject outputObject) throws Exception {
        List<HashMap<String, Object>> maps = iTestSpringBootDao.selectDbInfos(inputObject.getParams());
        outputObject.setBeans(maps);
    }

    @Override
    public void productSqlFile(InputObject inputObject, OutputObject outputObject) throws Exception {

        HashMap<String, Object> params = inputObject.getParams();
        String provNm = MapUtils.getString(params, "provNm");
        String connUsername = MapUtils.getString(params, "connUsername");
        String connPhone = MapUtils.getString(params, "connPhone");
        String time = MapUtils.getString(params, "time");
        time = time.replace("-", "");
        String jira = MapUtils.getString(params, "jira");
        String operation = MapUtils.getString(params, "operation");
        String username = MapUtils.getString(params, "username");      //英文名称
        String sql = MapUtils.getString(params, "sql");
        String opCount = MapUtils.getString(params, "opCount");
        String isRollback = MapUtils.getString(params, "isRollback");
        String rollbackSql = MapUtils.getString(params, "rollbackSql");
        String isBackup = MapUtils.getString(params, "isBackup");
        String provNmMulti = MapUtils.getString(params, "provNmMulti");

        if (Constants.YES.equals(isBackup) && Constants.YES.equals(isRollback)) {
            throw new Exception("update脚本不能同时既为备份脚本又为回滚脚本！");
        }

        if (StringUtils.isBlank(provNmMulti)
                || StringUtils.isBlank(connPhone)
                || StringUtils.isBlank(connUsername)
                || StringUtils.isBlank(opCount)
                || StringUtils.isBlank(time)
                || StringUtils.isBlank(jira)
                || StringUtils.isBlank(operation)) {
            throw new Exception("参数校验失败！");
        }

        /*//校验sql和操作类型是否一致
        if (!sql.contains(operation.toLowerCase()) && !sql.contains(operation)) {
            throw new Exception("脚本内容和操作类型不一致！");
        }*/

        //查询数据库数据，生成文件
        String[] split = provNmMulti.split(",");
        Map<String, Object> map = new HashMap();
        List<HashMap<String, Object>> results = new ArrayList<>();

        if (!"全省".equals(provNm)) {
            for (int i = 0; i < split.length; i++) {
                if ("全省".equals(split[i])) {
                    continue;
                }
                map.put("provNm", MapUtils.getString(params, "provNm"));
                List<HashMap<String, Object>> provResult = iTestSpringBootDao.selectDbInfos(map);//查询数据库信息
                results.addAll(provResult);
            }
        }

        results = iTestSpringBootDao.selectDbInfos(map);  //查询数据库信息

        //创建临时文件夹
        String dirPath = this.getClass().getResource("/sqlfiles").getPath() + "/" + connUsername + System.currentTimeMillis();
        File fileDir = new File(dirPath);
        fileDir.mkdir();

        //将脚本内容输入至相应文件夹
        for (int i = 0; i < results.size(); i++) {
            HashMap<String, Object> result = results.get(i);
            if ("ngwf接口机".equals(MapUtils.getString(result, "provNm")) && "全省".equals(provNm)) {
                continue;
            }

            //脚本文件名
            String sqlFileName = Constants.YES.equals(isRollback) ? Constants.SQL_ROLLBACK_TEMPLATE : Constants.YES.equals(isBackup) ? Constants.SQL_BACKUP_TEMPLATE : Constants.SQL_TEMPLATE;

            String fileName = String.format(sqlFileName, result.get("user"), time, jira, username,
                    operation);
            //回滚脚本文件名
            String rollbackFileName = String.format(Constants.SQL_ROLLBACK_TEMPLATE, result.get("user"), time, jira, username,
                    operation);

            String content = String.format(Constants.SQL_FILE_CONTENT, result.get("ip"), result.get("port"),
                    result.get("dbNm"), result.get("user"), MapUtils.getString(params, "opCount"),
                    MapUtils.getString(params, "connUsername"), MapUtils.getString(params, "connPhone"));
            String resultSql = sql;

            //正式sql语句
            if (StringUtils.isNotBlank(sql) && sql.indexOf("%s") != -1) {
                resultSql = resultSql.replace("%s", "'" + result.get("provCode") + "'");
            }

            /*//回滚sql
            if (StringUtils.isNotBlank(isRollback)) {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(dirPath + "/" + rollbackFileName)));
                bufferedWriter.write(content + rollbackSql);
                bufferedWriter.close();
            }*/

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(dirPath + "/" + fileName)));
            bufferedWriter.write(content + resultSql);
            bufferedWriter.close();
        }

        //读取生成文件，压缩后回传给用户
        ZipUtil.compress(dirPath, dirPath + ".zip");

        //压缩文件生成后删除原来文件夹
        FileUtil.delDirectory(new File(dirPath));

        outputObject.setObject(new FileInputStream(new File(dirPath + ".zip")));
        outputObject.getBean().put("fileName", dirPath.substring(dirPath.lastIndexOf(File.separator) + 1));

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

        /*for (int i = 0; i < results.size(); i++) {

            Map<String, Object> result = results.get(i);

            String sql1 = "INSERT INTO t_sr_cfg_code(TENANT_ID, PROV_CODE, CODE_ID, CODE_TYPE_CD, CODE_NM, CODE_FULL_NM, ARGE_SEQNO, LEAF_NODE_FLAG, VALID_FLAG, RMK, ORG_BRNCH_ID, OP_STAFF_ID, CRT_TIME, MODF_TIME, BIZ_CODE, SUPR_BIZ_CODE, CODE_TYPE_NM, cmos_modify_time) VALUES('100000', '" + MapUtils.getString(result, "provCode") + "', '1905271703330146001', 'COMMON_CODE@CUSTOMER_IS_APPROVE', '是', '是', '1', '1', '1', '是', '001016', 'YX1000', now(), now(), '1', '0', '是', now());\n\n";

            String sql2 = "INSERT INTO t_sr_cfg_code(TENANT_ID, PROV_CODE, CODE_ID, CODE_TYPE_CD, CODE_NM, CODE_FULL_NM, ARGE_SEQNO, LEAF_NODE_FLAG, VALID_FLAG, RMK, ORG_BRNCH_ID, OP_STAFF_ID, CRT_TIME, MODF_TIME, BIZ_CODE, SUPR_BIZ_CODE, CODE_TYPE_NM, cmos_modify_time) VALUES('100000', '" + MapUtils.getString(result, "provCode") + "', '1905271703330146002', 'COMMON_CODE@CUSTOMER_IS_APPROVE', '否', '否', '1', '1', '1', '否', '001016', 'YX1000', now(), now(), '0', '0', '否', now());\n\n";

            String sql3 = "INSERT INTO t_sr_cfg_code(TENANT_ID, PROV_CODE, CODE_ID, CODE_TYPE_CD, CODE_NM, CODE_FULL_NM, ARGE_SEQNO, LEAF_NODE_FLAG, VALID_FLAG, RMK, ORG_BRNCH_ID, OP_STAFF_ID, CRT_TIME, MODF_TIME, BIZ_CODE, SUPR_BIZ_CODE, CODE_TYPE_NM, cmos_modify_time) VALUES('100000', '" + MapUtils.getString(result, "provCode") + "', '1905271703330146003', 'COMMON_CODE@CUSTOMER_IS_APPROVE', '未知', '未知', '1', '1', '1', '未知', '001016', 'YX1000', now(), now(), '2', '0', '未知', now());\n\n";


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
        }*/
    }

    @Override
    public void showDownloadList(InputObject inputObject, OutputObject outputObject) throws Exception {

    }

}
