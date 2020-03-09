package com.test.springBoot.java8;




import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Description: lambda表达式
 * @Author: wangyinjia
 * @Date: 2020/2/5
 * @Company: kiisoo
 * @Version: 1.0
 */
@Controller
@RequestMapping("/lambda")
public class Lambda {

    //起线程
    Runnable run1 = new Runnable() {
        @Override
        public void run() {
            System.out.println("new runnable");
        }
    };
    Runnable run2 = () -> {
      System.out.println("123");
      System.out.println("234");
    };
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test(){
        //lambda是静态类型
        AtomicReference<Integer> num = new AtomicReference<>(0);
        List<Integer> list = Arrays.asList(1,2,3,4);
        List<Integer> list2 = new ArrayList<>();
        list.forEach(a -> {
            list2.add(a);
            num.getAndSet(num.get() + 1);
        });
//        list.remove(2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.format(new Date());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    @RequestMapping(value = "/date", method = RequestMethod.GET)
    @ResponseBody
    public void date() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //sdf不安全
        //一个list插2020-01-01，另一个2020-01-02，然后计数
        List<Integer> list = Arrays.asList(1,2);
        list.parallelStream().forEach(a -> {
            List<Date> newList = new ArrayList<>();
            try {
                for(int i=0;i<100000;i++){
                    Date date = sdf.parse("2000-01-0"+a);
                    newList.add(date);
                }
                Date d = sdf.parse("2000-01-0"+a);
                Long num = newList.stream().filter(date -> date.equals(d)).count();
                System.out.println(num);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * 流
     */
    @RequestMapping(value = "/stream", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> stream() throws ParseException {
        UserDO userDO = new UserDO();
        userDO.setId(1L);userDO.setAge(new Integer(10));userDO.setCarDO(new CarDO("奔驰"));
        UserDO userDO2 = new UserDO();
        userDO2.setId(2L);userDO2.setAge(10);userDO2.setCarDO(new CarDO("宝马"));
        UserDO userDO3 = new UserDO();
        userDO3.setId(2L);userDO3.setAge(new Integer(10));
        List<UserDO> userList = Arrays.asList(userDO, userDO2, userDO3);

//        List<String> cars = new ArrayList<>();
//        userList.forEach(user -> {
//            cars.addAll(user.getCars());
//        });

        List<Integer> list = Arrays.asList(1,2,3,4,5);
        List<Integer> list2 = list.stream().filter(a->a==6).collect(Collectors.toList());
        list2.forEach(System.out::println);

        return new HashMap<>();


    }

    public UserDO getUserByDB(Long name){
        UserDO userDO = new UserDO();
        userDO.setId(6L);userDO.setAge(18);userDO.setCarDO(new CarDO("奥里给"));
        return userDO;
    }


}
