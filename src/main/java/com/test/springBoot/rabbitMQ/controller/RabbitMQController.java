package com.test.springBoot.rabbitMQ.controller;

import com.rabbitmq.client.Channel;
import com.test.springBoot.rabbitMQ.service.RabbitMQService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: RabbitMQ消息队列使用方法
 * @Author: wangyinjia
 * @Date: 2020/7/4
 * @Version: 1.0
 */
@Controller
@RequestMapping("/rabbitmq")
public class RabbitMQController {

    @Autowired
    private RabbitMQService rabbitMQService;

    /**
     * 生产者
     */
    @RequestMapping(value = "/producer", method = RequestMethod.GET)
    public void producer() throws Exception {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8);
        list.parallelStream().forEach(pt -> {
            try {
                rabbitMQService.producer();
            }catch (Exception e){
                System.out.println(e);
            }

        });
    }

    /**
     * 消费者
     */
    @RequestMapping(value = "/consumer", method = RequestMethod.GET)
    public void consumer() throws Exception {
        rabbitMQService.consumer();
    }

    @RabbitHandler
    @RabbitListener(queues = "rabbit_test")
    public void consumerExistsQueue(Message message, Channel channel) throws IOException {

        System.out.println("consumerExistsQueue: " + message.toString());
        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();
        channel.basicAck(tag, false);// 消费确认
        channel.basicNack(tag, false, true);//这个有问题
    }

}
