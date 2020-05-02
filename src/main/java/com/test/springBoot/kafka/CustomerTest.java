package com.test.springBoot.kafka;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Properties;

/**
 * @Description: 消费者
 * @Author: wangyinjia
 * @Date: 2019/10/28
 * @Version: 1.0
 */
//@Service
public class CustomerTest {
    //连接参数
    private Properties properties;

    //消费者
    private KafkaConsumer<String, String> kafkaConsumer;
    /**
     * 消费组构造
     * @param servers 服务器
     */
    CustomerTest(@Value("${kafka.servers}") String servers){
        properties = new Properties();
        properties.put("bootstrap.servers", servers);
        properties.put("group.id", "group-1");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "earliest");
        properties.put("session.timeout.ms", "30000");
        properties.put("max.poll.records","100000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaConsumer = new KafkaConsumer<>(properties);
    }

    /**
     * 接收消息
     * @param size 条数
     * @throws InterruptedException
     */
    public void receive(Integer size) throws InterruptedException {
        kafkaConsumer.subscribe(Arrays.asList("jcftest","joker","test"));
        Integer num = 0;
        if(size != null){
            String last = "";
            for(int i = 0;i<size;i++){
                ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    last = "topic = "+record.topic()+", offset = "+record.offset()+", value = "+record.value()+", partion = "+record.partition();
//                    System.out.printf("topic = %s, offset = %d, value = %s, partion = %s", record.topic(), record.offset(), record.value(), record.partition());
//                    System.out.println("====================================>");
                    num++;
                    if(num%100000 == 0){
                        System.out.println(num);
                    }
                }
            }
            System.out.println(num+"条");
            System.out.println(last);
        }else{
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
//                    System.out.printf("topic = %s, offset = %d, value = %s, partion = %s", record.topic(), record.offset(), record.value(), record.partition());
//                    System.out.println("====================================>");
                    num++;
                    if(num%100000 == 0){
                        System.out.println(num);
                    }
                }
            }
        }

    }

    public void close(){
        kafkaConsumer.close();
    }
}
