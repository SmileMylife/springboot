package com.example.springboot.service;

import com.example.springboot.common.bean.InputObject;
import com.example.springboot.common.bean.OutputObject;

/**
 * Created by ZhangPei on 2019/11/27.
 */
public interface IQueryDocService {
    public void queryDoc(InputObject inputObject, OutputObject outputObject);
}
