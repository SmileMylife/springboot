package com.example.springboot.controller;

import com.example.springboot.bean.InputObject;
import com.example.springboot.bean.OutputObject;
import com.example.springboot.service.ITestSpringBootService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhangPei on 2018/11/15.
 */
@Controller
public class TestSpringBootController {
    @Autowired
    private ITestSpringBootService iTestSpringBootService;

    @RequestMapping(value = "/testRequest", method = RequestMethod.GET)
    @ResponseBody
    public String testRequest() {
        //application主函数要放在目录结构最外层
        System.out.println("测试springboot是否正常运行");
        return "";
    }

    @RequestMapping(value = "/testDaoOperation", method = RequestMethod.GET)
    @ResponseBody
    public List<HashMap<String, Object>> testDaoOperation() {
        List<HashMap<String, Object>> map = iTestSpringBootService.testDaoOperation();
        return map;
    }

    @RequestMapping(value = "/testParamsPackage", method = RequestMethod.GET)
    @ResponseBody
    public OutputObject testDaoOperation(@com.example.springboot.annotations.InputObject InputObject inputObject, OutputObject outputObject) throws Exception {
        iTestSpringBootService.testParamsPackage(inputObject, outputObject);
        return outputObject;
    }

    /**
     * 测试使用spring自带的类似HttpClient的工具调用http接口。
     *
     * @param inputObject
     * @param outputObject
     * @return
     */
    @RequestMapping(value = "/directIndex", method = RequestMethod.POST)
    @ResponseBody
    public String directIndex(InputObject inputObject, OutputObject outputObject, String upload) throws UnsupportedEncodingException {
        /*这种方法，发出去的参数在springmvc中接收不到

        RestTemplate restTemplate = new RestTemplate();
        HashMap<String, Object> map = new HashMap<>();
        map.put("xmlhead", "请求头");
        map.put("xmlbody", "请求体");
        map.put("provCode", "00030016");

        HttpEntity<Map<String, Object>> objectHttpEntity = new HttpEntity<>(map);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", MediaType.MULTIPART_FORM_DATA.toString());
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://127.0.0.1:8080/testRestTemplate", objectHttpEntity, String.class);
        String body = responseEntity.getBody();
        System.out.println("响应消息内容：" + body);


        //下面这种方法在springmvc中可以接收到
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("xmlhead", "请求头");
        map.add("xmlbody", "请求体");
        map.add("provCode", "省份编码");
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://127.0.0.1:8080/testRestTemplate";
        String s = restTemplate.postForObject(url, map, String.class);
        System.out.println(s);
        return s;*/

        HashMap<String, Object> input = new HashMap<>();
        input.put("provCode", "张佩");


        HashMap<String, Object> map = new HashMap<>();
        map.put("params", input);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Map<String, Object>> mapHttpEntity = new HttpEntity<>(map, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://127.0.0.1:8080/testRestTemplate", mapHttpEntity, String.class);
        String body = stringResponseEntity.getBody();

        String s = new String(body.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println("响应json数据为：" + s);
        return s;
    }

    //如果向要返回的数据中文不乱码，则需要加produce="text/plain;charset=utf-8"
    @RequestMapping(value = "/testRestTemplate", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String testRestTemplate(String xmlhead, String xmlbody, String provCode, HttpServletRequest httpServletRequest) {
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        System.out.println("报文头为：" + xmlhead);
        System.out.println("报文体为：" + xmlbody);
        System.out.println("省份编码为：" + provCode);
        String responseBody = "<?xml version = '1.1.0'><library><book>三国演义</book></library>";
        return responseBody;
    }

    @RequestMapping(value = "/testBinary")
    @ResponseBody
    public String testBinary(String str, MultipartFile multipartFile) throws IOException {
        byte[] bytes = str.getBytes();
        FileOutputStream fileOutputStream = new FileOutputStream(new File("./test.png"));
        fileOutputStream.write(bytes);
        fileOutputStream.flush();
        fileOutputStream.close();
        return null;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public OutputObject login(@com.example.springboot.annotations.InputObject InputObject inputObject, OutputObject outputObject) {
        outputObject.setRtnCode("0");
        outputObject.setRtnMsg("登录成功");
        return outputObject;
    }

    /**
     * 验证后台spirngmvc接收json对象的方式
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public OutputObject register(@RequestBody String json) {
        System.out.println(json);
        Jedis jedis = new Jedis("localhost", 6357);
        jedis.connect();
        jedis.set("username", "zhangpei");
        return new OutputObject();
    }

    /**
     * 查询员工信息，测试table组件使用
     * @param inputObject
     * @param outputObject
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryEmployees", method = RequestMethod.POST)
    @ResponseBody
    public OutputObject queryEmployees(@com.example.springboot.annotations.InputObject InputObject inputObject, OutputObject outputObject) throws Exception {
        HashMap<String, Object> params = inputObject.getParams();
        Integer start = MapUtils.getInteger(params, "start");
        Integer limit = MapUtils.getInteger(params, "limit");
        params.put("start", start);
        params.put("limit", limit);
        iTestSpringBootService.queryEmployees(inputObject, outputObject);
        return outputObject;
    }
}
