package com.test.activemq.flowstatistic;

import java.util.List;

public class Params {
     public String ability;
     public String sendTime;
     public List<Events> events;

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public List<Events> getEvents() {
        return events;
    }

    public void setEvents(List<Events> events) {
        this.events = events;
    }

    public Params() {
    }

    public Params(String ability, String sendTime, List<Events> events) {
        this.ability = ability;
        this.sendTime = sendTime;
        this.events = events;
    }
}
