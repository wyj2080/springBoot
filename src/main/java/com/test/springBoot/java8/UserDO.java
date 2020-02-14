package com.test.springBoot.java8;

import lombok.Data;

import java.util.List;

/**
 * @Description: 用户DO
 * @Author: wangyinjia
 * @Date: 2020/2/10
 * @Company: kiisoo
 * @Version: 1.0
 */
@Data
public class UserDO {
    //id
    Long id;
    //年龄
    Integer age;

    List<String> cars;
}
