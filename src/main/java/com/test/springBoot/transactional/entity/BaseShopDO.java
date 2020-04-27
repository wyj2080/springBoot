package com.test.springBoot.transactional.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 店铺DO
 * @Author: wangyinjia
 * @Date: 2020/3/26
 * @Version: 1.0
 */
@Data
@TableName("t_shop")
public class BaseShopDO {
    @TableId(type = IdType.AUTO)
    private Long shopId;

    private String shopName;

    private Date createTime;
}
