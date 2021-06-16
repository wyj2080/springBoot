package com.test.springBoot.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.springBoot.shop.entity.Shop;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2021-06-06
 */
public interface IShopService extends IService<Shop> {
    List<Shop> findList();
}
