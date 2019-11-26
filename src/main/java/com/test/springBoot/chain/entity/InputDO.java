package com.test.springBoot.chain.entity;

import lombok.Data;

/**
 * @Description: 输入参数DO
 * @Author: wangyinjia
 * @Date: 2019/11/25
 * @Version: 1.0
 */
@Data
public class InputDO {
    /**店铺id*/
    private Long shopId;

    /**区域id*/
    private Long regionId;

    /**用户id*/
    private Long userId;
}
