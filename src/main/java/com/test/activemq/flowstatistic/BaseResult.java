package com.test.activemq.flowstatistic;

public class BaseResult<T> {
    private int type = -1;
    private String code;
    private String msg;
    private T data;

    public BaseResult() {
    }

    public BaseResult(int type, String code, String msg, T data) {
        this.type = type;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
