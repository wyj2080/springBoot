package com.test.springBoot.link.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.test.springBoot.link.entity.AccountDO;
import com.test.springBoot.link.mapper.LinkDOMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.math.BigDecimal;
import java.util.*;

/**
 * @Description:
 * @Author: wangyinjia
 * @Date: 2019/9/5
 * @Version: 1.0
 */
@Controller
@RequestMapping("/link")
@EnableTransactionManagement
public class LinkMySql{

    @Autowired
    LinkDOMapper linkDOMapper;

    /**
     * 连接mysql
     * @param pageNum 页数
     * @param pageSize 每页大小
     * @return
     */
    @RequestMapping(value = "/mysql", method = RequestMethod.GET)
    public Map<String,Object> sc(@RequestParam(value="pageNum",required=false,defaultValue="1") Integer pageNum,
                                 @RequestParam(value="pageSize",required=false,defaultValue="10") Integer pageSize){
        Map<String,Object> result = new HashMap<>();
        //2个0就是不分页
        pageNum = 0;
        pageSize = 0;
        //物理分页，需引入maven
        QueryWrapper<AccountDO> wrapper = new QueryWrapper<>();
        wrapper.eq("id",5523);
        PageHelper.startPage(pageNum,pageSize);
        Page<AccountDO> list = (Page<AccountDO>)linkDOMapper.selectList(wrapper);
        System.out.println(list.getPages());
        list.forEach(listDO -> System.out.println(listDO.toString()));
        return result;
    }


    @RequestMapping(value = "/shiro", method = RequestMethod.GET)
    public Map<String,Object> shiro(){
        Map<String,Object> result = new HashMap<>();
        String username = "name";
        String password = "e10adc3949ba59abbe56e057f20f883e";
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        currentUser.login(token);
        return result;
    }
}
