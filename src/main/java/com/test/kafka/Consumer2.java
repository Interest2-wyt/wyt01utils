package com.test.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author : wangyongtao
 * @Description : kafka消费类
 * @Date : 2020/11/22 0022 13:51
 **/
public class Consumer2 {
//    spring.kafka.bootstrap-servers=10.
//    spring.kafka.consumer.group-id=TRAFFIC_STATISTIC_UPLOAD_WX
//    spring.kafka.consumer.enable-auto-commit=true
//    spring.kafka.consumer.auto-commit-interval=1000
//    spring.kafka.consumer.auto-offset-reset=earliest
//    spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
//    spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer
//            <dependency>
//			<groupId>org.springframework.kafka</groupId>
//			<artifactId>spring-kafka</artifactId>
//			<version>2.1.4.RELEASE</version>
//		</dependency>
//    @Component
//    public class TrafficAlarmConsumerTask {
//        private static Logger log = LoggerFactory.getLogger(TrafficAlarmConsumerTask.class);
//        @Autowired
//        private DataUploadCustomer dataUploadCustomer;
//        @Autowired
//        private RecDataUploadCustomer recDataUploadCustomer;
//
//        @KafkaListener(topics = {"BAYONET_VEHICLEALARM_JSON_TOPIC", "BAYONET_VEHICLEPASS_JSON_TOPIC"})
//        public void listen(ConsumerRecord<?, ?> record) throws Exception {
//            try {
//                Map<String, Object> keyParam = new HashMap<String, Object>();
//                keyParam.put("kData", record.value());
//                if ("BAYONET_VEHICLEALARM_JSON_TOPIC".equals(record.topic())) {
//                    dataUploadCustomer.add(keyParam);
//                } else if ("BAYONET_VEHICLEPASS_JSON_TOPIC".equals(record.topic())) {
//                    recDataUploadCustomer.add(keyParam);
//                }
//            } catch (Exception e) {
//                log.error("消费Kafka异常", e);
//                e.printStackTrace();
//            }
//        }
//    }
}
