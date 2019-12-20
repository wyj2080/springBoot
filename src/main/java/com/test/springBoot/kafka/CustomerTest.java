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
@Service
public class CustomerTest {
        //服务器
    @Value("${kafka.servers}")
    private String servers;
    public void receive() throws InterruptedException {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", servers);
        properties.put("group.id", "group-1");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "earliest");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList("jcftest","joker","test"));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("topic = %s, offset = %d, value = %s, partion = %s", record.topic(), record.offset(), record.value(), record.partition());
                System.out.println("====================================>");
            }
        }
//        kafkaConsumer.close();

    }
}
