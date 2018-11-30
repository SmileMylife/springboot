package com.example.springboot.dao;


import java.util.List;
import java.util.Map;

/**
 * Created by ZhangPei on 2018/11/15.
 */
public interface ITestSpringBootDao {
    public List<Map<String, Object>> selectUsers();
}
