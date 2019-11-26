package com.test.springBoot.chain.entity;

import lombok.Data;

import java.util.List;

/**
 * @Description: 输出DO
 * @Author: wangyinjia
 * @Date: 2019/11/25
 * @Version: 1.0
 */
@Data
public class OutputDO {
    /**实体类型*/
    Integer type;

    /**实体*/
    List<Long> entitys;

}
