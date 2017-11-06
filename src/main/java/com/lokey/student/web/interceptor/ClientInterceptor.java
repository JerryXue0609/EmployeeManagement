package com.lokey.student.web.interceptor;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2015/12/7.
 */
public class ClientInterceptor implements HandlerInterceptor {

    private Logger logger = Logger.getLogger("ClientInterceptor");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        if ("GET".equalsIgnoreCase(request.getMethod())) {
            //RequestUtil.saveRequest();
        }
        //log.info("==============执行顺序: 1、preHandle================");
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        System.err.println("requestUri=" + requestUri);
        System.err.println("contextPath=" + contextPath);
        System.err.println("url:" + url);

        response.setHeader("Access-Control-Allow-Origin", "*");

        if (url.equals("/admin/login.html") || url.equals("/admin/login.json")) {
            return true;
        } else {
            if (url.contains("admin")) {
                String username = (String) request.getSession().getAttribute("username");
                if (username == null) {
                    //log.info("Interceptor：跳转到login页面！");
                    //request.getRequestDispatcher("/admin/login.jsp").forward(request, response);

                    response.sendRedirect("../admin/login.html");
                    return false;
                } else
                    return true;
            } else {
                return true;
            }
        }

    }




    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
