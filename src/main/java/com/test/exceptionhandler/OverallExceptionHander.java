package com.test.exceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *@ClassName OverallExceptionHander
 *@Description 全局异常处理类，当有未捕获的异常抛出时，就会自动进入该配置。一是避免一个一个重复捕获，而是可以防止未经处理的异常直接返回前端
 *@Author wangyongtao
 *@Date 2020/4/11 9:39
 *@Version 1.0
 */
@RestControllerAdvice
public class OverallExceptionHander {
    private static Logger logger = LoggerFactory.getLogger(OverallExceptionHander.class);

    @ExceptionHandler(RuntimeException.class)
    public Object handRuntimeException(Exception e){
        logger.error("进入全局数据库异常处理类，该异常的详细信息为：[{}]",e);

        return "";
    }

}
