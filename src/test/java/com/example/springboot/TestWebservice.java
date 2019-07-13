package com.example.springboot;
import javax.xml.namespace.QName;

import org.apache.axis.Constants;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;

/**
 * webservice客户端测试
 * Created by ZhangPei on 2019/7/10.
 */
public class TestWebservice {
    public static void main(String[] args) {
        Service service = new Service();
        try {
            Call call = (Call)service.createCall();
            //设置地址
            call.setTargetEndpointAddress("http://localhost:8080/services/HelloServiceImpl?wsdl");
            //设置要执行的方法(以下两种方式都可以)
//			call.setOperationName("sayHello");
            call.setOperationName(new QName("http://example","testFileUpload"));
            //设置要传入参数,如果没有要传入的参数，则不要写这个（参数名、参数类型、ParameterMode）
            call.addParameter("fileByte", Constants.XSD_HEXBIN,javax.xml.rpc.ParameterMode.IN);
            //设置返回的类型
            call.setReturnType(org.apache.axis.Constants.XSD_STRING);
            //调用WebService服务
            String info = "小鱼儿，你好！";
            byte[] bytes = FileCopyUtils.copyToByteArray(new File("/Users/smile_mylife/Desktop/WechatIMG58.jpeg"));
            String result = (String) call.invoke(new Object[]{bytes});
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
