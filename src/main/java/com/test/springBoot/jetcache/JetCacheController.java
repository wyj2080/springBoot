package com.test.springBoot.jetcache;

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


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void upload() throws Exception {

    }



}
