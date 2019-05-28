package com.example.springboot.service.impl;

import com.example.springboot.bean.InputObject;
import com.example.springboot.bean.OutputObject;
import com.example.springboot.dao.ITestSpringBootDao;
import com.example.springboot.service.ISqlProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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
}
