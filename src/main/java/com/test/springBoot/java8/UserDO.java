package com.test.springBoot.java8;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 用户DO
 * @Author: wangyinjia
 * @Date: 2020/2/10
 * @Company: kiisoo
 * @Version: 1.0
 */
@Data
public class UserDO implements Serializable {
    //id
    Long id;
    //姓名
    String name;
    //年龄
    Integer age;
    //
    List<String> cars;

    /**车*/
    private CarDO carDO;

    private Car car = new Car("小车");

    @Data
    @AllArgsConstructor
    public static class Car implements Serializable{
        private String name;
    }
}
