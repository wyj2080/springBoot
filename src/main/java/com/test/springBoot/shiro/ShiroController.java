package com.test.springBoot.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description: shiro认证
 * @BeforeClass -> @Before -> @Test -> @After -> @AfterClass;
 * @Author: wangyinjia
 * @Date: 2020/1/19
 * @Company: kiisoo
 * @Version: 1.0
 */

@Controller
@RequestMapping("/shiro")
public class ShiroController {
    private SimpleAccountRealm accountRealm = new SimpleAccountRealm();
    private DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

    /**
     * 初始化账号list
     */
    private ShiroController(){
        //初始化数据源,实际是要从数据库读取的
        accountRealm.addAccount("congzhizhi", "123");
        accountRealm.addAccount("jackc", "4567");
        //构建环境
        defaultSecurityManager.setRealm(accountRealm);
    }

    public void testAuthentication() {
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //当前操作主体， application user
        Subject subject = SecurityUtils.getSubject();
        //用户输入的账号密码
        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken("jackc", "4567");
        //登入认证
        try{
            subject.login(usernamePasswordToken);
            System.out.println("认证结果:"+subject.isAuthenticated());
        }catch (UnknownAccountException e) {
            System.out.println("账号不存在"+e);
        } catch (IncorrectCredentialsException e1) {
            System.out.println("密码错误"+e1);
        } catch (Exception e2) {
            System.out.println(e2);
        }

    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void shiroTest(){
        testAuthentication();
    }
}
