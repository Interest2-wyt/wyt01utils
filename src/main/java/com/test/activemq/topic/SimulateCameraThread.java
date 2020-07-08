package com.test.activemq.topic;


import com.test.activemq.flowstatistic.FlowStatisticInfo;

public class SimulateCameraThread implements Runnable{
    public FlowStatisticInfo flowStatisticInfo;

    public void setFlowStatisticInfo(FlowStatisticInfo flowStatisticInfo) {
        this.flowStatisticInfo = flowStatisticInfo;
    }
    public void run(){
        try {
            ActivemqTools.Producer(flowStatisticInfo);
        }catch (Exception e){
            System.out.println("出现异常");
        }
    }

}
