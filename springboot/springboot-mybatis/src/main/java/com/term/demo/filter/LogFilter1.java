package com.term.demo.filter;

import com.term.demo.interceptor.LogInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class LogFilter1 implements Filter {
    private static final Logger log = LoggerFactory.getLogger(LogFilter1.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("-----------LogFilter1 开始------------");
        log.info("请求地址1：" + request.getRequestURL().toString() +" "+ request.getMethod());
        log.info("远程地址1：" + request.getRemoteAddr());

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("----------- LogFilter1 结束耗时: " + (System.currentTimeMillis() - startTime) + "ms ---------------");
    }
}
