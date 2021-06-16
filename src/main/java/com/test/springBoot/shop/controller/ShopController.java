package com.test.springBoot.shop.controller;


import com.test.springBoot.shop.entity.Shop;
import com.test.springBoot.shop.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2021-06-06
 */
@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private IShopService shopService;

    @GetMapping("/list")
    public List<Shop> findList(){
        return shopService.findList();
    }

}
