package com.test.kafka;

import com.test.properties.PropertiesUitl;
import kafka.admin.AdminUtils;
import kafka.server.ConfigType;
import kafka.utils.ZkUtils;
import org.apache.kafka.common.security.JaasUtils;
import org.junit.Test;
import scala.collection.JavaConversions;

import java.util.List;
import java.util.Properties;

/**
 * demo中的kafka版本号是kafka_2.10', version: '0.10.2.0'。但是本工程中用的不是这个版本，如果该方法走不通，可以更换版本
 */
public class TopicManagerTest {


    @Test
    public void createTopic(){
        ZkUtils zkUtils = ZkUtils.apply("10.194.98.45:2181",30000,30000, JaasUtils.isZkSecurityEnabled());
        //创建一个单分区单副本名为kafkaDataExtract的topic
//        AdminUtils.createTopic(zkUtils,"kafkaDataExtract",1,1,new Properties());
        AdminUtils.createTopic(zkUtils, "WYT_KAFKA_TEMP", 1, 1, new Properties());
        zkUtils.close();
    }
    @Test
    public void deleteTopic(){
        ZkUtils zkUtils = ZkUtils.apply("10.194.98.29:2181",30000,30000, JaasUtils.isZkSecurityEnabled());
        //删除topic为kafkaDataExtract主题
        AdminUtils.deleteTopic(zkUtils,"WYT_KAFKA_EXTRACT");
        zkUtils.close();
    }
    @Test
    public void searchTopic(){
        ZkUtils zkUtils = ZkUtils.apply("10.194.98.29:2181",30000,30000, JaasUtils.isZkSecurityEnabled());
        //1、获取所有topic 的属性
        List<String> topics = JavaConversions.seqAsJavaList(zkUtils.getAllTopics());
        //2、罗列所有topic
        topics.forEach(System.out::println);
        //3、过滤是否有目标topic
        boolean hasTargetTopic = false;
        for (String topic:topics){
            if ("WYT_KAFKA_SEND".equals(topic)){
                hasTargetTopic = true;
                System.out.println("\n 含有目标主题："+topic +"\n");
            }
        }
        if (hasTargetTopic==false){
            System.out.println("\n 不含有目标主题 \n");
        }

        zkUtils.close();
    }

    @Test
    public void updateTopic(){
        ZkUtils zkUtils = ZkUtils.apply("10.194.98.29:9092",30000,30000, JaasUtils.isZkSecurityEnabled());
        //获取topic 的属性
        Properties props = AdminUtils.fetchEntityConfig(zkUtils, ConfigType.Topic(),"kafkaDataExtract");
        //增加topic级别属性
        props.put("min.cleanable.dirty.ratio", "0.3");
        //删除topic级别属性
        props.remove("max.message.bytes");
        //修改topic 'kafkaDataExtract'的属性
        AdminUtils.changeTopicConfig(zkUtils, "kafkaDataExtract", props);
        zkUtils.close();
        PropertiesUitl.getString("zookeeper");
    }


}
