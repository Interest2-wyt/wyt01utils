package com.test.logaop;

import lombok.Data;

/**
 * @Author : wangyongtao
 * @Description : 封装请求类
 * @Date : 2020/11/22 0022 14:04
 **/
@Data
public class RequestInfo {
    private String ip;
    private String url;
    private String httpMethod;
    private String classMethod;
    private Object requestParams;
    private Object result;
    private Long timeCost;
}
