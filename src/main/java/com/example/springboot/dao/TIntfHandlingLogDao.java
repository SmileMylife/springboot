package com.example.springboot.dao;

import com.example.springboot.common.bean.TIntfHandlingLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TIntfHandlingLogDao {
    int deleteByPrimaryKey(Long lgId);

    int insert(TIntfHandlingLog record);

    int insertSelective(TIntfHandlingLog record);

    TIntfHandlingLog selectByPrimaryKey(Long lgId);

    int updateByPrimaryKeySelective(TIntfHandlingLog record);

    int updateByPrimaryKey(TIntfHandlingLog record);
}