package com.test.springBoot.order.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("mall_order")
@AllArgsConstructor
public class Order {
  private Long id;
  private Long orderNo;
  private Integer status;
  private Date orderCreateTime;
  private Date orderPayTime;
  private Long userId;
  private String userName;
  private String sendAddress;
  private String receiveAddress;
  private BigDecimal originAmount;
  private BigDecimal amount;

}
