package com.test.springBoot.redis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public Map<String, Object> send() throws InterruptedException {

        return new HashMap<>();
    }

    @RequestMapping(value = "/receive", method = RequestMethod.GET)
    public Map<String, Object> receive() throws InterruptedException {

        return new HashMap<>();
    }

}
