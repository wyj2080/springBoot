package com.test.springBoot.order.controller;

import com.test.springBoot.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 订单
 * @Author: wangyinjia
 * @Date: 2020/7/9
 * @Version: 1.0
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 生产订单
     */
    @RequestMapping(value = "/producer", method = RequestMethod.GET)
    public void producer() throws Exception {
        orderService.producer();
    }

    /**
     * url录入订单
     */
    @RequestMapping(value = "/in", method = RequestMethod.GET)
    public void in() throws Exception {
        orderService.in();
    }

    /**
     * url查询订单
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public void get() throws Exception {
        orderService.get();
    }

}
