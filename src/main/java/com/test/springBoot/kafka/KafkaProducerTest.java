
package com.test.springBoot.kafka;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;

//@Service
public class KafkaProducerTest {

    //连接参数
    Properties props;
    //生产者
    Producer<String, String> producer;

    /**
     * 生产者构造
     * @param servers 服务器
     */
    KafkaProducerTest(@Value("${kafka.servers}") String servers){
        props = new Properties();
        props.put("bootstrap.servers", servers);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }

    /**
     * 发送消息
     * @param topic 主题
     * @param msg 消息
     */
    public void send(String topic, String msg){
        Date s;
        Date e;
        //一百万条用时
        s = new Date();
        for(int i=0;i<500000;i++){
            producer.send(new ProducerRecord<>(topic, msg));
        }
        e = new Date();
        System.out.println(e.getTime()-s.getTime()+"五十万用时");
        //二百万条单线程用时
        s = new Date();
        List<Integer> threadList = Arrays.asList(1,2);
        threadList.forEach(a->{
            for(int i=0;i<250000;i++){
                producer.send(new ProducerRecord<>(topic, msg));
            }
        });
        e = new Date();
        System.out.println(e.getTime()-s.getTime()+"五十万单线程用时");
        //二百万条多线程用时
        s = new Date();
        List<Integer> threadList2 = Arrays.asList(1,2);
        threadList2.parallelStream().forEach(a->{
            for(int i=0;i<250000;i++){
                producer.send(new ProducerRecord<>(topic, msg));
            }
        });
        e = new Date();
        System.out.println(e.getTime()-s.getTime()+"五十万多线程用时");

    }

    public void close(){
        producer.close();
    }
}

