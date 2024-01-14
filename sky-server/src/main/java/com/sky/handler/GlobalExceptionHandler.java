package com.sky.handler;

import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        // 得到报错信息：Duplicate entry 'zhangsan' for key 'employee.idx_username'
        String message = ex.getMessage();

        // 判断是否是重复数据异常
        if (message.contains("Duplicate")) {
            // 得到重复的用户名
            String[] split = message.split(" ");
            String userName = split[2];
            return Result.error("用户名已存在：" + userName);
        }
        // 若不是重复数据异常，则返回默认的错误信息
        else{
            return Result.error("位置错误");
        }
    }
}
