package com.test.springBoot.link.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test.springBoot.link.entity.TestDO;
import com.test.springBoot.link.mapper.LinkDOMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.*;

/**
 * @Description:
 * @Author: wangyinjia
 * @Date: 2019/9/5
 * @company: kiisoo
 * @Version: 1.0
 */
@Controller
@RequestMapping("/link")
@EnableTransactionManagement
public class LinkMySql{

    @Autowired
    LinkDOMapper linkDOMapper;

    @RequestMapping(value = "/mysql", method = RequestMethod.GET)
    public Map<String,Object> sc(){
        Map<String,Object> result = new HashMap<>();
        String username = "name";
        String password = "e10adc3949ba59abbe56e057f20f883e";
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        currentUser.login(token);
        return result;
    }
}
