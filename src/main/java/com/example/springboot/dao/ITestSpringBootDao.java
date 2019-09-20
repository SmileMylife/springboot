package com.example.springboot.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhangPei on 2018/11/15.
 */
public interface ITestSpringBootDao {
    public List<HashMap<String, Object>> selectUsers();

    public List<HashMap<String, Object>> selectEmployees(Map<String, Object> params);

    public int selectEmplyeesCount(Map<String, Object> params);

    public void insertDbInfo(Map<String, Object> params);

    public List<HashMap<String, Object>> selectDbInfos(Map<String, Object> params);

    public void insertUser(Map<String, Object> params);

    public int inserWorksheet(List<String> wrkfmIds);
}
