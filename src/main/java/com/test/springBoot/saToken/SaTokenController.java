package com.test.springBoot.saToken;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangyinjia
 * @description sa-token权限认证
 * @date 2021/9/1
 */
@RestController
@RequestMapping("/sa-token")
@Api(tags = "sa-token权限认证")
public class SaTokenController {


    @ApiOperation(value = "登录", notes = "登录")
    @GetMapping(value="/login")
    public String login(@RequestParam("username")String username,
                             @RequestParam("password")String password) {
        if(username.equals("a") && password.equals("1")){
            StpUtil.login("a");
            return "登录成功";
        }else{
            return "登录失败";
        }
    }

    @ApiOperation(value = "判断是否登录", notes = "判断是否登录")
    @GetMapping(value="/is/login")
    public String isLogin() {
        boolean result = StpUtil.isLogin();
        return result ? "已经登录" : "未登录";
    }

    @ApiOperation(value = "注销", notes = "注销")
    @GetMapping(value="/login/out")
    public String loginOut() {
        StpUtil.logout();
        return "注销成功";
    }


}
