package com.suyihan.handle;

import com.suyihan.response.Result;
import com.suyihan.response.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandle {
//全局异常，没有指定异常的类型
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
//        e.printStackTrace();
        log.error(e.getMessage());
        return Result.error();
    }
//  算术异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.error().code(ResultCode.ARITHMETICEXCEPTION.getCode())
                .message(ResultCode.ARITHMETICEXCEPTION.getMessage());
    }
//    自定义异常
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result error(BusinessException e){
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.error().code(e.getCode()).message(e.getMessage());
    }
}
