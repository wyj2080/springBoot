package com.test.springBoot.beanUtil.controller;

import com.test.springBoot.beanUtil.entity.BeanDO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 对类内属性做操作
 * 应用场景：要对每个属性重复的动作，比如对每个属性排序，再分级
 * @Author: wangyinjia
 * @Date: 2019/11/28
 * @Version: 1.0
 */
@Controller
@RequestMapping("/bean")
public class BeanUtilController{

    @RequestMapping(value = "/output", method = RequestMethod.GET)
    public void getBean(){
        //属性名list
        List<String> paramList = Arrays.asList("id","age","name","volAmount");
        List<BeanDO> beanList = new ArrayList<>();
        BeanDO beanDO = new BeanDO();
        beanDO.setId(1L);
        beanDO.setAge(18);
        beanDO.setName("我是谁");
        beanDO.setVolAmount(new BigDecimal("1238"));
        beanList.add(beanDO);
        //输出对象值
        paramList.forEach(param ->{
            beanList.forEach(bean -> {
                String value = null;
                try {
                    value = BeanUtils.getProperty(bean, param);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(value);
            });
        });
    }

}
