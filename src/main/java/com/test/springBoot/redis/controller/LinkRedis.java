package com.test.springBoot.redis.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 连接redis
 * @Author: wangyinjia
 * @Date: 2019/11/14
 * @Version: 1.0
 */
@Controller
@RequestMapping("/link/redis")
public class LinkRedis {

    @Value("${redis.servers}")
    private String servers;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public Map<String, Object> send() throws InterruptedException {
        //连接 Redis 服务
        Jedis jedis = new Jedis(servers);
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        jedis.set("username","dapeng");
        jedis.set("password","123456");
        jedis.close();
        return new HashMap<>();
    }

    @RequestMapping(value = "/receive", method = RequestMethod.GET)
    public Map<String, Object> receive() throws InterruptedException {
        Jedis jedis = new Jedis(servers);
        System.out.println("username:" + jedis.get("username") + ",password:" + jedis.get("password"));
        System.out.println(jedis.get("unknowKey"));
        System.out.println(jedis.get("unknowKey") == null);
        return new HashMap<>();
    }

}
