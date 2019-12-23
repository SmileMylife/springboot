package com.example.springboot.dao;

import com.example.springboot.common.bean.TIntfWorkerOrderMessage;

public interface TIntfWorkerOrderMessageDao {
    int deleteByPrimaryKey(Long recId);

    int insert(TIntfWorkerOrderMessage record);

    int insertSelective(TIntfWorkerOrderMessage record);

    TIntfWorkerOrderMessage selectByPrimaryKey(Long recId);

    int updateByPrimaryKeySelective(TIntfWorkerOrderMessage record);

    int updateByPrimaryKey(TIntfWorkerOrderMessage record);
}