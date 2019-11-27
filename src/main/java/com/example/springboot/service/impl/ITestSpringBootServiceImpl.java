package com.example.springboot.service.impl;

import com.example.springboot.common.bean.InputObject;
import com.example.springboot.common.bean.OutputObject;
import com.example.springboot.dao.ITestSpringBootDao;
import com.example.springboot.service.ITestSpringBootService;
import com.example.springboot.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public List<Map<String, Object>> testDaoOperation() {
        List<Map<String, Object>> list = iTestSpringBootDao.selectUsers();
        return list;
    }

    @Override
    public void testParamsPackage(@com.example.springboot.common.annotations.InputObject InputObject inputObject, OutputObject outputObject) {
        List<Map<String, Object>> maps = iTestSpringBootDao.selectUsers();
        outputObject.setBeans(maps);
    }

    @Override
    public void queryEmployees(InputObject inputObject, OutputObject outputObject) throws Exception {
        inputObject.getParams().put("tableName", "employees");
        List<Map<String, Object>> list = iTestSpringBootDao.selectEmployees(inputObject.getParams());
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

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Override
    public void testTheadpoolDi() {
        //测试线程池依赖注入
        threadPoolTaskExecutor.execute(new ThreadTest());
    }

    @Override
    public void testDatabase(InputObject inputObject, OutputObject outputObject) {

        long start = System.currentTimeMillis();
        List<String> wrkfmIds = new ArrayList<>();
        for (int i = 1; i < 100000; i++) {
            if (i % 1000 == 0) {
                iTestSpringBootDao.inserWorksheet(wrkfmIds);
                wrkfmIds = new ArrayList<>();
            }
            wrkfmIds.add(String.valueOf(i + 850000));
        }
        long end = System.currentTimeMillis();
        
        
        System.out.println("执行完毕话费时间" + (end - start) / 1000);

    }

    class ThreadTest implements Runnable {

        @Override
        public void run() {
            System.out.println("测试线程");
        }
    }
}
