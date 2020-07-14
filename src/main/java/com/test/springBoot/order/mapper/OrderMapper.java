package com.test.springBoot.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.springBoot.order.entity.Order;
import org.springframework.stereotype.Repository;

/**
 * @Description: 订单mapper
 * @Author: wangyinjia
 * @Date: 2020/7/9
 * @Version: 1.0
 */
@Repository
public interface OrderMapper extends BaseMapper<Order> {

}
