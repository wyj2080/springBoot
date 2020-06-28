package com.test.springBoot.mybatisPlus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: wangyinjia
 * @Date: 2020/6/28
 * @Version: 1.0
 */
@RestController
@RequestMapping("/mp")
public class MyBatisPlusController {

    @GetMapping("lambda")
    public void lambdaTest(){
        System.out.println("lambda");
    }
}
