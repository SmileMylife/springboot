package com.example.springboot.service.impl;

import com.example.springboot.common.bean.InputObject;
import com.example.springboot.common.bean.OutputObject;
import com.example.springboot.dao.IQueryDocDao;
import com.example.springboot.service.IQueryDocService;
import com.example.springboot.util.Constants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by ZhangPei on 2019/11/27.
 */
@Service
@Transactional
public class QueryDocServiceImpl implements IQueryDocService {
    @Autowired
    private IQueryDocDao iQueryDocDao;

    @Override
    public void queryDoc(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        if (params.containsKey("start") && params.containsKey("limit")) {
            params.put("start", MapUtils.getIntValue(params, "start"));
            params.put("limit", MapUtils.getIntValue(params, "limit"));
        }

        int total = iQueryDocDao.queryDocCount(params);

        List<Map<String, Object>> beans = iQueryDocDao.queryDoc(params);
        if (CollectionUtils.isEmpty(beans)) {
            beans = new ArrayList<>();
        }
        outputObject.setBeans(beans);
        outputObject.getBean().put("total", total);
    }

    /**
     * 上传文件
     * @param inputObject
     * @param outputObject
     */
    public void uploadDoc(InputObject inputObject, OutputObject outputObject) {
        //入库操作
        HashMap<String, Object> params = inputObject.getParams();
        String fileNm = MapUtils.getString(params, "fileNm");
        String uuid = UUID.randomUUID().toString();

        Map<String, Object> map = new HashMap<>();
        map.put("docNm", fileNm);
        map.put("docUniqueIdentity", uuid);
        map.put("uploadBy", MapUtils.getString(params, "uploadBy"));
        map.put("docPath", Constants.UPLOAD_PATH + MapUtils.getString(params, "newFileNm"));
        iQueryDocDao.uploadDoc(map);
    }

    /**
     * 下载文件
     * @param inputObject
     * @param outputObject
     */
    public void downLoadDoc(InputObject inputObject, OutputObject outputObject) {
        HashMap<String, Object> params = inputObject.getParams();
        String uuid = MapUtils.getString(params, "uuid");

        if (StringUtils.isBlank(uuid)) {
            return;
        }

        params.put("docUniqueIdentity", uuid);
        List<Map<String, Object>> beans = iQueryDocDao.queryDoc(inputObject.getParams());
        outputObject.setBeans(beans);
    }
}
