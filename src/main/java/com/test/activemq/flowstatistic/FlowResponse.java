package com.test.activemq.flowstatistic;

public class FlowResponse {
    String time;
    String attr = "观音山上每小时总客流情况";
    int value=0;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public FlowResponse() {
    }

    public FlowResponse(String time, int value) {
        this.time = time;
        this.value = value;
    }

    public FlowResponse(String time, String attr, int value) {
        this.time = time;
        this.attr = attr;
        this.value = value;
    }
}
