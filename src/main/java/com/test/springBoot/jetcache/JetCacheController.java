package com.test.springBoot.jetcache;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.CacheManager;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;
import com.alicp.jetcache.template.QuickConfig;
import com.test.springBoot.java8.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.Duration;
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
    // expire:过期时间(秒)跟着cacheType
    // localLimit内存中的数量
    // cacheType默认远程
    //不同类里名字相同，会共享
//    @CreateCache(name = "User", expire = 600, cacheType = CacheType.BOTH, localLimit = 50)
    private Cache<Long, UserDO> userCache;
    //2.7开始用这个,之前用@CreateCache
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private JetCacheService jetCacheService;

    @PostConstruct
    public void init() {
        QuickConfig qc = QuickConfig.newBuilder("userCache")
                .expire(Duration.ofSeconds(10))
                .cacheType(CacheType.BOTH) // two level cache
                .syncLocal(true) // invalidate local cache in all jvm process after update
                .localLimit(50).build();
        userCache = cacheManager.getOrCreateCache(qc);
    }

    /**
     * redis sentinel哨兵集群，测各种情况下的数据
     * 主:master,从:slave,哨兵:sentinel
     * 采用1主，2从，3哨兵。共6个服务(如果放在外网各自通讯一天至少1.5G流量)
     * 假设2台机器间隔10ms，100个数据put就要1秒钟。所以数据量大用putAll
     * 1.全部redis挂掉。get操作不会同步本地到redis。内存里有就直接返回了
     * 2.挂掉从，再启动。自动从主同步数据
     * 3.
     */
    //todo 哨兵提醒：redis挂掉，sentinel挂掉

    /**
     * put会存到本地和redis。
     * 如果redis挂掉重启第一次put/remove会报错，但是能继续执行,内存里生效了
     * get先读内存，没有就读redis，同时放入内存
     */
    @RequestMapping(value = "/put", method = RequestMethod.GET)
    public void put() throws Exception {
        UserDO userDO = new UserDO();
        userDO.setId(1001L);
        userDO.setName("小五");
        userDO.setAge(19);

        //存，redis里没有就报错 todo
        userCache.put(userDO.getId(), userDO);
    }

    @RequestMapping(value = "/put2", method = RequestMethod.GET)
    public void put2() throws Exception {
        UserDO userDO = new UserDO();
        userDO.setId(1002L);
        userDO.setName("小五");
        userDO.setAge(19);

        //存，redis里没有就报错 todo
        userCache.put(userDO.getId(), userDO);
    }

    @GetMapping(value = "/remove")
    public void remove() throws Exception {
        userCache.remove(1001L);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get() throws Exception {
        UserDO userDO = new UserDO();
        userDO.setId(1001L);
        userDO.setName("小五sql");
        userDO.setAge(19);
        return userCache.get(1001L).toString();
        //mysql里取
//        return userCache.computeIfAbsent(1001L, key -> getById(key), false).toString();
    }

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
        //未成功
        boolean hasRun = userCache.tryLockAndRun(1001L, 20, TimeUnit.SECONDS, () -> {
            System.out.println("lock");
        });
        System.out.println(hasRun);

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

    /**
     * 更新缓存(未成功)
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public UserDO updateBean() throws Exception {
        UserDO userDO = new UserDO();
        userDO.setId(1001L);
        userDO.setName("新名词");
        updateCache(userDO);
        return userCache.get(1001L);
    }

    @CacheUpdate(name="JetCacheController.getBean", key="#userDO.id", value = "#userDO")
    public void updateCache(UserDO userDO)  {
    }

    /**
     * 异步put ,removeAll
     * get因为有返回值所以还是会阻塞
     */
    @RequestMapping(value = "/async/put", method = RequestMethod.GET)
    @ResponseBody
    public void asyncPut() throws Exception {
        jetCacheService.async();
        System.out.println("down");
    }



}
