package com.test.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {

    //定义ActivMQ的连接地址
    private static final String ACTIVEMQ_URL = "tcp://172.27.254.201:7018";
    //定义发送消息的队列名称
    private static final String Topic_NAME = "iac.ifoottraffic.flow_density_data.topic";
    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //创建连接
        Connection connection = activeMQConnectionFactory.createConnection();
        //打开连接
        connection.start();
        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列目标
        Destination destination = session.createTopic(Topic_NAME);
        //创建消费者
        javax.jms.MessageConsumer consumer = session.createConsumer(destination);
        //创建消费的监听
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("获取消息：" + textMessage.getText());
                } catch (JMSException e) {
                    System.out.println("error");
                    e.printStackTrace();
                }
            }
        });
    }
}
