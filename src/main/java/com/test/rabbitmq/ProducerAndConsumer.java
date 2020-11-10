package com.test.rabbitmq;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *@ClassName ProducerAndConsumer
 *@Description rabbitmq 生产消费测试类
 *@Author wangyongtao
 *@Date 2020/9/25 11:33
 *@Version 1.0
 */
public class ProducerAndConsumer {

 /******************************************************* 生产消费模式（只声明队列就行，具体情况看代码） **************************************************************************/
    /**
     * 测试生产者
     */
    @Test
    public void producer() throws Exception{
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        //设置 RabbitMQ 地址
        factory.setHost("10.194.101.164");
        //建立到代理服务器到连接
        Connection conn = factory.newConnection();
        //获得信道
        Channel channel = conn.createChannel();
        //声明队列(名称，是否持久化，是否独占本连接,是否自动删除,附加参数 )
        String queueName = "hello-queue";
        channel.queueDeclare(queueName,true,false,false,null);
        //发布消息
        byte[] messageBodyBytes = "测试队列内容3".getBytes("utf-8");
        channel.basicPublish("", queueName, null, messageBodyBytes);
        channel.close();
        conn.close();
    }

    /**
     * 测试消费者
     */
    @Test
    public void consumer() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("10.194.101.164");
        //建立到代理服务器到连接
        Connection conn = factory.newConnection();
        //获得信道
        final Channel channel = conn.createChannel();
        //声明队列
        String queueName = "hello-queue";
        channel.queueDeclare(queueName,true,false,false,null);

        //声明消费的回调函数，即创建消费者
        Consumer callback = new DefaultConsumer(channel) {
            /**
             * @param consumerTag 消费者标签，在channel.basicConsume时候可以指定
             * @param envelope 消息包的内容，可从中获取消息id，消息routingkey，交换机，消息和重传标志(收到消息失败后是否需要重新发送)
             * @param properties  属性信息(生产者的发送时指定)
             * @param body 消息内容
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //路由key
                String routingKey = envelope.getRoutingKey();
                //获取交换机信息
                String exchange = envelope.getExchange();
                //获取消息ID
                long deliveryTag = envelope.getDeliveryTag();
                //获取消息信息
                String message = new String(body,"utf-8");
                System.out.println(
                    "routingKey:" + routingKey +
                    ",exchange:" + exchange +
                    ",deliveryTag:" + deliveryTag +
                    ",message:" + message);
            }
        };
        /**
         * 消息消费
         * 参数1：队列名称
         * 参数2：是否自动应答，true为自动应答[mq接收到回复会删除消息]，设置为false则需要手动应答
         * 参数3：消息接收到后回调
         */
        channel.basicConsume(queueName,true,callback);

        //注意，此处不建议关闭资源，让程序一直处于读取消息(尽量通过钩子函数，保证项目关闭时关闭打开的资源)
        channel.close();
        conn.close();
    }

 /******************************************************* 发布订阅模式 **************************************************************************/

    /**
     * 消息发布者
     */
    @Test
    public void publish() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("10.194.101.164");
        //建立到代理服务器到连接
        Connection conn = factory.newConnection();
        //获得信道
        final Channel channel = conn.createChannel();
        //声明交换机- channel.exchangeDeclare(交换机名字,交换机类型)
        String exchangeName = "fanout_exchange";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT);
        //声明队列 ,声明和绑定一般是初始执行一次即可，也可手动在管理页面创建 (交换机没有存储能力，仅声明交换机不会存储消息，没有队列只有交换机的情况可以看做synchronizeQueue队列使用)
        String queueName = "publish_queue";
        channel.queueDeclare(queueName,true,false,false,null);
        //交换机和队列绑定 ( 队列名, 交换机名, 路由key[广播消息设置为空串] )
        channel.queueBind(queueName,exchangeName,"");
        //连续发10条消息
        for (int i = 0; i < 10; i++) {
            //10、创建消息-String m = xxx
            String message = "hello,欢迎来到深圳黑马！" + i;
            //11、消息发送-（交换机名称[默认Default Exchage], 路由key[简单模式可以传递队列名称], 其它属性, 消息内容）
            channel.basicPublish(exchangeName,"",null,message.getBytes("utf-8"));
        }
        //12、关闭资源-channel.close();connection.close()
        channel.close();
        conn.close();
    }

    /**
     * 消息订阅者
     */
    @Test
    public void subscribe() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("10.194.101.164");
        //建立到代理服务器到连接
        Connection conn = factory.newConnection();
        //获得信道
        final Channel channel = conn.createChannel();
        //声明交换机
        String exchangeName = "fanout_exchange";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT);
        //声明队列
        String queueName = "publish_queue";
        channel.queueDeclare(queueName,true,false,false,null);
        //交换机和队列绑定 ( 队列名, 交换机名, 路由key[广播消息设置为空串] )
        channel.queueBind(queueName,exchangeName,"");

        //声明消费的回调函数，即创建消费者
        Consumer callback = new DefaultConsumer(channel) {
            /**
             * @param consumerTag 消费者标签，在channel.basicConsume时候可以指定
             * @param envelope 消息包的内容，可从中获取消息id，消息routingkey，交换机，消息和重传标志(收到消息失败后是否需要重新发送)
             * @param properties  属性信息(生产者的发送时指定)
             * @param body 消息内容
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //路由key
                String routingKey = envelope.getRoutingKey();
                //获取交换机信息
                String exchange = envelope.getExchange();
                //获取消息ID
                long deliveryTag = envelope.getDeliveryTag();
                //获取消息信息
                String message = new String(body,"utf-8");
                System.out.println(
                        "routingKey:" + routingKey +
                                ",exchange:" + exchange +
                                ",deliveryTag:" + deliveryTag +
                                ",message:" + message);
            }
        };
        /**
         * 消息消费
         * 参数1：队列名称
         * 参数2：是否自动应答，true为自动应答[mq接收到回复会删除消息]，设置为false则需要手动应答
         * 参数3：消息接收到后回调
         */
        while (true){
            channel.basicConsume(queueName,true,callback);
        }

        //注意，此处不建议关闭资源，让程序一直处于读取消息(尽量通过钩子函数，保证项目关闭时关闭打开的资源)
//        channel.close();
//        conn.close();
    }

 /******************************************************* Routing路由模式 **************************************************************************/

    /**
     * 路由生产者（可以先不声明队列，直接声明交换机和routingkey。队列在消费者中声明并和交换器、routingkey绑定，数据也是在队列声明后才正式存储再mq中）
     */
    @Test
    public void routingProducer() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("10.194.101.164");
        //建立到代理服务器到连接
        Connection connection = factory.newConnection();
        //8、创建频道-channel = connection.createChannel()
        Channel channel = connection.createChannel();

        //声明交换机- channel.exchangeDeclare(交换机名字,交换机类型)
        channel.exchangeDeclare("routing_exchange", BuiltinExchangeType.DIRECT);
        //连续发3条消息
        for (int i = 0; i < 3; i++) {
            String routingKey = "";
            //发送消息的时候根据相关逻辑指定相应的routing key。
            switch (i){
                case 0:  //假设i=0，为error消息
                    routingKey = "log.error";
                    break;
                case 1: //假设i=1，为info消息
                    routingKey = "log.info";
                    break;
                case 2: //假设i=2，为warning消息
                    routingKey = "log.warning";
                    break;
            }
            //10、创建消息-String m = xxx
            String message = "hello,欢迎来到深圳黑马！" + i;
            //11、消息发送-channel.basicPublish(交换机[默认Default Exchage],路由key[简单模式可以传递队列名称],消息其它属性,消息内容)
            channel.basicPublish("routing_exchange",routingKey,null,message.getBytes("utf-8"));
        }
        //12、关闭资源-channel.close();connection.close()
        channel.close();
        connection.close();
    }

    /**
     * 路由消费者 (一个队列只能绑定一个exchange，但是可以绑定多个routingkey)
     */
    @Test
    public void routingConsumer() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("10.194.101.164");
        //建立到代理服务器到连接
        Connection connection = factory.newConnection();
        //8、创建频道-channel = connection.createChannel()
        Channel channel = connection.createChannel();
        //9、声明队列-channel.queueDeclare(名称，是否持久化，是否独占本连接,是否自动删除,附加参数)
        channel.queueDeclare("routing_queue1",true,false,false,null);
        //队列绑定交换机-channel.queueBind(队列名, 交换机名, 路由key[广播消息设置为空串])
        channel.queueBind("routing_queue1", "routing_exchange", "log.error");
        channel.queueBind("routing_queue1", "routing_exchange", "log.info");
        channel.queueBind("routing_queue1", "routing_exchange", "log.warning");
        //创建消费者
        Consumer callback = new DefaultConsumer(channel){
            /**
             * @param consumerTag 消费者标签，在channel.basicConsume时候可以指定
             * @param envelope 消息包的内容，可从中获取消息id，消息routingkey，交换机，消息和重传标志(收到消息失败后是否需要重新发送)
             * @param properties  属性信息(生产者的发送时指定)
             * @param body 消息内容
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //路由的key
                String routingKey = envelope.getRoutingKey();
                //获取交换机信息
                String exchange = envelope.getExchange();
                //获取消息ID
                long deliveryTag = envelope.getDeliveryTag();
                //获取消息信息
                String message = new String(body,"utf-8");
                System.out.println(
                        "routingKey:" + routingKey +
                        ",exchange:" + exchange +
                        ",deliveryTag:" + deliveryTag +
                        ",message:" + message);
            }
        };
        /**
         * 消息消费
         * 参数1：队列名称
         * 参数2：是否自动应答，true为自动应答[mq接收到回复会删除消息]，设置为false则需要手动应答
         * 参数3：消息接收到后回调
         */
        while (true){
            channel.basicConsume("routing_queue1",true,callback);
        }

        //注意，此处不建议关闭资源，让程序一直处于读取消息

    }


/******************************************************* topic通配符匹配模式 **************************************************************************/

    /**
     * Topics通配符消息生产者
     */
    @Test
    public void topicProducer() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("10.194.101.164");
        //建立到代理服务器到连接
        Connection connection = factory.newConnection();
        //8、创建频道-channel = connection.createChannel()
        Channel channel = connection.createChannel();

        //声明交换机- channel.exchangeDeclare(交换机名字,交换机类型)
        channel.exchangeDeclare("topic_exchange", BuiltinExchangeType.TOPIC);
        //连续发3条消息
        for (int i = 0; i < 5; i++) {
            String routingKey = "";
            //发送消息的时候根据相关逻辑指定相应的routing key。
            switch (i){
                case 0:  //假设i=0，为error消息
                    routingKey = "log.error";
                    break;
                case 1: //假设i=1，为info消息
                    routingKey = "log.info";
                    break;
                case 2: //假设i=2，为warning消息
                    routingKey = "log.warning";
                    break;
                case 3: //假设i=3，为log.info.add消息
                    routingKey = "log.info.add";
                    break;
                case 4: //假设i=4，为log.info.update消息
                    routingKey = "log.info.update";
                    break;
            }
            //10、创建消息-String m = xxx
            String message = "hello,欢迎来到深圳黑马！" + i;
            //11、消息发送-channel.basicPublish(交换机[默认Default Exchage],路由key[简单模式可以传递队列名称],消息其它属性,消息内容)
            channel.basicPublish("topic_exchange",routingKey,null,message.getBytes("utf-8"));
        }
        //12、关闭资源-channel.close();connection.close()
        channel.close();
        connection.close();
    }

    /**
     * Topics通配符消息消费者
     */
    @Test
    public void topicConsumer() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("10.194.101.164");
        //建立到代理服务器到连接
        Connection connection = factory.newConnection();
        //8、创建频道-channel = connection.createChannel()
        Channel channel = connection.createChannel();
        //9、声明队列-channel.queueDeclare(名称，是否持久化，是否独占本连接,是否自动删除,附加参数)
        channel.queueDeclare("topic_queue1",true,false,false,null);

        //队列绑定路由key
        channel.queueBind("topic_queue1", "topic_exchange", "log.#");
        //创建消费者
        Consumer callback = new DefaultConsumer(channel){
            /**
             * @param consumerTag 消费者标签，在channel.basicConsume时候可以指定
             * @param envelope 消息包的内容，可从中获取消息id，消息routingkey，交换机，消息和重传标志(收到消息失败后是否需要重新发送)
             * @param properties  属性信息(生产者的发送时指定)
             * @param body 消息内容
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //路由的key
                String routingKey = envelope.getRoutingKey();
                //获取交换机信息
                String exchange = envelope.getExchange();
                //获取消息ID
                long deliveryTag = envelope.getDeliveryTag();
                //获取消息信息
                String message = new String(body,"utf-8");
                System.out.println(
                        "routingKey:" + routingKey +
                                ",exchange:" + exchange +
                                ",deliveryTag:" + deliveryTag +
                                ",message:" + message);
            }
        };
        /**
         * 消息消费
         * 参数1：队列名称
         * 参数2：是否自动应答，true为自动应答[mq接收到回复会删除消息]，设置为false则需要手动应答
         * 参数3：消息接收到后回调
         */
        while (true){
            channel.basicConsume("topic_queue1",true,callback);
        }
        //注意，此处不建议关闭资源，让程序一直处于读取消息
    }


    /**********************************************************************  通用工具类 ***********************************************************************************/
    /**
     * 获取连接工具类
     * @return
     */
    public static Connection getConnection(){
        //1、创建链接工厂对象-factory=new ConnectionFactory()
        ConnectionFactory factory = new ConnectionFactory();
        //2、设置RabbitMQ服务主机地址，默认localhost-factory.setHost("localhost")
        factory.setHost("10.194.101.164");
        //3、设置RabbitMQ服务端口，默认-1-factory.setPort(5672)
//        factory.setPort(5672);
        //4、设置虚拟主机名字，默认/-factory.setVirtualHost("szitheima")
//        factory.setVirtualHost("szitheima");
        //5、设置用户连接名，默认guest-factory.setUsername("admin")
        factory.setUsername("guest");
        //6、设置链接密码，默认guest-factory.setPassword("admin")
        factory.setPassword("guest");
        //7、创建链接-connection=factory.newConnection()
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
