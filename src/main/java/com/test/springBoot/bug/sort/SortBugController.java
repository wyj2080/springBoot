package com.test.springBoot.bug.sort;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Description: 排序bug
 * @Author: wangyinjia
 * @Date: 2019/11/20
 * @Version: 1.0
 */
@Controller
@RequestMapping("/bug")
public class SortBugController {

    /**
     * 排序时发生的bug
     * @return
     * @throws InterruptedException
     */
    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    public Map<String, Object> send() throws InterruptedException {
        //销售额
        List<BigDecimal> list = Arrays.asList(new BigDecimal(2), new BigDecimal(4)
                , null, new BigDecimal(0), null);
        //排序
        list.sort((a,b)->{
            Integer sortNull = sortWithNull(a, b);
            if(sortNull != null) {
                return sortNull;
            }
            else {
                //这里都取float,如果用了.intValue(),如果是0.5会返回0
                Float value = b.subtract(a).floatValue();
                return value > 0 ? 1 : (value < 0 ? -1 : 0);
            }
        });
        return new HashMap<>();
    }

    /**
     * 与null值比较
     * @param a 对象a
     * @param b 对象b
     * @return 1/-1/0/null
     */
    public static Integer sortWithNull(Object a, Object b) {
        if(a != null && b == null) {
            return -1;
        }
        if(a == null && b == null) {
            return 0;
        }
        if(a == null && b != null) {
            return 1;
        }
        else {
            return null;
        }
    }
}
