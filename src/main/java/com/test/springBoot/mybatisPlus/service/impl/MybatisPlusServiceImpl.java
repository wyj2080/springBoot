package com.test.springBoot.mybatisPlus.service.impl;

import com.test.springBoot.mybatisPlus.entity.MybatisPlus;
import com.test.springBoot.mybatisPlus.mapper.MybatisPlusMapper;
import com.test.springBoot.mybatisPlus.service.IMybatisPlusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2021-07-27
 */
@Service
public class MybatisPlusServiceImpl extends ServiceImpl<MybatisPlusMapper, MybatisPlus> implements IMybatisPlusService {

    @Override
    public void getAndUpdate(Long id) {
        MybatisPlus mybatisPlus = getById(1420715833606426626L);
        mybatisPlus.setAge(21);
        updateById(mybatisPlus);
    }
}
