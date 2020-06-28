package com.test.springBoot.mybatisPlus.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test.springBoot.mybatisPlus.entity.GoodsStock190407052437;
import com.test.springBoot.mybatisPlus.entity.GoodsStock190407052437Test;
import com.test.springBoot.mybatisPlus.mapper.MpDOMapper;
import com.test.springBoot.mybatisPlus.mapper.MpTestDOMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: wangyinjia
 * @Date: 2020/3/11
 * @Version: 1.0
 */
@Service
public class MpService {
    @Autowired
    private MpTestDOMapper mpTestDOMapper;
    @Autowired
    private MpDOMapper mpDOMapper;
    @Autowired
    private MpIServiceImp mpIServiceImp;
    @Transactional
    public List<GoodsStock190407052437> test(){
        QueryWrapper<GoodsStock190407052437> wrapper = new QueryWrapper<>();
        List<GoodsStock190407052437> list = mpDOMapper.selectList(wrapper);
        List<GoodsStock190407052437Test> targetList = new ArrayList<>();
        List<GoodsStock190407052437Test> result = new ArrayList<>();
        list.forEach(DO -> {
            GoodsStock190407052437Test testDO = new GoodsStock190407052437Test();
            try {
                BeanUtils.copyProperties(testDO, DO);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            targetList.add(testDO);
        });
        System.out.println(targetList.size());
        for(int i=4000;i<8000;i++){
            result.add(targetList.get(i));
        }
        Date s = new Date();
        for(int i=0;i<4000;i++){
            mpTestDOMapper.insert(targetList.get(i));
        }
        Date e = new Date();
        System.out.println(e.getTime()-s.getTime());
        Date s1 = new Date();
        mpIServiceImp.saveBatch(result);
        Date e1 = new Date();
        System.out.println(e1.getTime()-s1.getTime());
        return list;
    }
}
