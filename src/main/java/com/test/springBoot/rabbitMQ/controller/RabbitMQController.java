package com.test.springBoot.rabbitMQ.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.test.springBoot.order.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

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
    /**
     * 这个类会自动读取配置信息里的服务器信息
     */
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
        //发送确认(消息→exchange)
        CorrelationData correlationData = new CorrelationData("可以放实体id");
        rabbitTemplate.convertAndSend("rabbit_test", (Object) JSON.toJSONString("对象实体"), correlationData);
        //发送确认(exchange→queue) 得有exchange
        rabbitTemplate.convertAndSend("exchange.test.topic","route.test", (Object) JSON.toJSONString("对象实体"), correlationData);
    }

    /**
     * 延时消息，队列里数量不变，但消费不到到期的
     */
    @RequestMapping(value = "/delay", method = RequestMethod.GET)
    void delay() throws Exception {
        Order order = new Order();
        order.setId(new Long(1));
        CorrelationData correlationData = new CorrelationData(order.getId().toString());

        MessageProperties messageProperties = new MessageProperties();
        byte[] body = JSON.toJSONString(order).getBytes();
        messageProperties.setContentType("json");
        //设置消息的过期时间
        messageProperties.setExpiration("5000");
        Message message = new Message(body,messageProperties);

        rabbitTemplate.convertAndSend("exchange.test.topic","route.test", message, correlationData);
    }

    //消息发送确认，实现RabbitTemplate.ConfirmCallback
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    void test() throws Exception {
        for (int i=0;i<2;i++){

            Order order = new Order();
            order.setId(new Long(i));
            CorrelationData correlationData = new CorrelationData(order.getId().toString());

            MessageProperties messageProperties = new MessageProperties();
            byte[] body = JSON.toJSONString(order).getBytes();
            messageProperties.setContentType("json");
            //设置消息的过期时间
            messageProperties.setExpiration("5000");
            Message message = new Message(body,messageProperties);

            rabbitTemplate.convertAndSend("exchange.test.topic","route.test", message, correlationData);
        }

    }

    /**
     * 自动创建队列
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "rabbit_test", durable = "true", autoDelete = "false", arguments = {}),
            exchange = @Exchange(value = "rabbit_test.exchange", type = ExchangeTypes.DIRECT),
            key = "rabbit_test_view"))
    public void ctest(Message message, Channel channel) throws IOException {
        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();
        //接收实体(Object改成发送的对象)
        Order order = JSONObject.parseObject(message.getBody(), Order.class);
        System.out.println(order.toString());
        //设置未确认数窗口，这个频繁变动，会导致消息丢失。如果是自动确认，速度太快来不及消费会用到这个
        channel.basicQos(200);
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
//        //设置未确认数窗口，这个频繁变动，会导致消息丢失。如果是自动确认，速度太快来不及消费会用到这个
//        channel.basicQos(200);
//        // 消费确认，这个没写，只会在启动的时候消费一次，tag。不能多次确认，会重复消费
//        channel.basicAck(tag, false);
//        //这个会循环去消费
//        channel.basicNack(tag, false, true);
//        //和basicAck效果相同，语义不同
//        channel.basicReject(tag, false);
//    }

}
