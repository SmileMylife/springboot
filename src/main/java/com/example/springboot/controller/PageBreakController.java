package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

/**
 * 页面跳转控制器
 * Created by ZhangPei on 2019/5/28.
 */
@Controller
public class PageBreakController extends BaseControllerServceImpl {

    /**
     * 去首页
     * @param isError
     * @return
     */
    //跳转至首页
    @RequestMapping(value = "/toSqlProduct", method = RequestMethod.GET)
    public ModelAndView toSqlProduct(String isError, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String sessionId = request.getSession().getId();
        Object loginFlag = request.getSession().getAttribute(sessionId); //登录状态

        modelAndView.setViewName("login");
        if (loginFlag instanceof Boolean) {
            boolean loginFlagResult = (boolean) loginFlag;
            if (loginFlagResult) {
                modelAndView.setViewName("sqlProduct");
                if ("true".equals(isError)) {
                    //如果是错误页面中跳转过来的则需要告知页面回填数据
                    modelAndView.addObject("isError", "true");
                } else {
                    modelAndView.addObject("isError", "");
                }
            }
        }
        return modelAndView;
    }

    /**
     * 去首页
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView toIndex() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    //跳转至sql转换页面
    @RequestMapping(value = "/transposition", method = RequestMethod.GET)
    public ModelAndView toTransposition() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transposition");
        return modelAndView;
    }

    /**
     * 前往登录页面
     * @return
     */
    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public ModelAndView tologinPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /**
     * 前往图文识别页面
     * @return
     */
    @RequestMapping(value = "/toOcr", method = RequestMethod.GET)
    public ModelAndView toOcr() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ocr");
        return modelAndView;
    }
}
