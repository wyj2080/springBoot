package com.test.springBoot.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.test.springBoot.shop.entity.Shop;
import com.test.springBoot.shop.mapper.ShopMapper;
import com.test.springBoot.shop.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2021-06-06
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Override
    public List<Shop> findList() {
        LambdaQueryWrapper<Shop> wrapper = new LambdaQueryWrapper<>();
        return list(wrapper);
    }
}
