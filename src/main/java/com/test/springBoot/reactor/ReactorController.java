package com.test.springBoot.reactor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;

import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.Selector;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description: 非阻塞IO
 * @Author: wangyinjia
 * @Date: 2020/3/2
 * @Company: kiisoo
 * @Version: 1.0
 */
@Controller
@RequestMapping("/reactor")
public class ReactorController {

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void date() {
        Flux f1 = Flux.just("123");
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        Flux f2 = Flux.fromIterable(getList());
//        f2.subscribe(value -> {
//            System.out.println(value);
//        }, error -> System.out.println("出错"),() -> System.out.println("完成"));
        //发送200W个并发请求到服务器，看看响应时间

    }

    public List<Integer> getList(){
        System.out.println("!!!");
        return Arrays.asList(1,2,3,4,5);
    }

}
