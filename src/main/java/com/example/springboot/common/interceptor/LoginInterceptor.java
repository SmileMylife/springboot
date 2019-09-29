package com.example.springboot.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by ZhangPei on 2019/9/29.
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 在请求被处理之前做的事情
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, ServletException {
        StringBuffer requestURL = request.getRequestURL();
        System.out.println("本次请求url为：" + requestURL);

        HttpSession session = request.getSession();
        boolean isLogin = false;
        if (session != null) {
            //如果用户登录成功，则会把用户的sessionID和塞进session中
            Object loginFlag = session.getAttribute(session.getId());
            if (loginFlag instanceof Boolean) {
                isLogin = (boolean) loginFlag;
            }
        }
        if (!isLogin) {
            response.sendRedirect("/loginPage");
        }
        return isLogin;
    }

    /**
     * 在服务器受理请求后，但在渲染视图层之前做的事情
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws ServletException, IOException {
        System.out.println(modelAndView);
    }

    /**
     * 在视图层渲染之后做的事情
     * @param request
     * @param response
     * @param handler
     * @param ex
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
