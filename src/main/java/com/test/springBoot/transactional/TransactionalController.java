package com.test.springBoot.transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test.springBoot.transactional.entity.BaseShopDO;
import com.test.springBoot.transactional.mapper.ShopDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description:
 * @Author: wangyinjia
 * @Date: 2020/3/19
 * @Version: 1.0
 */
@Controller
@RequestMapping("/transactional")
public class TransactionalController {

    @Autowired
    private TransactionalService transactionalService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test(){
//        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,0);
//        list.parallelStream().forEach(a -> {
            transactionalService.testA();
//        });
    }



}
