package com.test.springBoot.common.tools;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/**
 * @Description: 常用工具类演示
 * @Author: wangyinjia
 * @Date: 2019/12/5
 * @Company: kiisoo
 * @Version: 1.0
 */
@Controller
@RequestMapping("/tools")
public class CommonToolsController {

    @RequestMapping(value = "/string", method = RequestMethod.GET)
    public void StringUtilsTest(){
        //字符串判空
        List<String> a = Arrays.asList("", "null", null, "  ", " a a ", "0");
        a.forEach(str ->{
            System.out.println(StringUtils.isEmpty(str));
        });
        System.out.println("-----------------区域：isEmpty 有空格不算空");
        a.forEach(str ->{
            System.out.println(StringUtils.isEmpty(str));
        });
        //数组→字符串
        Integer[] s = {1, 2, 3};
        String r = StringUtils.join(s,"分隔符可选");
        System.out.println(r);
        //空→null
        List<String> aa = Arrays.asList("", null, "  ", "0");
        a.forEach(str ->{
            System.out.println(StringUtils.trimToNull(str));
        });
        //替换字符串
        String re = "aa其实bb";
        System.out.println(StringUtils.replace(re,"aa","bb"));
    }

    @RequestMapping(value = "/array", method = RequestMethod.GET)
    public void arrayUtilsTest(){
        Integer[] s = {1,2,3};
        Integer[] s1 = {};
        Integer[] s2 = null;
        System.out.println(ArrayUtils.isEmpty(s));
        System.out.println(ArrayUtils.isEmpty(s1));
        System.out.println(ArrayUtils.isEmpty(s2));
    }

    @RequestMapping(value = "/digest", method = RequestMethod.GET)
    public void digestTest(){
        String d = DigestUtils.sha256("username").toString();
        DateUtils.isSameDay(new Date(),new Date());
        System.out.println(d);
    }
}
