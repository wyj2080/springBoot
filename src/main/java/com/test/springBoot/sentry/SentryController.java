package com.test.springBoot.sentry;

import io.sentry.Sentry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangyinjia
 * @description
 * @date 2021/11/3
 */
@RestController
@RequestMapping("/sentry")
@Api(tags = "哨兵")
public class SentryController {

    @ApiOperation(value = "测试", notes = "测试")
    @GetMapping(value="/test")
    public void test() throws Exception {
        for (int i = 0; i < 60; i++) {
            try {
                String a = "a"+i;
//                Integer b = Integer.valueOf(a);
                Thread.sleep(300);
                throw new Exception(a);
            }catch (Exception e){
                Sentry.captureException(e);
            }
        }

//        throw new Exception("出错测试");
        System.out.println("jishu");
    }
}
