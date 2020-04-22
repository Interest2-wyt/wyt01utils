package com.test.kafka;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

//<dependency>
//<groupId>org.apache.kafka</groupId>
//<artifactId>kafka_2.11</artifactId>
//<version>0.9.0.0</version>
//</dependency>

public class ProducerConsumer1 {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProducerConsumer1.class);
    /**
     * 1、生产者：发送对象消息 至kafka上,调用json转化为json字符串，应为kafka存储的是String。
     *
     * @param msg
     */
    public void sendMsgToKafka(String msg) {
        System.out.println(msg.length());
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.194.98.45:9092,10.194.98.43:9092,10.194.98.48:9092");//服务器ip:端口号，集群用逗号分隔
        props.put("acks", "0");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 50);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //因为传输格式为ByteArraySerializer，所以处理比较麻烦。如果是字符串就简单很多。
        String data = String.valueOf(new Date());
//        byte[] key = data.getBytes();
//        Producer<byte[], byte[]> producer = new KafkaProducer<>(props);
        Producer<String, String> producer = new KafkaProducer<>(props);
        producer.send(new ProducerRecord<String, String>("WYT_KAFKA_TEMP", data, msg));
        producer.close();
    }

    /**
     * 2、消费者：从kafka上接收对象消息，将json字符串转化为对象，便于获取消息的时候可以使用get方法获取。
     */
    @Test
    public void getMsgFromKafka() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.194.98.29:9092");//服务器ip:端口号，集群用逗号分隔
        props.put("group.id", "test3");  // 该字段用于区分消费者，如果存在多个消费者，group.id不能一样，如果一样，只能保证其中一个消费者消费到数据。
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        Consumer<String,String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("WYT_KAFKA_SEND")); //TOPIC_NAME
        while (true) {
            ConsumerRecords<String,String> records = consumer.poll(100);
            if (records.count() > 0) {
                for (ConsumerRecord<String,String> record : records) {
                    LOGGER.info("从kafka接收到的消息是：" + record.value());
                    System.out.println("从kafka接收到的消息是：" + record.value());
                }
            }
        }
    }

    /**
     * 2、消费者：因为zookeeper管理kafka，所以可以从zk消费kafka信息
     */
    @Test
    public void getMsgByZK(){
        Properties prop = new Properties();
        prop.put("zookeeper.connect","10.194.98.29:2181");
        prop.put("group.id","test3");
        prop.put("zookeeper.session.timeout.ms", "40000");       //zookeeper 与 region server 的链接超时时间
        prop.put("zookeeper.sync.time.ms", "200");
        prop.put("auto.commit.interval.ms", "1000");
        prop.put("serializer.class", "kafka.serializer.StringEncoder");
        ConsumerConfig config = new ConsumerConfig(prop);
        ConsumerConnector consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);

        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put("WYT_KAFKA_SEND", new Integer(1));

        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

        Map<String, List<KafkaStream<String, String>>> consumerMap = consumer.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
        KafkaStream<String, String> stream = consumerMap.get("WYT_KAFKA_SEND").get(0);
        while (true){
            ConsumerIterator<String, String> it = stream.iterator();
            //字节流转换为字符串
            while (it.hasNext()) {  //相当于加了一把锁，一直返回true
                try {
                    System.out.println(it.next().message());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 因为test方法中不能带参数，所以对于生产者新增了一个发生消息的方法，便于测试。
     */
    @Test
    public void sendTest(){
        sendMsgToKafka("123ASJDHAOJSSSSSSSSSSSSSSSSSSSHDAOSDHN 456");
    }

    public static void main(String[] args) {
        ProducerConsumer1 producerConsumer1 = new ProducerConsumer1();
        producerConsumer1.sendTest();
    }

}
