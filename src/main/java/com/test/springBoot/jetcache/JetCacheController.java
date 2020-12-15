package com.test.springBoot.jetcache;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.alicp.jetcache.anno.CreateCache;
import com.test.springBoot.java8.UserDO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    //DO类需要实现序列化
    // expire:过期时间
    // localLimit内存中的数量
    // cacheType默认远程
    @CreateCache(name = "JetCacheController.userCache", expire = 50, cacheType = CacheType.BOTH, localLimit = 50)
    private Cache<Long, UserDO> userCache;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test() throws Exception {
        UserDO userDO = new UserDO();
        userDO.setId(1001L);
        userDO.setName("小五");
        userDO.setAge(19);
        //存
        userCache.put(userDO.getId(), userDO);
        //取
        userCache.get(1001L);
        //删除
//        userCache.remove(1001L);
        System.out.println(userCache.get(1001L).toString());
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public void get() throws Exception {
        System.out.println(userCache.get(1001L).toString());
    }

    @RequestMapping(value = "/bean", method = RequestMethod.GET)
    @ResponseBody
    @Cached(name="JetCacheController.getBean", expire = 30)
    public UserDO getBean() throws Exception {
        return userCache.get(1001L);
    }


}
