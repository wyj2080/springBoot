package com.test.springBoot.rabbitMQ.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    @Value("${rabbitmq.servers}")
    private String servers;
    @Value("${rabbitmq.port}")
    private Integer port;
    @Value("${rabbitmq.username}")
    private String username;
    @Value("${rabbitmq.password}")
    private String password;

    public void producer() throws Exception {
        //交换器
        String exchangeName = "exchange.test.topic";
        //路由键
        String routingKey = "route.test";
        //RabbitMQ服务端配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(servers);
        connectionFactory.setPort(port);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        //连接
        Connection connection = connectionFactory.newConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //调用basicPublish循环发送10条消息 , 每条消息间隔1秒
        for (int i = 0; i < 10; i++) {
            channel.basicPublish(exchangeName, routingKey,
                    MessageProperties.TEXT_PLAIN, ("测试消息" + i).getBytes());
            TimeUnit.SECONDS.sleep(1);
        }
        channel.close();
        connection.close();
    }

    public void consumer(){

    }

}
