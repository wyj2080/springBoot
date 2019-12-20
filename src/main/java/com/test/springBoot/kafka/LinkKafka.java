package com.test.springBoot.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 连接kafka
 * @Author: wangyinjia
 * @Date: 2019/10/28
 * @Version: 1.0
 */
@Controller
@RequestMapping("/link/kafka")
public class LinkKafka {
    @Autowired
    KafkaProducerTest producerTest;
    @Autowired
    CustomerTest customerTest;
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public Map<String, Object> send() throws InterruptedException {
        producerTest.send("test","消息1");
//        CustomerTest customerTest = new CustomerTest();
//        customerTest.receive();
//        customerTest.receive();
        return new HashMap<>();
    }

    @RequestMapping(value = "/receive", method = RequestMethod.GET)
    public Map<String, Object> receive() throws InterruptedException {
        customerTest.receive();
        return new HashMap<>();
    }


}
