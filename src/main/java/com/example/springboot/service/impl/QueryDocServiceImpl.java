package com.example.springboot.service.impl;

import com.example.springboot.common.bean.InputObject;
import com.example.springboot.common.bean.OutputObject;
import com.example.springboot.dao.IQueryDocDao;
import com.example.springboot.service.IQueryDocService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by ZhangPei on 2019/11/27.
 */
public class QueryDocServiceImpl implements IQueryDocService {
    @Autowired
    private IQueryDocDao iQueryDocDao;

    @Override
    public void queryDoc(InputObject inputObject, OutputObject outputObject) {
        List<Map<String, Object>> beans = iQueryDocDao.queryDoc(inputObject.getParams());
        outputObject.setBeans(beans);
    }
}
