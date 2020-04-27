package com.test.springBoot.transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test.springBoot.transactional.entity.BaseShopDO;
import com.test.springBoot.transactional.mapper.ShopDOMapper;
import org.apache.commons.collections.CollectionUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: wangyinjia
 * @Date: 2020/3/27
 * @Version: 1.0
 */
@Service
public class TransactionalService {

    /**
     * 店铺mapper
     */
    @Autowired
    private ShopDOMapper shopDOMapper;

    public void testA(){
        test();
    }

    //其他类里transactionalService.update()生效
    @Transactional
    Integer update(){
        BaseShopDO shopDO = new BaseShopDO();
        shopDO.setShopId(269438L);
        shopDO.setShopName("我是书屋1");
        Integer result = shopDOMapper.updateById(shopDO);

        String s = "a";
        int b = Integer.parseInt(s);

        return 1;
    }
    //这样不生效
    private void test(){
        update();
    }




}
