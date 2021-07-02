package com.test.springBoot.redis.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * @Description: 连接redis
 * @Author: wangyinjia
 * @Date: 2019/11/14
 * @Version: 1.0
 */
@Controller
@RequestMapping("/redis")
public class LinkRedis {

    @Value("${redis.servers}")
    private String servers;

    @Value("${redis.password}")
    private String password;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public Map<String, Object> send() throws InterruptedException {
        //连接 Redis 服务
        Jedis jedis = new Jedis("192.168.0.100", 6379);
        jedis.auth(password);
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        jedis.set("username","dapeng");
        jedis.set("password","123456");
        jedis.close();
        System.out.println("end");
        return new HashMap<>();
    }

    @RequestMapping(value = "/receive", method = RequestMethod.GET)
    public Map<String, Object> receive() throws InterruptedException {
        Jedis jedis = new Jedis("192.168.0.100", 6379);
        System.out.println("username:" + jedis.get("username") + ",password:" + jedis.get("password"));
        System.out.println(jedis.get("unknowKey"));
        System.out.println(jedis.get("unknowKey") == null);
        return new HashMap<>();
    }

    @RequestMapping(value = "/performance", method = RequestMethod.GET)
    public Map<String, Object> performance(){

        Integer num = 10000;
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<num;i++){
            list.add(i);
        }
//        Date s = new Date();
//        list.forEach(a->{
//            Date tmp1 = new Date();
//            Jedis jedis = new Jedis(servers);
//            String status = jedis.set(""+a,""+a);
//            jedis.close();
//            Date tmp2 = new Date();
//            System.out.println(status+":"+(tmp2.getTime()-tmp1.getTime())+"ms");
//        });
//        Date e = new Date();
//        System.out.println(num+"条串行插入完成耗时："+(e.getTime()-s.getTime()));
        Date c = new Date();
        Vector<Integer> errorList = new Vector<>();
        list.parallelStream().forEach(a->{
            try{
                Date tmp1 = new Date();
                Jedis jedisPara = new Jedis(servers);
                jedisPara.set(""+a,""+a);
                jedisPara.close();
                Date tmp2 = new Date();
                System.out.println(a+":"+(tmp2.getTime()-tmp1.getTime())+"ms");
            }catch (Exception e){
                System.out.println("发送出错=========================>，number:"+a);
                errorList.add(a);
            }finally {

            }

        });
        Date d = new Date();
        System.out.println(num+"条并行插入完成耗时："+(d.getTime()-c.getTime()));
        System.out.println("发送出错list:"+errorList.toString());
        System.out.println("综上成功："+(num-errorList.size())+"条,出错："+errorList.size()+"条");
        return new HashMap<>();
    }

}
