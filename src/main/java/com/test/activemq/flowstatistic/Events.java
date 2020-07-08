package com.test.activemq.flowstatistic;

public class Events {
    public Data data;
    public String eventId;
    public String eventType;
    public String happenTime;
    public String srcIndex;
    public String srcType;
    public String status;
    public String timeout;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getHappenTime() {
        return happenTime;
    }

    public void setHappenTime(String happenTime) {
        this.happenTime = happenTime;
    }

    public String getSrcIndex() {
        return srcIndex;
    }

    public void setSrcIndex(String srcIndex) {
        this.srcIndex = srcIndex;
    }

    public String getSrcType() {
        return srcType;
    }

    public void setSrcType(String srcType) {
        this.srcType = srcType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public Events() {
    }

    public Events(Data data, String eventId, String eventType, String happenTime, String srcIndex, String srcType, String status, String timeout) {
        this.data = data;
        this.eventId = eventId;
        this.eventType = eventType;
        this.happenTime = happenTime;
        this.srcIndex = srcIndex;
        this.srcType = srcType;
        this.status = status;
        this.timeout = timeout;
    }
}
