package com.test.rabbitmq;//package com.hikvision.jiangsu.ctm01dmonitor.modules.rabbitmq;
//
//import com.hikvision.ga.logger.build.HikGaLoggerFactory;
//import com.hikvision.ga.logger.log.HikGaLogger;
//import com.rabbitmq.client.*;
//
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeoutException;
//
///**
// *@ClassName ProCosumer
// *@Description rabbitmq 生产消费者
// *@Author wangyongtao
// *@Date 2020/5/28 15:12
// *@Version 1.0
// */
//public class ProCosumer {
//    private static HikGaLogger logger = HikGaLoggerFactory.getLogger(ProCosumer.class);
//
//    private static final String EXCHANGE_NAME = "xalarm_aps_exchange_forward_to_component";
//    private static final String ROUTING_KEY   = "ctm01tempshow";
//    private static final String QUEUE_NAME    = "queue_demo";
//    private static final String IP_ADDRESS    = "10.194.98.30";
//    private static final int    PORT          = 6005;
//    private static final String USERNAME      = "root";
//    private static final String PASSWORD      = "rKG6g789";
//
//    /**
//     * 向指定rabbitmq地址发送消息
//     */
//    public void producer( String msg ) throws IOException, TimeoutException {
//        Connection connection = null;
//        Channel channel = null;
//        ConnectionFactory factory = null;
//        try {
//            factory = new ConnectionFactory();
//            factory.setHost(IP_ADDRESS);
//            factory.setPort(PORT);
//            factory.setUsername("guest");
//            factory.setPassword("guest");
//            //创建连接
//            connection = factory.newConnection();
//            //创建信道
//            channel = connection.createChannel();
//
////            //创建一个type="direct"、持久化的、非自动删除的交换器 ( 向已有队列发送不用执行 )
////            channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
////            //创建一个持久化、非排他的、非自动删除的队列  ( 向已有队列发送不用执行 )
////            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
////            //将交换器与队列通过路由键绑定  ( 向已有队列发送不用执行 )
////            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
//
//            //发送一条持久化的消息
//            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
//        } catch (Exception e) {
//            logger.error("发送消息到指定队列报错：",e);
//        } finally {
//            logger.info("开始关闭channel和connection资源");
//            //关闭资源
//            if (channel!=null){
//                channel.close();
//            }
//            if (connection!=null){
//                connection.close();
//            }
//        }
//
//    }
//
//
//    public void consumer() throws IOException, TimeoutException, InterruptedException {
//        Address[] addresses = new Address[] { new Address(IP_ADDRESS, PORT) };
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setUsername(USERNAME);
//        factory.setPassword(PASSWORD);
//        //创建连接,这里的连接方式与生产者的demo略有不同，注意辨别区别
//        Connection connection = factory.newConnection(addresses);
//        //创建信道
//        final Channel channel = connection.createChannel();
//        //设置客户端最多接受未被ack的消息的个数
//        channel.basicQos(64);
//        Consumer consumer = new DefaultConsumer(channel) {
//            @Override
//            public void handleDelivery(String consumerTag,Envelope envelope,AMQP.BasicProperties properties,byte[] body) throws IOException {
//                System.out.println("recv message:" + new String(body));
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                channel.basicAck(envelope.getDeliveryTag(), false);
//            }
//        };
//        //动态获取队列名
//        String queue = channel.queueDeclare().getQueue();
//        System.out.println(queue);
//        channel.basicConsume(queue, consumer);
//        //等待回调函数执行完毕之后，关闭资源
//        TimeUnit.SECONDS.sleep(5);
//        channel.close();
//        connection.close();
//    }
//
//    public static void main(String[] args) {
//
//    }
//
//
//}
