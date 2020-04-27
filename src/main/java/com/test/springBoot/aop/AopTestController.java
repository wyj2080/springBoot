package com.test.springBoot.aop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description:
 * @Author: wangyinjia
 * @Date: 2020/4/10
 * @Version: 1.0
 */
@Controller
@RequestMapping("/aop")
public class AopTestController {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(String p){
        System.out.println(p);
        return "success";
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String atest(){
        System.out.println("test2");
        return "success";
    }
}
