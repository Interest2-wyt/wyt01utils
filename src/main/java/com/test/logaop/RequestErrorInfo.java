package com.test.logaop;

import lombok.Data;

/**
 * @Author : wangyongtao
 * @Description : 封装错误请求类
 * @Date : 2020/11/22 0022 14:05
 **/
@Data
public class RequestErrorInfo {
    private String ip;
    private String url;
    private String httpMethod;
    private String classMethod;
    private Object requestParams;
    private RuntimeException exception;
}
