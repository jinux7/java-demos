package com.term.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("------------- LogInterceptor start -------------");
        System.out.println("拦截器请求地址：" + request.getRequestURL().toString() +" "+ request.getMethod());
        System.out.println("拦截器远程地址：" + request.getRemoteAddr());

        long startTime = System.currentTimeMillis();
        request.setAttribute("requestStartTime", startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (long) request.getAttribute("requestStartTime");
        System.out.println("------------- LogInterceptor 结束耗时：" + (System.currentTimeMillis() - startTime) + "ms ---------------");
    }
}
