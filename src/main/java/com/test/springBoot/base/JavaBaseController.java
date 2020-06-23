package com.test.springBoot.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @Description: java基础
 * @Author: wangyinjia
 * @Date: 2020/4/27
 * @Company: kiisoo
 * @Version: 1.0
 */
@Controller
@RequestMapping("/base")
public class JavaBaseController {

    /**
     * Integer用"=="的问题
     */
    @RequestMapping(value = "/Integer", method = RequestMethod.GET)
    public void Integer(){
        Integer a = 126;
        Integer b = 126;
        System.out.println(a==b);//false
        a++;
        b++;
        System.out.println(a==b);//true
        a++;
        b++;
        System.out.println(a==b);//true

        Integer a2 = 126;
        Integer b2 = new Integer(126);
        System.out.println(a2 == b2);//false
        System.out.println(new Integer(126) == a2);//false
        System.out.println(new Integer(126) == 126);//false

        System.out.println(Math.round(-2.5));//-2
    }

    /**
     * BigDecimal
     */
    @RequestMapping(value = "/decimal", method = RequestMethod.GET)
    public void decimal(){
        BigDecimal b1 = new BigDecimal("1000.000");
        BigDecimal b2 = BigDecimal.valueOf(0.9);
        System.out.println(b1.stripTrailingZeros().toPlainString());
        System.out.println(b2.toPlainString());
        //加
        System.out.println(b1.add(b2));
        //减
        System.out.println(b1.subtract(b2));
        //乘
        System.out.println(b1.multiply(b2));
        //除
        System.out.println(b1.divide(b2,6, BigDecimal.ROUND_HALF_UP));
        //保留小数
        b2 = new BigDecimal("0.666").setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(b2);
    }

    /**
     * String
     */
    @RequestMapping(value = "/string", method = RequestMethod.GET)
    public void string(){
        String s = "abcabc";
        System.out.println(s.charAt(2));
        System.out.println(s.indexOf("abc"));
        System.out.println(s.lastIndexOf("abc"));

        String s1 = "ABC";
        String s2 = "abc";
        System.out.println(s1.equalsIgnoreCase(s2));
    }

    /**
     * JSON,JSONObject和JSONArray区别
     */
    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public void json(){
        User user = new User();
        String jsonStr = JSON.toJSONString(user);
        System.out.println(jsonStr);
        System.out.println(user.toString());
        User userTmp = JSON.parseObject(jsonStr, User.class);
        System.out.println(userTmp);

        User user1 = new User();
        User user2 = new User();
        List<User> userList = Arrays.asList(user1, user2);
        //userList转jsonArray
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(userList));
        System.out.println(jsonArray.toString());

        //jsonArray转userList
        List<User> userListTmp = JSON.parseArray(jsonArray.toJSONString(), User.class);
        System.out.println(userListTmp.toString());

    }

    /**
     * List集合
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void list(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(30);
        list.add(4);
        list.add(5);
        //最大值
        int max = Collections.max(list);
        //list倒置
        Collections.reverse(list);
        //截取，如果往list添加元素，再遍历newList会报错。!!因此不要用这个!!
        List<Integer> newList = list.subList(2,4);
        

    }


}
