package com.example.springboot.service;

import com.example.springboot.bean.InputObject;
import com.example.springboot.bean.OutputObject;

/**
 * Created by ZhangPei on 2019/5/28.
 */
public interface ISqlProductService {
    /**
     * 查询省份下拉
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    public void queryAllProv(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void productSqlFile(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void showDownloadList(InputObject inputObject, OutputObject outputObject) throws Exception;
}
