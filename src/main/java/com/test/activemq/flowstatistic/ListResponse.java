package com.test.activemq.flowstatistic;

import java.util.List;

public class ListResponse<T> {
    List<T> list = null;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public ListResponse(List<T> list) {
        this.list = list;
    }

    public ListResponse() {
    }
}
