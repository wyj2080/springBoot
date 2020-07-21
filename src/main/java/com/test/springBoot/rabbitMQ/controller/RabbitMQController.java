package com.test.springBoot.rabbitMQ.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.test.springBoot.order.entity.Order;
import com.test.springBoot.order.mapper.OrderMapper;
import com.test.springBoot.rabbitMQ.service.RabbitMQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
@Slf4j
@Controller
@RequestMapping("/rabbitmq")
public class RabbitMQController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 生产者
     */
    @RequestMapping(value = "/producer", method = RequestMethod.GET)
    public void producer() throws Exception {
        //队列
        rabbitTemplate.convertAndSend("rabbit_test", "消息一条，爱要不要");
        //交换机和路由键
        rabbitTemplate.convertAndSend("exchange.test.topic", "route.test", "消息一条，爱要不要");
        //发送实体
        rabbitTemplate.convertAndSend("队列名", JSON.toJSONString("对象实体"));

    }

    @RabbitListener(queues = "rabbit_test")
    public void consumerExistsQueue(Message message, Channel channel) throws IOException {
        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();
        System.out.println(message);
        channel.basicAck(tag, false);
        //接收实体(Object改成发送的对象)
        Object order = JSONObject.parseObject(message.getBody(), Object.class);
        // 消费确认，这个没写，只会在启动的时候消费一次
        channel.basicAck(tag, false);
        //这个会循环去消费
        channel.basicNack(tag, false, true);

    }

}
