package com.example.springboot.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by ZhangPei on 2019/11/27.
 */
public interface IQueryDocDao {
    public List<Map<String, Object>> queryDoc(Map<String, Object> params);

    public void uploadDoc(Map<String, Object> params);

    public int queryDocCount(Map<String, Object> params);
}
