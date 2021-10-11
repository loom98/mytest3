package com.itheima.common.exception;

import com.itheima.common.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //标识该类是全局的异常处理类
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //写一个方法  方法 用于当controller发送异常的时候被调用 要捕获异常 代替 controller 去返回给前端
    //系统异常
    @ExceptionHandler(value=Exception.class)
    public Result handlerException(Exception e){
        //e.printStackTrace();
        LOGGER.error("系统错误异常是:",e);
        return Result.errorMessage(e.getMessage());
    }

    //LeadNewsException业务上的异常 才进行处理
    @ExceptionHandler(value=LeadNewsException.class)
    public Result handlerLeadNewsException(LeadNewsException e){
        //e.printStackTrace();
        LOGGER.error("业务错误异常是:",e);
        return Result.errorMessage(e.getMessage());
    }

}