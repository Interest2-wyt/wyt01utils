package com.test.activemq.topic;



import com.test.activemq.flowstatistic.*;

import javax.jms.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Producer {

    public static void main(String[] args) throws JMSException, InterruptedException {

        TargetAttrs targetAttrs = new TargetAttrs("平山堂东路","cameraindexcode","平山堂东路","deviceindexcode","平山堂东路","1","1","1");
        PeopleCounting peopleCounting = new PeopleCounting("51","100","2","realTime",targetAttrs);
        List<PeopleCounting> list = new ArrayList<PeopleCounting>();
        list.add(peopleCounting);
        StringBuffer buffer = new StringBuffer();
        buffer.append("2019-07-09T").append("16").append(":25:57.184+08:00");
        Data data = new Data(buffer.toString(),"1","flowStatistic",buffer.toString(),"peopleCounting","peopleCounting","172.27.7.72",list,"9001","2019-07-09T18:25:57.182+08:00");
        Events events = new Events(data,"44E20C02-A86C-3841-89A7-997F84FE52EF","131616","2019-07-09T18:46:59.000+08:00","4c777ca96a1c4eb997ab89caf34f3044","camera","0","0");
        List<Events> list1 = new ArrayList<Events>();
        list1.add(events);
        System.out.println(buffer.toString());
        Params params = new Params("event_pdc",buffer.toString(),list1);
        FlowStatisticInfo flowStatisticInfo = new FlowStatisticInfo("OnEventNotify",params);
        //1、创建17个线程，仿真17个摄像头并发访问
        Thread thread = null;
        SimulateCameraThread simulateCameraThread = null;
        for (int i=0;i<17;i++){
            simulateCameraThread = new SimulateCameraThread();
            simulateCameraThread.setFlowStatisticInfo(flowStatisticInfo);
            thread = new Thread(simulateCameraThread);
            thread.start();
        }

    }

}
