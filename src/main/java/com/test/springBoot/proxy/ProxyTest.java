package com.test.springBoot.proxy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description:
 * @Author: wangyinjia
 * @Date: 2020/4/13
 * @Version: 1.0
 */
@Controller
@RequestMapping("/proxy")
public class ProxyTest {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(String p){
        System.out.println(p);
        return "success";
    }
}
