package com.example.springboot.dao;

import com.example.springboot.common.bean.TIntfConstConfig;

public interface TIntfConstConfigDao {
    int deleteByPrimaryKey(Long configId);

    int insert(TIntfConstConfig record);

    int insertSelective(TIntfConstConfig record);

    TIntfConstConfig selectByPrimaryKey(Long configId);

    int updateByPrimaryKeySelective(TIntfConstConfig record);

    int updateByPrimaryKey(TIntfConstConfig record);
}