package com.test.springBoot.java11;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wangyinjia
 * @description java 11 controller
 * @date 2021/6/18
 */
@Api(tags = "Java11 控制器")
@RestController
@RequestMapping("/java11")
public class Java11Controller {

    @GetMapping("/list")
    public String findList(){
        var a = "123a";
        return a;
    }

}
