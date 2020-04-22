package com.test.activemq.flowstatistic;

public class PeopleCounting {
    public String enter;
    public String exit;
    public String pass;
    public String statisticalMethods;
    public TargetAttrs targetAttrs;

    public String getEnter() {
        return enter;
    }

    public void setEnter(String enter) {
        this.enter = enter;
    }

    public String getExit() {
        return exit;
    }

    public void setExit(String exit) {
        this.exit = exit;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getStatisticalMethods() {
        return statisticalMethods;
    }

    public void setStatisticalMethods(String statisticalMethods) {
        this.statisticalMethods = statisticalMethods;
    }

    public TargetAttrs getTargetAttrs() {
        return targetAttrs;
    }

    public void setTargetAttrs(TargetAttrs targetAttrs) {
        this.targetAttrs = targetAttrs;
    }

    public PeopleCounting() {
    }

    public PeopleCounting(String enter, String exit, String pass, String statisticalMethods, TargetAttrs targetAttrs) {
        this.enter = enter;
        this.exit = exit;
        this.pass = pass;
        this.statisticalMethods = statisticalMethods;
        this.targetAttrs = targetAttrs;
    }
}
