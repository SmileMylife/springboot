package com.example.springboot.service.impl;

import com.example.springboot.dao.ITestSpringBootDao;
import com.example.springboot.service.ITestSpringBootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
