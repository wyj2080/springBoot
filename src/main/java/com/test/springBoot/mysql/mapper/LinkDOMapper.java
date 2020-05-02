package com.test.springBoot.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.springBoot.mysql.entity.AccountDO;

import java.util.Map;

/**
 * @Description:
 * @Author: wangyinjia
 * @Date: 2019/9/6
 * @Version: 1.0
 */
public interface LinkDOMapper extends BaseMapper<AccountDO> {

    Map<String,Object> selectShop();
}
