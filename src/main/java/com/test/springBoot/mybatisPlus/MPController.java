package com.test.springBoot.mybatisPlus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description:
 * @Author: wangyinjia
 * @Date: 2020/3/11
 * @Company: kiisoo
 * @Version: 1.0
 */
@Controller
@RequestMapping("/mp")
public class MPController {

    @Autowired
    private MpService mpService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public void test(){
        mpService.test();
    }

}
