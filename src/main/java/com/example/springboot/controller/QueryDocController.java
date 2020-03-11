package com.example.springboot.controller;

import com.example.springboot.common.annotations.InputObject;
import com.example.springboot.common.bean.OutputObject;
import com.example.springboot.common.bean.TxtException;
import com.example.springboot.service.IQueryDocService;
import com.example.springboot.util.Constants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ZhangPei on 2019/11/22.
 */
@Controller
public class QueryDocController {
    @Autowired
    private IQueryDocService iQueryDocService;


    @RequestMapping(value = "/queryDocs", method = RequestMethod.POST)
    @ResponseBody
    public OutputObject queryDocs(@InputObject com.example.springboot.common.bean.InputObject inputObject, OutputObject outputObject) {
        inputObject.getParams().put("dbKey", "proj_busi");
        iQueryDocService.queryDoc(inputObject, outputObject);
        return outputObject;
    }

    @RequestMapping(value = "/uploadDoc", method = RequestMethod.POST)
    @ResponseBody
    public void uploadDoc(@RequestParam("file") MultipartFile multipartFile,
                          @InputObject com.example.springboot.common.bean.InputObject inputObject, OutputObject outputObject) throws Exception {
        HashMap<String, Object> params = inputObject.getParams();
        params.put("dbKey", "proj_busi");
        if (StringUtils.isBlank(MapUtils.getString(params, "fileNm"))) {
            throw new TxtException("上传文件名为空！");
        }

        byte[] bytes = multipartFile.getBytes();

        //文件扩展名
        String fileNm = MapUtils.getString(params, "fileNm");
        String fileExt = fileNm.substring(fileNm.lastIndexOf("."));
        if (!Arrays.asList(Constants.ALLOW_EXTEND.split(",")).contains(fileExt)) {
            throw new TxtException("文件类型只支持.doc和.txt");
        }

        String newFileNm = UUID.randomUUID().toString() + fileExt;
        params.put("newFileNm", newFileNm);
        FileCopyUtils.copy(bytes, new File(Constants.UPLOAD_PATH + newFileNm));

        //入库操作
        iQueryDocService.uploadDoc(inputObject, outputObject);
    }

    /**
     * 下载文档
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping(value = "/downLoadDoc", method = RequestMethod.GET)
    public void downLoadDoc(@InputObject com.example.springboot.common.bean.InputObject inputObject,
                            OutputObject outputObject, HttpServletResponse httpServletResponse) throws IOException {
        HashMap<String, Object> params = inputObject.getParams();
        params.put("dbKey", "proj_busi");
        iQueryDocService.downLoadDoc(inputObject, outputObject);

        if (CollectionUtils.isNotEmpty(outputObject.getBeans())) {
            Map<String, Object> resultMap = outputObject.getBeans().get(0);
            String docPath = MapUtils.getString(resultMap, "docPath");

            FileInputStream fileInputStream = new FileInputStream(new File(docPath));
            ServletOutputStream outputStream = httpServletResponse.getOutputStream();
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            //解决中文乱码的问题
            httpServletResponse.addHeader("Content-Disposition", "attachment; filename=" + new String(MapUtils.getString(resultMap, "docNm").getBytes("UTF-8"), "ISO-8859-1"));

            int lenth;
            while ((lenth = fileInputStream.read()) > -1) {
                outputStream.write(lenth);
                outputStream.flush();
            }
            outputStream.close();
        }
    }

    @RequestMapping(value = "/lookOnline", method = RequestMethod.GET)
    public void lookOnline(@InputObject com.example.springboot.common.bean.InputObject inputObject,
                           OutputObject outputObject, HttpServletResponse httpServletResponse) throws Exception {
        HashMap<String, Object> params = inputObject.getParams();
        params.put("dbKey", "proj_busi");
        iQueryDocService.downLoadDoc(inputObject, outputObject);

        if (CollectionUtils.isNotEmpty(outputObject.getBeans())) {
            Map<String, Object> resultMap = outputObject.getBeans().get(0);
            String docPath = MapUtils.getString(resultMap, "docPath");
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            String imagePath = path.getAbsolutePath() + "\\static\\image";
            String targetFileName = "/Users/smile_mylife/Desktop/test.html";

            OutputStreamWriter outputStreamWriter = null;
            try {
                XWPFDocument document = new XWPFDocument(new FileInputStream(docPath));
                XHTMLOptions options = XHTMLOptions.create();
                // 存放图片的文件夹
//            options.setExtractor(new FileImageExtractor(new File(imagePath)));
                // html中图片的路径
                options.URIResolver(new BasicURIResolver("image"));
                outputStreamWriter = new OutputStreamWriter(httpServletResponse.getOutputStream());
                XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
                xhtmlConverter.convert(document, outputStreamWriter, options);
            } finally {
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
            }
        }
    }
}
