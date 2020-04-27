package com.test.springBoot.mybatisPlus;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("goods_stock_190407052437_test")
public class GoodsStock190407052437Test {

  private Long id;
  private Long skuId;
  private String sku;
  private Long brandId;
  private Long storeId;
  private Integer quantity;
  private Double amount;
  /**
   * 创建时间
   */
  private Date createTime;
  /**
   * 更新时间
   */
  private Date updateTime;
  private Integer status;
  private Date firstTime;
  private Integer firstQuantity;
  private Integer type;
  private Integer onTheWayQuantity;
}