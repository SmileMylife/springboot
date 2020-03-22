package com.example.springboot.service;

import com.example.springboot.common.bean.InputObject;
import com.example.springboot.common.bean.OutputObject;

public interface IQueryMessageService {
    public void uploadMessage(InputObject inputObject, OutputObject outputObject);

    public void queryMessage(InputObject inputObject, OutputObject outputObject);
}
