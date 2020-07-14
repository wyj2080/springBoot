package com.test.springBoot.rabbitMQ.service;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.*;
import com.test.springBoot.order.entity.Order;
import com.test.springBoot.order.service.OrderService;
import com.test.springBoot.rabbitMQ.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description: RabbitMQ消息队列service
 * @Author: wangyinjia
 * @Date: 2020/7/4
 * @Version: 1.0
 */
@Service
@Slf4j
public class RabbitMQService {
    /**服务器地址端口*/
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private Integer port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Autowired
    private OrderService orderService;

    public void producer() throws Exception {
        //交换器
        String exchangeName = "exchange.test.topic";
        //路由键
        String routingKey = "route.test";
        //RabbitMQ服务端配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        //连接
        Connection connection = connectionFactory.newConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //调用basicPublish循环发送10条消息 , 每条消息间隔1秒
        List<Order> orderList = orderService.producer();
        orderList.forEach(order -> {
            try {
                channel.basicPublish(exchangeName, routingKey,
                        MessageProperties.TEXT_PLAIN, order.toString().getBytes());
            } catch (IOException e) {
                log.error("发生订单到rabbitmq失败",e);
            }
        });
        channel.close();
        connection.close();
    }

    public void consumer() throws Exception{
        //队列
        String queueName = "rabbit_test";
        //交换器
        String exchangeName = "exchange.test.topic";
        //路由键
        String routingKey = "route.test";
        //RabbitMQ服务端配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        //连接
        Connection connection = connectionFactory.newConnection();
        //创建通道
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(exchangeName, "topic", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);
        //5 设置channel，使用自定义消费者
        channel.basicConsume(queueName, true, new RabbitMQConsumer(channel));

        channel.close();
        connection.close();

    }

}
