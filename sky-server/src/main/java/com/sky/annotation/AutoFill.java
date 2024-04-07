package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Mingkai Feng
 * @date 2024/4/1 9:44
 * @Description 自定义注解，用于表示某个方法需要进行功能字段自动填充
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {

    // 数据库操作，INSERT or UPDATE
    OperationType value();
}
