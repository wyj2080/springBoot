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

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    void test(){
        for (int i=0;i<50;i++){
            Order order = new Order();
            order.setId(new Long(i));
            rabbitTemplate.convertAndSend("rabbit_test", JSON.toJSONString(order));

        }

    }

    @RabbitListener(queues = "rabbit_test")
    public void ctest(Message message, Channel channel) throws IOException {
        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();
        //接收实体(Object改成发送的对象)
        Order order = JSONObject.parseObject(message.getBody(), Order.class);
        System.out.println(order.getId());
        //设置未确认数窗口，这个频繁变动，会导致消息丢失。如果是自动确认，速度太快来不及消费会用到这个
        channel.basicQos(1);
        if(order.getId() != 4L){
            // 消费确认，这个没写，只会在启动的时候消费一次
            channel.basicAck(tag, false);
        }else{
            channel.basicNack(tag, false, false);
        }

    }

//    @RabbitListener(queues = "队列名称")
//    public void consumerExistsQueue(Message message, Channel channel) throws IOException {
//        MessageProperties properties = message.getMessageProperties();
//        long tag = properties.getDeliveryTag();
//        //接收实体(Object改成发送的对象)
//        Object order = JSONObject.parseObject(message.getBody(), Object.class);
//        // 消费确认，这个没写，只会在启动的时候消费一次，tag。不能多次确认，会重复消费
//        channel.basicAck(tag, false);
//        //这个会循环去消费
//        channel.basicNack(tag, false, true);
//        //和basicAck效果相同，语义不同
//        channel.basicReject(tag, false);
//    }

}
