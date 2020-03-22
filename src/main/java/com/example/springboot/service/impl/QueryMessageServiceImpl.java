package com.example.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.common.bean.InputObject;
import com.example.springboot.common.bean.OutputObject;
import com.example.springboot.common.bean.TSrMessageInfo;
import com.example.springboot.dao.TSrMessageInfoDao;
import com.example.springboot.service.IQueryMessageService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QueryMessageServiceImpl implements IQueryMessageService {
    @Autowired
    private TSrMessageInfoDao tSrMessageInfoDao;
    /**
     * json文件上传
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void uploadMessage(InputObject inputObject, OutputObject outputObject) {
        HashMap<String, Object> params = inputObject.getParams();
        String jsonStr = MapUtils.getString(params, "jsonStr");

        //解析postman导出的json
        if (StringUtils.isNotBlank(jsonStr)) {
            Map map = JSON.parseObject(jsonStr, Map.class);
            JSONArray collections = (JSONArray) map.get("collections");
            if (CollectionUtils.isNotEmpty(collections)) {
                for (int i = 0; i < collections.size(); i++) {
                    Map collMap = (Map) collections.get(i);
                    JSONArray requests = (JSONArray) collMap.get("requests");
                    if (CollectionUtils.isNotEmpty(requests)) {
                        List<TSrMessageInfo> list = new ArrayList<>();
                        for (int j = 0; j < requests.size(); j++) {
                            Map requestMap = (Map) requests.get(j);
                            String headers = MapUtils.getString(requestMap, "headers");
                            String data = MapUtils.getString(requestMap, "data");
                            String method = MapUtils.getString(requestMap, "method");
                            String url = MapUtils.getString(requestMap, "url");
                            String name = MapUtils.getString(requestMap, "name");
                            String[] headerArr = headers.split("\\n");
                            String contentType = "";
                            for (int k = 0; k < headerArr.length; k++) {
                                if (headerArr[k].contains("Content-Type:")) {
                                    int startIndex = headerArr[k].indexOf("Content-Type: ");
                                    if (startIndex != -1) {
                                        contentType = headerArr[k].substring(14);
                                        if (contentType.equals("multipart/form-data")) {
                                            JSONArray jsonArray = JSON.parseArray(data);
                                            JSONObject insertJsonOjb = new JSONObject();
                                            for (int l = 0; l < jsonArray.size(); l++) {
                                                JSONObject jsonObject = jsonArray.getJSONObject(l);
                                                String key = MapUtils.getString(jsonObject, "key");
                                                String value = MapUtils.getString(jsonObject, "value");
                                                insertJsonOjb.put(key, value);
                                            }
                                            data = JSON.toJSONString(insertJsonOjb);
                                        }
                                    }
                                    break;
                                }
                            }
                            //批量入表
                            if (StringUtils.isNotBlank(url) && StringUtils.isNotBlank(method)
                                    && StringUtils.isNotBlank(contentType) && StringUtils.isNotBlank(data)) {
                                TSrMessageInfo tSrMessageInfo = new TSrMessageInfo();
                                tSrMessageInfo.setRequestUrl(url);
                                tSrMessageInfo.setRequestMethod(method);
                                tSrMessageInfo.setContentType(contentType);
                                tSrMessageInfo.setInterName(name);
                                tSrMessageInfo.setRequestMessage(JSON.toJSONString(JSON.parse(data)));
                                list.add(tSrMessageInfo);
                            }
                        }
                        if (CollectionUtils.isNotEmpty(list)) {
                            tSrMessageInfoDao.insertList(list);
                        }
                    }
                }
            }

        }
    }

    /**
     * 查询报文
     * @param inputObject
     * @param outputObject
     */
    public void queryMessage(InputObject inputObject, OutputObject outputObject) {

        Map<String, Object> params = inputObject.getParams();
        if (params.containsKey("start") && params.containsKey("limit")) {
            params.put("start", MapUtils.getIntValue(params, "start"));
            params.put("limit", MapUtils.getIntValue(params, "limit"));
        }

        int total = tSrMessageInfoDao.selectCount(params);

        List<Map<String, Object>> beans = tSrMessageInfoDao.selectAll(params);
        if (CollectionUtils.isEmpty(beans)) {
            beans = new ArrayList<>();
        }
        outputObject.setBeans(beans);
        outputObject.getBean().put("total", total);

    }
}
