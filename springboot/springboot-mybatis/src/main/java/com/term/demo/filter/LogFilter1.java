package com.term.demo.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class LogFilter1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("-----------LogFilter1 开始------------");
        System.out.println("请求地址1：" + request.getRequestURL().toString() +" "+ request.getMethod());
        System.out.println("远程地址1：" + request.getRemoteAddr());

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("----------- LogFilter1 结束耗时: " + (System.currentTimeMillis() - startTime) + "ms ---------------");
    }
}
