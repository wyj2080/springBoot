package com.test.springBoot.common;

/**
 * @author wangyinjia
 * @description
 * @date 2021/12/3
 */
public class WeException extends BaseUncheckedException{
    private static final long serialVersionUID = -1L;

    public WeException(String message) {
        super(message);
    }

    public WeException(int code, String message) {
        super(code, message);
    }

    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
