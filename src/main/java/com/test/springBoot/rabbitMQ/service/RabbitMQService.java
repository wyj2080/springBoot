package com.test.springBoot.rabbitMQ.service;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.*;
import com.test.springBoot.rabbitMQ.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Description: RabbitMQ消息队列service
 * @Author: wangyinjia
 * @Date: 2020/7/4
 * @Version: 1.0
 */
@Service
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
        for (int i = 0; i < 10000; i++) {
            User user = new User(new Long(i),"name"+i,i+18,"长沙福建信德巷108号3001-3004,"+i,"长山中学",new BigDecimal(""+i*1000),
                    "项目经理",new Date(), new Date());
            channel.basicPublish(exchangeName, routingKey,
                    MessageProperties.TEXT_PLAIN, user.toString().getBytes());
//            TimeUnit.SECONDS.sleep(1);
        }
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
