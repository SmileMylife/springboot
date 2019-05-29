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
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void testTransaction(InputObject inputObject, OutputObject outputObject) throws Exception {
        inputObject.getParams().put("username", "zhangsan");
        inputObject.getParams().put("password", "456");
        System.out.println("热部署生效了");
        iTestSpringBootDao.insertUser(inputObject.getParams());
    }


}
