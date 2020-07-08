package com.test.activemq.flowstatistic;

public class FlowStatisticInfo {
    public String method;
    public Params params;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public FlowStatisticInfo() {
    }

    public FlowStatisticInfo(String method, Params params) {
        this.method = method;
        this.params = params;
    }
}
