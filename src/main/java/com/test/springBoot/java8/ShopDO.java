package com.test.springBoot.java8;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 店铺DO
 * @Author: wangyinjia
 * @Date: 2020/3/16
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
public class ShopDO {
    //id
    Long id;
    //code
    String code;
    //店铺名称
    String name;
    //面积
    Float area;
    //导购名称list
    List<String> sellerNames;
    //导购list
    private List<SellerDO> sellerList;

    public ShopDO(){
        sellerNames = new ArrayList<>();
        sellerList = new ArrayList<>();
    }
}
