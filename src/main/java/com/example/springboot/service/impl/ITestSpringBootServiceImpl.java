package com.example.springboot.service.impl;

import com.example.springboot.bean.InputObject;
import com.example.springboot.bean.OutputObject;
import com.example.springboot.dao.ITestSpringBootDao;
import com.example.springboot.service.ITestSpringBootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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
        outputObject.setBeans(list);
    }


}
