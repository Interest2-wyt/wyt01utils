package com.test.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {
    private static final String ACTIVEMQ_URL = "tcp://127.0.0.5:61616";
    private static final String QUEUE_NAME = "MyMessage";
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(QUEUE_NAME);
        MessageProducer producer = session.createProducer(destination);
        for (int i=1;i<=100;i++){
            TextMessage message = session.createTextMessage("我发送message：" + i);
            producer.send(message);
            System.out.println("我现在发送的消息是："+message.getText());
        }
        connection.close();
    }
}
