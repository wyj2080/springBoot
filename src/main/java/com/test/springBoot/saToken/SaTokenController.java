package com.test.springBoot.saToken;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

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
                             @RequestParam("password")String password,
                        HttpServletResponse response) {
        String result = "";
        switch (username){
            case "d1":if(password.equals("d1")){
                result = "d1";
            }else{
                result = "密码错误";
            }break;
            case "d2":if(password.equals("d2")){
                result = "d2";
            }else{
                result = "密码错误";
            }break;
            default:result="账号不存在";
        }
        if(!result.equals("密码错误") && !result.equals("账号不存在")){
            StpUtil.login(username);
        }
        return result;
    }

    @ApiOperation(value = "测试", notes = "测试")
    @GetMapping(value="/test")
    public void isLogin() {
        //是否登录
        boolean result = StpUtil.isLogin();
        System.out.println(result);
        // 获取当前会话账号id, 未登录则抛出异常
        String id = StpUtil.getLoginIdAsString();
        //返回默认值
        String id2 = StpUtil.getLoginId(null);
        System.out.println(id);
        //
        String id3 = (String) StpUtil.getLoginIdByToken("afbcedea-68b2-4019-900f-43df167f02e0");
        System.out.println(id3);
        // 获取当前`StpLogic`的token名称
        String tokenName = StpUtil.getTokenName();
        System.out.println(tokenName);
        // 获取当前会话的token值
        String tokenValue = StpUtil.getTokenValue();
        System.out.println(tokenValue);
        // 获取当前会话的token信息参数
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        System.out.println(tokenInfo.toString());

        //踢人下线
        StpUtil.logoutByLoginId("a");
        //封禁(秒，永久-1，建议先踢下线),是否封禁，时间，解除。
        StpUtil.disable("a", 86400);
        StpUtil.isDisable("a");
        StpUtil.getDisableTime("a");
        StpUtil.untieDisable("a");
    }

    @ApiOperation(value = "注销", notes = "注销")
    @GetMapping(value="/login/out")
    public String loginOut() {
        StpUtil.logout();
        return "注销成功";
    }

    @ApiOperation(value = "权限", notes = "权限")
    @GetMapping(value="/permission")
    public void permission() {
        // 当前账号是否含有指定权限, 返回true或false
        StpUtil.hasPermission("user-update");

        // 当前账号是否含有指定权限, 如果验证未通过，则抛出异常: NotPermissionException
        StpUtil.checkPermission("user-update");

        // 当前账号是否含有指定权限 [指定多个，必须全部验证通过]
        StpUtil.checkPermissionAnd("user-update", "user-delete");

        // 当前账号是否含有指定权限 [指定多个，只要其一验证通过即可]
        StpUtil.checkPermissionOr("user-update", "user-delete");
    }

    @SaCheckLogin
    @ApiOperation(value = "注解(主要配置config类)", notes = "注解")
    @GetMapping(value="/at")
    public String saLogin(){
        //是否登录
        boolean result = StpUtil.isLogin();
        System.out.println(result);
//        @SaCheckRole("admin"): 角色认证 —— 必须具有指定角色标识才能进入该方法
//        @SaCheckPermission("user:add"): 权限认证 —— 必须具有指定权限才能进入该方法
//        @SaCheckSafe: 二级认证校验 —— 必须二级认证之后才能进入该方法
//        @SaCheckBasic: HttpBasic认证 —— 只有通过 Basic 认证后才能进入该方法
        return "来了老弟";
    }



}
