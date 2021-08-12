package com.test.springBoot.mybatisPlus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.springBoot.mybatisPlus.entity.MybatisPlus;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2021-07-27
 */
public interface IMybatisPlusService extends IService<MybatisPlus> {

    void getAndUpdate(Long id);



}
