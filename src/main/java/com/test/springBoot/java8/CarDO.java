package com.test.springBoot.java8;

import lombok.Data;

/**
 * @Description:
 * @Author: wangyinjia
 * @Date: 2020/2/17
 * @Company: kiisoo
 * @Version: 1.0
 */
@Data
public class CarDO {
    private String name;

    private Integer price;

    public CarDO(String name){
        this.name = name;
    }
}
