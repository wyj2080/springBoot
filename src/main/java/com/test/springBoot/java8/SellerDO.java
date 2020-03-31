package com.test.springBoot.java8;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 导购DO
 * @Author: wangyinjia
 * @Date: 2020/3/16
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
public class SellerDO {
    //id
    Long id;
    //姓名
    String name;
    //年龄
    Integer age;
    //销售额
    BigDecimal amount;
}
