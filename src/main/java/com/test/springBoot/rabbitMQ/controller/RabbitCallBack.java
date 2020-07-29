package com.test.springBoot.rabbitMQ.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

/**
 * @Description: 发布确认回调
 * @Author: wangyinjia
 * @Date: 2020/7/23
 * @Version: 1.0
 */
@Controller
public class RabbitCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        //发送确认，初始化设置
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
        //强制委托模式
        rabbitTemplate.setMandatory(true);
    }

    /**
     * 发送到exchange回调
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String curse) {
        System.out.println("标识id："+correlationData);
        if(!ack){
            //处理业务
        }
    }

    /**
     * exchange→queue失败才回调
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println(message);
        System.out.println(replyCode);
        System.out.println(replyText);
        System.out.println(exchange);
        System.out.println(routingKey);
    }
}
