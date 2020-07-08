package com.test.activemq.flowstatistic;

import java.util.List;

public class Data {
    public String sendTime;
    public String channelID;
    public String dataType;
    public String dateTime;
    public String eventDescription;
    public String eventType;
    public String ipAddress;
    public List<PeopleCounting> peopleCounting;
    public String portNo;
    public String recvTime;

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public List<PeopleCounting> getPeopleCounting() {
        return peopleCounting;
    }

    public void setPeopleCounting(List<PeopleCounting> peopleCounting) {
        this.peopleCounting = peopleCounting;
    }

    public String getPortNo() {
        return portNo;
    }

    public void setPortNo(String portNo) {
        this.portNo = portNo;
    }

    public String getRecvTime() {
        return recvTime;
    }

    public void setRecvTime(String recvTime) {
        this.recvTime = recvTime;
    }

    public Data() {
    }

    public Data(String sendTime, String channelID, String dataType, String dateTime, String eventDescription, String eventType, String ipAddress, List<PeopleCounting> peopleCounting, String portNo, String recvTime) {
        this.sendTime = sendTime;
        this.channelID = channelID;
        this.dataType = dataType;
        this.dateTime = dateTime;
        this.eventDescription = eventDescription;
        this.eventType = eventType;
        this.ipAddress = ipAddress;
        this.peopleCounting = peopleCounting;
        this.portNo = portNo;
        this.recvTime = recvTime;
    }
}
