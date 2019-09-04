package com.example.springboot.service;

import com.example.springboot.common.bean.InputObject;
import com.example.springboot.common.bean.OutputObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ZhangPei on 2018/11/15.
 */
public interface ITestSpringBootService {
    public List<HashMap<String, Object>> testDaoOperation();

    public void testParamsPackage(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void queryEmployees(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void insertDbInfo(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void testTransaction(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void testTheadpoolDi();

}
