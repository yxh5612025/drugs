package com.yxh.springboot.exception;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.yxh.springboot.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice("com.yxh.springboot.controller")
public class MyExceptionHandler {
//    @ExceptionHandler(ServiceException.class)
//    @ResponseBody
//    public Result result(ServiceException e){
//        return Result.error(e.getCode(),e.getMessage());
//    }
private static final Log log = LogFactory.get();

    //统一异常处理@ExceptionHandler,主要用于Exception
    @ExceptionHandler(ServiceException.class)
    @ResponseBody//返回json串
    public Result<?> result(HttpServletRequest request, ServiceException e) {
        return Result.error(e.getCode(), e.getMessage());
    }
    //统一异常处理@ExceptionHandler,主要用于Exception
    @ExceptionHandler(Exception.class)
    @ResponseBody//返回json串
    public Result<?> error(HttpServletRequest request, Exception e) {
        log.error("异常信息：", e);
        return Result.error("-1", "系统异常");
    }

}
