package com.example.springboot.dao;

import com.example.springboot.common.bean.TSrMessageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TSrMessageInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TSrMessageInfo record);

    int insertSelective(TSrMessageInfo record);

    TSrMessageInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TSrMessageInfo record);

    int updateByPrimaryKey(TSrMessageInfo record);

    int insertList(List<TSrMessageInfo> list);

    List<Map<String, Object>> selectAll(Map<String, Object> params);

    int selectCount(Map<String, Object> params);
}