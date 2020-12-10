package com.test.springBoot.jetcache;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.test.springBoot.java8.UserDO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: jetcache
 * @Author: wangyinjia
 * @Date: 2020/12/10
 * @Version: 1.0
 */
@RestController
@RequestMapping("/jetcache")
public class JetCacheController {
    //DO类需要实现序列化,expire:过期时间
    @CreateCache(name = "JetCacheController.userCache", expire = 100, cacheType = CacheType.BOTH, localLimit = 50)
    private Cache<Long, UserDO> userCache;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test() throws Exception {
        UserDO userDO = new UserDO();
        userDO.setId(1001L);
        userDO.setName("小五");
        userDO.setAge(18);
        userCache.put(userDO.getId(), userDO);
        System.out.println(userCache.get(1001L).toString());
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public void get() throws Exception {
        System.out.println(userCache.get(1001L).toString());
    }


}
