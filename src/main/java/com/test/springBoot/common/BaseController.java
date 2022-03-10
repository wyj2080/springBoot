package com.test.springBoot.common;

/**
 * @author wangyinjia
 * @description 基础控制器
 * @date 2021/12/2
 */
public abstract class BaseController {

    public <E> R<E> success(E data) {
        return R.success(data);
    }

    public R success() {
        return R.success();
    }

}
