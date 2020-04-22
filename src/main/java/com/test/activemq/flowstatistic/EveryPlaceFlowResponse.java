package com.test.activemq.flowstatistic;

public class EveryPlaceFlowResponse {
    String location;
    int enter;
    int exit;
    int keeps;

    public EveryPlaceFlowResponse() {
    }

    public EveryPlaceFlowResponse(String location, int enter, int exit, int keeps) {
        this.location = location;
        this.enter = enter;
        this.exit = exit;
        this.keeps = keeps;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getEnter() {
        return enter;
    }

    public void setEnter(int enter) {
        this.enter = enter;
    }

    public int getExit() {
        return exit;
    }

    public void setExit(int exit) {
        this.exit = exit;
    }

    public int getKeeps() {
        return keeps;
    }

    public void setKeeps(int keeps) {
        this.keeps = keeps;
    }
}
