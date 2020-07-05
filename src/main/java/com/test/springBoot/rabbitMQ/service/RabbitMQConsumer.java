package com.test.springBoot.rabbitMQ.service;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class RabbitMQConsumer extends DefaultConsumer {

    public RabbitMQConsumer(Channel channel) {
        super(channel);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body){
        //consumerTag: 内部生成的消费标签  properties: 消息属性  body: 消息内容
        System.out.println("-----------consume message----------");
        System.out.println("consumerTag: " + consumerTag);
        //envelope包含属性：deliveryTag(标签), redeliver, exchange, routingKey
        //redeliver是一个标记，如果设为true，表示消息之前可能已经投递过了，现在是重新投递消息到监听队列的消费者
        System.out.println("envelope: " + envelope);
        System.out.println("properties: " + properties);
        System.out.println("body: " + new String(body));
    }
}
