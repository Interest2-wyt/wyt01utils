package com.test.activemq.topic;

import com.alibaba.fastjson.JSON;
import com.test.activemq.flowstatistic.FlowStatisticInfo;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivemqTools {
    private static final String ACTIVEMQ_URL = "tcp://localhost:61616";
    private static final String Topic_NAME = "iac.ifoottraffic.flow_density_data.topic";
    private static final ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    public static String getCurrentTime(Date date){
        return df.format(date).substring(11,13);// new Date()为获取当前系统时间
    }

    public static void Producer(FlowStatisticInfo object) throws JMSException, InterruptedException {
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic(Topic_NAME);
        MessageProducer producer = session.createProducer(destination);
        TextMessage message = null;
        int cnt = 0;
        String[] indexCodes = new String[17];
        indexCodes[4] = "4c777ca96a1c4eb997ab89caf34f3044";
        indexCodes[5] = "77072b852663437c974572378aceddad";
        indexCodes[6] = "589804809d644f65b6cb2b8000dabaab";
        indexCodes[9] = "e42a2ee3b37a43df857dd02164b66d10";
        indexCodes[0] = "607f1a07fe1140e6a2057d2f0bcc48a3";
        indexCodes[1] = "ecf647a06e0642d59b78222744f49f9c";
        indexCodes[2] = "ee0a8827c1ae46fe94ede1b23da58d4f";
        indexCodes[7] = "11b77a2b54e8418db30cf37907b8b442";
        indexCodes[8] = "7def0ae274944a47b69d05e54034d2cc";
        indexCodes[3] = "b0a13a6004f44d75a6819e273afbfb5e";
        indexCodes[10]="32d15d9ec9814788951a8f8e0ad0abfe";
        indexCodes[11]="a210502eedc842da9b7a1c8ece2fe997";
        indexCodes[12]="6b4b1aaea4f04b3ba6c53e856fdc4444";
        indexCodes[13]="83457d2b7a56493c954e738cd4fcb814";
        indexCodes[14]="851e8920d81b4bf9bb55b87fae36788d";
        indexCodes[15]="bb753d3cbdc443938037e3ff7e4c59ec";
        indexCodes[16]="062f98cea8c04921bc6393defd6c046c";

        while (true){
            for (int i=0;i<1000;i++){
                Thread.sleep(10000);
                object.getParams().getEvents().get(0).getData().getPeopleCounting().get(0).getTargetAttrs().setCameraIndexCode(indexCodes[i%17]);
                object.getParams().getEvents().get(0).setSrcIndex(indexCodes[i%17]);
                StringBuffer buffer = new StringBuffer();
                buffer.append("2019-07-09T").append(getCurrentTime(new Date())).append(":25:57.184+08:00");
                object.getParams().getEvents().get(0).getData().setDateTime(buffer.toString());
                object.getParams().getEvents().get(0).getData().setSendTime(buffer.toString());
                message = session.createTextMessage(JSON.toJSONString(object));
                producer.send(message);
            }
            System.out.println("每轮1000秒，现在为： " + cnt);
        }
    }

}
