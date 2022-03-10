package com.test.springBoot.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wangyinjia
 * @description 响应内容包装
 * @date 2021/12/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> implements Serializable {

    @ApiModelProperty("0成功，其他失败")
    private int errcode = 0;

    @ApiModelProperty("错误信息")
    private String errmsg = "success";

    @ApiModelProperty("响应数据")
    private T data;

    public R(int errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public static R success() {
        return success("success", null);
    }

    public static <T> R<T> success(T data) {
        return success("success", data);
    }

    public static <T> R<T> success(String msg, T data) {
        return success(0, msg, data);
    }

    public static <T> R<T> success(int code, String msg, T data) {
        return new R(code, msg, data);
    }

    public static R failed() {
        return failed("fail");
    }

    public static R failed(String msg) {
        return failed(msg, null);
    }

    public static R failed(int code, String msg) {
        return new R(code, msg);
    }

    public static <T> R<T> failed(String msg, T data) {
        return failed(-1, msg, data);
    }

    public static <T> R<T> failed(int code, String msg, T data) {
        return new R(code, msg, data);
    }

}
