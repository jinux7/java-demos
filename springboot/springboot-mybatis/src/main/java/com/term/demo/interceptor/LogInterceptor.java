package com.term.demo.interceptor;

import com.term.demo.cotroller.UserController;
import com.term.demo.exception.UnAuthException;
import com.term.demo.util.redis.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class LogInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(LogInterceptor.class);
    @Autowired
    private RedisCache redisCache;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("------------- LogInterceptor 开始 -------------");
        log.info("拦截器请求地址：" + request.getRequestURL().toString() +" "+ request.getMethod());
        log.info("拦截器远程地址：" + request.getRemoteAddr());
        long startTime = System.currentTimeMillis();
        request.setAttribute("requestStartTime", startTime);

        String method = request.getMethod();
        if("OPTIONS".equals(method)) {
            return true;
        }
        String token = request.getHeader("Authorization");
        log.info("token=> {}", token);
        if(token==null) {
            throw new UnAuthException();
        }
        Object CacheToken = redisCache.getCacheObject(token);
        if(CacheToken==null) {
            throw new UnAuthException();
        }
        log.info("cachetoken=> {}", CacheToken);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (long) request.getAttribute("requestStartTime");
        log.info("------------- LogInterceptor 结束耗时：" + (System.currentTimeMillis() - startTime) + "ms ---------------");
    }
}
