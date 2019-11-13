package com.test.springBoot.link.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.springBoot.link.entity.TestDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: wangyinjia
 * @Date: 2019/9/6
 * @Company: kiisoo
 * @Version: 1.0
 */
public interface LinkDOMapper extends BaseMapper<TestDO> {

    Map<String,Object> selectShop();
}
