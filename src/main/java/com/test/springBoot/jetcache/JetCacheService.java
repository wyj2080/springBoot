package com.test.springBoot.jetcache;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.test.springBoot.java8.UserDO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author wangyinjia
 * @description
 * @date 2021/7/7
 */
@Service
public class JetCacheService {
    @CreateCache(name = "JetCacheController.userCache", expire = 50, cacheType = CacheType.BOTH, localLimit = 50)
    private Cache<Long, UserDO> userCache;

    @Async
    public void async() throws InterruptedException {
        UserDO userDO = new UserDO();
        userDO.setId(1002L);
        userCache.put(userDO.getId(), userDO);
        userDO.setId(1003L);
        userCache.put(userDO.getId(), userDO);
        Thread.sleep(2000);
        System.out.println("put async");
    }
}
