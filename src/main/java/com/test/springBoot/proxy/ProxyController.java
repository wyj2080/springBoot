package com.test.springBoot.proxy;

import org.apache.shiro.aop.MethodInterceptor;
import org.apache.shiro.aop.MethodInvocation;
import org.springframework.stereotype.Controller;

/**
 * @Description:
 * @Author: wangyinjia
 * @Date: 2020/4/13
 * @Version: 1.0
 */
@Controller
public class ProxyController implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        return null;
    }
}
