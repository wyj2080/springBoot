package com.test.springBoot.beanUtil.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: wangyinjia
 * @Date: 2019/11/28
 * @Company: kiisoo
 * @Version: 1.0
 */
@Data
public class BeanDO {
    private Long id;
    private Integer age;
    private String name;
    private BigDecimal volAmount;
}
