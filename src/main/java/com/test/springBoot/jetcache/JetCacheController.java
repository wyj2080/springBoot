package com.test.springBoot.jetcache;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.alicp.jetcache.anno.CreateCache;
import com.test.springBoot.java8.UserDO;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

/**
 * @Description: jetcache
 * @Author: wangyinjia
 * @Date: 2020/12/10
 * @Version: 1.0
 */
@RestController
@RequestMapping("/jetcache")
public class JetCacheController {
    //DO类需要实现序列化，内部类也要
    // expire:过期时间
    // localLimit内存中的数量
    // cacheType默认远程
    //不同类里名字相同，会共享
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
        userCache.remove(1002L);
        System.out.println(userCache.get(1001L).toString());

        //不支持多级缓存
//        userCache.putIfAbsent(userDO.getId(), userDO);
        //批量操作
        userCache.getAll(new HashSet<>());
        userCache.putAll(new HashMap<>());
        userCache.removeAll(new HashSet<>(Arrays.asList(1L)));
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public void test2() throws Exception {
        //jetCache特有api
        userCache.computeIfAbsent(1001L, t -> {
            System.out.println(t);
            return userCache.get(1001L);
        }, true, 100, TimeUnit.SECONDS);
        //put单独设置时间
        userCache.put(1002L, new UserDO(), 2, TimeUnit.SECONDS);
        //
        boolean hasRun = userCache.tryLockAndRun(1001L, 20, TimeUnit.SECONDS, () -> {
            System.out.println("lock");
        });
        System.out.println(hasRun);

    }

    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public void test3() throws Exception {
        System.out.println(userCache.get(1001L));
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public void get() throws Exception {
        System.out.println(userCache.get(1001L).toString());
    }

    /**
     * 方法缓存：返回值缓存,null除外
     */
    @RequestMapping(value = "/bean", method = RequestMethod.GET)
    @ResponseBody
    @Cached(name="JetCacheController.getBean", expire = 30)
    public UserDO getBean(@RequestParam("ff") String ff) throws Exception {
        System.out.println(ff);
        return userCache.get(1001L);
    }


}
