package com.example.springboot.controller;

import com.example.springboot.common.bean.InputObject;
import com.example.springboot.common.bean.OutputObject;
import com.example.springboot.other.AsyncTaskService;
import com.example.springboot.service.ITestSpringBootService;
import com.example.springboot.thread.ThreadPoolTestServiceImpl;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by ZhangPei on 2018/11/15.
 */
@Controller
public class TestSpringBootController {
    @Autowired
    private ITestSpringBootService iTestSpringBootService;

    @RequestMapping(value = "/testRequest", method = RequestMethod.GET)
    @ResponseBody
    public String testRequest(HttpServletRequest httpServletRequest) throws IOException {
        int contentLength = httpServletRequest.getContentLength();
        ServletInputStream inputStream = httpServletRequest.getInputStream();

        byte[] bytes = new byte[contentLength];
        inputStream.read(bytes);

        String s = new String(bytes);
        System.out.println(s);

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
    public OutputObject testDaoOperation(@com.example.springboot.common.annotations.InputObject InputObject inputObject, OutputObject outputObject) throws Exception {
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
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        map.put("params", input);
        map.put("list", integers);


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
    public String testRestTemplate(@RequestBody String json, String xmlhead, String xmlbody, String provCode, HttpServletRequest httpServletRequest) {
        System.out.println(json);
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
    public OutputObject login(@com.example.springboot.common.annotations.InputObject InputObject inputObject, OutputObject outputObject) {
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
    public OutputObject queryEmployees(@com.example.springboot.common.annotations.InputObject InputObject inputObject, OutputObject outputObject) throws Exception {
        HashMap<String, Object> params = inputObject.getParams();
        Integer start = MapUtils.getInteger(params, "start");
        Integer limit = MapUtils.getInteger(params, "limit");
        params.put("start", start);
        params.put("limit", limit);
        iTestSpringBootService.queryEmployees(inputObject, outputObject);
        return outputObject;
    }

    @RequestMapping(value = "/insertDbInfo", method = RequestMethod.POST)
    @ResponseBody
    public void insertDbInfo(@com.example.springboot.common.annotations.InputObject InputObject inputObject, OutputObject outputObject) throws Exception {
        inputObject.getParams().put("dbKey", "ngwf");
        iTestSpringBootService.insertDbInfo(inputObject, outputObject);
    }

    @RequestMapping(value = "/testTransaction", method = RequestMethod.POST)
    @ResponseBody
    public void testTransaction(InputObject inputObject, OutputObject outputObject) throws Exception {
        iTestSpringBootService.testTransaction(inputObject, outputObject);
    }

    @RequestMapping(value = "/fileDownload", method = RequestMethod.GET)
    public void fileDownload(HttpServletResponse response, String fileName) throws Exception {
        //方法一
        if (StringUtils.isBlank(fileName)) {
            throw new Exception("文件下载失败，文件名为空");
        }
        FileInputStream fileInputStream = new FileInputStream(new File(this.getClass().getResource("/").getPath() + "/" + "files/" + fileName));
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
        response.addHeader("Content-Disposition", "attachment; filename=" + "template.txt");

        int lenth;
        while ((lenth = fileInputStream.read()) > -1) {
            outputStream.write(lenth);
            outputStream.flush();
        }
        outputStream.close();



        //方法二

        /*File file = new File(this.getClass().getResource("/").getPath() + "/" + "files/" + fileName);
        String filename = file.getName();

        HttpHeaders headers = new HttpHeaders();//http头信息

        String downloadFileName = new String(filename.getBytes("UTF-8"),"iso-8859-1");//设置编码

        headers.setContentDispositionFormData("attachment", downloadFileName);

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        //MediaType:互联网媒介类型  contentType：具体请求中的媒体类型信息

        return new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file),headers, HttpStatus.CREATED);*/

        /*//方法三
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment", "filename.jpg");
        byte[] bytes = FileUtils.readFileToByteArray(new File("a.jpg"));
        return new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.OK);*/
    }

    /**
     * 测试springboot自定义异常页面
     * @throws Exception
     */
    @RequestMapping(value = "/testWhitePage", method = RequestMethod.GET)
    public void testWhitePage() throws Exception {
        throw new Exception();
    }

    /**
     * 测试文件在服务器间的传输
     * @throws IOException
     */
    @RequestMapping("/tesPicDownload")
    @ResponseBody
    public void testFileDownload() throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Map<String, Object>> mapHttpEntity = new HttpEntity<>(new HashMap<>(), httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        byte[] stringResponseEntity = restTemplate.getForObject("http://124.0.0.1:8080/testForGong", byte[].class);
        FileCopyUtils.copy(stringResponseEntity, new FileOutputStream(new File("/Users/smile_mylife/Desktop/test.jpeg")));
        System.out.println(stringResponseEntity);
    }


    /**
     * 测试图片下载
     */
    @RequestMapping(value = "/testForGong", method = RequestMethod.GET)
    public void tesPicDownload(HttpServletResponse response) throws IOException {
        response.setHeader("content-type", MediaType.IMAGE_JPEG_VALUE);
        ServletOutputStream outputStream = response.getOutputStream();

        byte[] bytes = FileCopyUtils.copyToByteArray(new File("/Users/smile_mylife/Desktop/WechatIMG58.jpeg"));
        outputStream.write(bytes);
        outputStream.flush();
    }

    @Autowired
    AsyncTaskService asyncTaskService;

    /**
     * 测试异步方法抛异常是否会影响正常方法，答案是不会
     * @throws Exception
     */
    @RequestMapping(value = "testAsync", method = RequestMethod.POST)
    @ResponseBody
    public void testAsync() throws Exception {
        asyncTaskService.testAsync();

        for (int i = 0; i < 100000; i++) {
            i++;
        }
        System.out.println("测试异步调用");
    }

    //测试发送json请求
    @RequestMapping(value = "/testAjax", method = RequestMethod.POST)
    @ResponseBody

    public void testJson(HttpServletRequest httpServletRequest, String json) {
        System.out.println(json);
    }


    /**
     * 经过测试线程池的最大线程数量不超过maxpoolsize，流程是一开始线城市会初始化corePoolSize个线程
     * 但是线程处于等待接收任务状态，当有任务执行时，会启动线程去执行，当任务数量大于corePoolSize的时候，任务会先进任务队列中，
     * 如果任务队列存满了，则会扩充线程池的数量直到最大数量，然后如果还有任务未执行，则会根据拒绝策略进行处理，所以线程中所能处理的最大任务数量应该是
     * 最大线程数和任务队列任务的总和
     * 线程池测试方法
     */
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;
    @RequestMapping(value = "/testThread", method = RequestMethod.POST)
    @ResponseBody
    public void testThread() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            taskExecutor.execute(new Thread(new ThreadPoolTestServiceImpl()));
            int corePoolSize = taskExecutor.getCorePoolSize();
            int maxPoolSize = taskExecutor.getMaxPoolSize();
            int poolSize = taskExecutor.getPoolSize();
            int activeCount = taskExecutor.getActiveCount();

            System.out.println("核心线程池数量" + corePoolSize + "，最大线程池数量" + maxPoolSize + "，当前线程池大小：" + poolSize + "，已激活线程数量：" + activeCount);
        }

        Thread.sleep(20000);

        int poolSize = taskExecutor.getPoolSize();
        int corePoolSize = taskExecutor.getCorePoolSize();
        int maxPoolSize = taskExecutor.getMaxPoolSize();
        int activeCount = taskExecutor.getActiveCount();
        System.out.println("核心线程池数量" + corePoolSize + "，最大线程池数量" + maxPoolSize + "，当前线程池大小：" + poolSize + "，已激活线程数量：" + activeCount);
    }

    @RequestMapping(value = "/testThreadpoolDi")
    @ResponseBody
    public void testThreadpoolDi() {
        iTestSpringBootService.testTheadpoolDi();
    }

    @RequestMapping(value = "/testException")
    public void testException() throws Exception {
        throw new Exception("模拟异常抛出！");
    }
}
