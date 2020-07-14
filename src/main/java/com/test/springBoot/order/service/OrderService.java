package com.test.springBoot.order.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test.springBoot.order.entity.Order;
import com.test.springBoot.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 订单service
 * @Author: wangyinjia
 * @Date: 2020/7/9
 * @Version: 1.0
 */
@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public List<Order> producer(){
        int num = 0;
        List<Order> orderList = new ArrayList<>();
        while (true){
            num++;
            Order order = new Order(null, new Long(num), 0, new Date(), new Date(), new Long(num+10000), "花里胡哨谁谁谁"+num, "北京朝阳区明杏路小花园18栋3008号-"+num+"号", "天津市东大先名利花园45号楼"+num+"室", new BigDecimal(num*10), new BigDecimal(num*5));
            orderList.add(order);
            if (num % 1000 == 0)System.out.println("订单生成：" + num/100 + "%");
            if (num == 10000)break;
        }
        System.out.println("---------------订单生成完毕---------------");
        return orderList;
    }

    public void consumer(List<Order> orderList){
        AtomicInteger num = new AtomicInteger(0);
        orderList.parallelStream().forEach(order -> {
            num.updateAndGet(v -> v+1);
            orderMapper.insert(order);
            if (num.get() % 1000 == 0)System.out.println("订单录入：" + num.get()/100 + "%");
        });
        System.out.println("---------------订单消费完毕---------------");
    }

    public void in(){
        int num =9000;
        Order order = new Order(null, new Long(num), 0, new Date(), new Date(), new Long(num+10000), "花里胡哨谁谁谁"+num, "北京朝阳区明杏路小花园18栋3008号-"+num+"号", "天津市东大先名利花园45号楼"+num+"室", new BigDecimal(num*10), new BigDecimal(num*5));
        orderMapper.insert(order);
        System.out.println("url录入成功");
    }

    public void get(){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Order::getUserName, "花里胡哨谁谁谁9000");
        List<Order> order = orderMapper.selectList(wrapper);
        System.out.println("查询订单:"+order.toString());
    }
}
