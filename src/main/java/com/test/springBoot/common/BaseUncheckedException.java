package com.test.springBoot.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wangyinjia
 * @description 运行时异常
 * @date 2021/12/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseUncheckedException extends RuntimeException{
    private String message;
    private Integer code;

    public BaseUncheckedException(String message) {
        super(message);
        this.code = -1;
    }

    public BaseUncheckedException(int code, String message) {
        super(message);
        this.code = code;
    }
    public String getMessage() {
        return super.getMessage();
    }

    public int getCode() {
        return this.code;
    }
}
