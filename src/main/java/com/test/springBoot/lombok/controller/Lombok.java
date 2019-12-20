package com.test.springBoot.lombok.controller;

import com.test.springBoot.lombok.entity.LombokDO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: lombok使用
 * @Author: wangyinjia
 * @Date: 2019/12/13
 * @Version: 1.0
 */
@Controller
@RequestMapping("/lombok")
public class Lombok {

    /**
     * 暂行不通
     */
    @RequestMapping(value = "/sync", method = RequestMethod.GET)
    public void test(){
        LombokDO lombokDO = new LombokDO();
        lombokDO.setId(0L);
        List<Long> list = new ArrayList<>();
        for(Long i = 0L;i<10000L;i++){
            list.add(i);
        }
        list.forEach(num -> lombokDO.setId(lombokDO.getId() + num));
        System.out.println(lombokDO.getId());

        lombokDO.setId(0L);
        list.parallelStream().forEach(num -> lombokDO.setId(lombokDO.getId() + num));
        System.out.println(lombokDO.getId());
    }
}
