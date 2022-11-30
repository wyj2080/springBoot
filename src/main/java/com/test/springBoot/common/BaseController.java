package com.test.springBoot.common;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangyinjia
 * @description 基础控制器
 * @date 2021/12/2
 */
public abstract class BaseController {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;

    protected void setContentRange(String range, long total) {
        String outRangeStr = range + "/" + total;
        this.response.addHeader("Content-Range", outRangeStr);
    }

    public <E> R<E> success(E data) {
        return R.success(data);
    }

    public R success() {
        return R.success();
    }

}
