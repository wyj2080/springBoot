package com.test.springBoot.link.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.springBoot.link.entity.AccountDO;

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
