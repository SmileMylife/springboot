package com.example.springboot.controller;

import com.example.springboot.common.annotations.InputObject;
import com.example.springboot.common.bean.OutputObject;
import com.example.springboot.util.QRCodeUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

/**
 * Created by ZhangPei on 2019/11/22.
 */
@Controller
public class LoginLogoutController {
    /**
     * 登录逻辑验证，需要兼容二维码扫描登录
     *
     * @param inputObject
     * @param outputObject
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginSqlProduct")
    public ModelAndView login(@InputObject com.example.springboot.common.bean.InputObject inputObject, OutputObject outputObject, HttpServletRequest request) {
        String username = MapUtils.getString(inputObject.getParams(), "username");
        String password = MapUtils.getString(inputObject.getParams(), "password");

        ModelAndView loginModelAndView = new ModelAndView();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            loginModelAndView.setViewName("login");
            return loginModelAndView;
        } else if ("root".equals(username)) {
            loginModelAndView.setViewName("index");
        }
        loginModelAndView.addObject("isError", "false");
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60 * 60 * 24);     //60秒过期
        session.setAttribute(request.getSession().getId(), true);
        return loginModelAndView;
    }

    /**
     * 注销
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView distroySession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    /**
     * 刷新二维码
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping(value = "/refreshQRcode", method = RequestMethod.GET)
    public void refreshQRcode(@InputObject com.example.springboot.common.bean.InputObject inputObject, OutputObject outputObject, HttpServletResponse response) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        UUID uuid = UUID.randomUUID();
        QRCodeUtils.encode("http://172.18.255.19:8080/loginSqlProduct?uuid=" + uuid.toString(), byteArrayOutputStream);
        response.setContentType("image/png");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(byteArrayOutputStream.toByteArray());
        byteArrayOutputStream.close();
    }
}
