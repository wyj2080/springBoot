package com.test.springBoot.common.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test.springBoot.link.entity.TestDO;
import com.test.springBoot.link.mapper.LinkDOMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: wangyinjia
 * @Date: 2019/10/11
 * @Version: 1.0
 */
@Service
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    LinkDOMapper linkDOMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        TestDO testDO = new TestDO();
        UsernamePasswordToken authToken = (UsernamePasswordToken)token;
        testDO.setName(authToken.getUsername());
        QueryWrapper<TestDO> wrapper = new QueryWrapper<>();
        wrapper.eq("login",testDO.getName());
        List<TestDO> list = linkDOMapper.selectList(wrapper);
        if(list == null || list.size() == 0){
            throw new UnknownAccountException();
        }else{
            testDO = list.get(0);
        }
        AuthenticationInfo authInfo = new SimpleAuthenticationInfo(testDO.getLogin(),testDO.getPassword(),"");
        return authInfo;
    }
}
