package com.test.springBoot.mybatisPlus;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.springBoot.mybatisPlus.mapper.MpDOMapper;
import com.test.springBoot.mybatisPlus.mapper.MpTestDOMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description:
 * @Author: wangyinjia
 * @Date: 2020/3/11
 * @Version: 1.0
 */
@Service
@EnableTransactionManagement(proxyTargetClass=true)//mybatis plus IService需要
public class MpIServiceImp extends ServiceImpl<MpTestDOMapper, GoodsStock190407052437Test> implements MpIService{

}
