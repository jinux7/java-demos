package com.term.demo.exception;
import com.term.demo.resp.WrapResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 * 
 * @author ruoyi
 */
@RestControllerAdvice
public class GlobalExceptionHandler
{


    /**
     * 权限校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WrapResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request)
    {
//        System.out.println(e.getMessage());
        return WrapResult.resultError(e.getFieldError().getDefaultMessage());
    }

    /**
     * 全局异常
     */
    @ExceptionHandler(Exception.class)
    public WrapResult handleException(Exception e, HttpServletRequest request)
    {
//        System.out.println(e.getMessage());
        return WrapResult.resultError(e.toString());
    }

    // 拦截器登陆过起无权限异常
    @ExceptionHandler(UnAuthException.class)
    public WrapResult handleUnAuthException(Exception e, HttpServletRequest request)
    {
        return WrapResult.resultErrorUnAuth("登录过期，请重新登录");
    }
}
