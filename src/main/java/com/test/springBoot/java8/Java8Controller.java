package com.test.springBoot.java8;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: java8常用方法
 * @Author: wangyinjia
 * @Date: 2020/3/16
 * @Version: 1.0
 */
@Controller
@RequestMapping("/java8")
public class Java8Controller {
    //店铺list
    private List<ShopDO> shopList;

    /**
     * 基础数据
     */
    public Java8Controller(){
        SellerDO sellerDO1 = new SellerDO(1L, "王三", 20, new BigDecimal(1000));
        SellerDO sellerDO2 = new SellerDO(2L, "叶四", 24, new BigDecimal(1500));
        SellerDO sellerDO3 = new SellerDO(3L, "江五", 24, new BigDecimal("300.5"));
        SellerDO sellerDO4 = new SellerDO(4L, "朱六", 19, new BigDecimal("300.2"));
        SellerDO sellerDO5 = new SellerDO(5L, "蒋七", 25, new BigDecimal("300.8"));
        SellerDO sellerDO6 = new SellerDO(6L, "汪何八", 16, new BigDecimal(3000));
        List<SellerDO> sellerList1 = Arrays.asList(sellerDO1, sellerDO2);
        List<SellerDO> sellerList2 = Arrays.asList(sellerDO3, sellerDO4);
        List<SellerDO> sellerList3 = Arrays.asList(sellerDO5, sellerDO6);
        ShopDO shopDO1 = new ShopDO(1L,"DP01", "一号店", 100F, new ArrayList<>(), sellerList1);
        ShopDO shopDO2 = new ShopDO(2L,"DP02", "二号店", 120F, new ArrayList<>(), sellerList2);
        ShopDO shopDO3 = new ShopDO(3L,"DP03", "三号店", 90F, new ArrayList<>(), sellerList3);
        shopList = new ArrayList<>();
        shopList.add(shopDO1);
        shopList.add(shopDO2);
        shopList.add(shopDO3);

    }

    /**
     * 遍历List,Map
     */
    @RequestMapping(value = "/iterator", method = RequestMethod.GET)
    public void iterator(){
        List<Integer> list = Arrays.asList(1,2,3,4);
        AtomicInteger count = new AtomicInteger(0);
        list.forEach(i -> {
            System.out.println(i);
            count.updateAndGet(v -> v+3);
        });
        count.get();


    }

    /**
     * stream流
     */
    @RequestMapping(value = "/stream", method = RequestMethod.GET)
    public void stream(){
        List<Integer> list = Arrays.asList(2,1,3);
        Stream<Integer> s = list.stream();
        list.forEach(a -> a=2);
        list.stream().forEach(System.out::println);
        Stream<Integer> ss = list.stream();

    }

    /**
     * 并行流
     */
    @RequestMapping(value = "/parallelStream", method = RequestMethod.GET)
    public void parallelStream(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        List<Integer> resultList = list.parallelStream().map(a ->a).collect(Collectors.toList());
        resultList.forEach(System.out::println);

//        List<Integer> result = new ArrayList<>();
//        list.parallelStream().forEach(result::add);
//        result.forEach(System.out::println);
        System.out.println("------------");
        //同步的方法


    }

    /**
     * 去重
     */
    @RequestMapping(value = "/distinct", method = RequestMethod.GET)
    public void distinct(){
        List<Integer> list = Arrays.asList(2,1,3,2);
        Set<Integer> se = new HashSet<>(list);
        list = list.stream().distinct().collect(Collectors.toList());
        list.stream().forEach(System.out::println);
//        SellerDO sellerDO1 = new SellerDO(new Long(1), new String("小王"), new Integer(20), new BigDecimal(1000));
//        SellerDO sellerDO2 = new SellerDO(new Long(1), new String("小王"), new Integer(20), new BigDecimal("1000"));
//        List<SellerDO> sellerList = new ArrayList<>();
//        sellerList.add(sellerDO1);
//        sellerList.add(sellerDO2);
//        sellerList = sellerList.stream().distinct().collect(Collectors.toList());
//        sellerList.forEach(seller -> System.out.println(seller.toString()));
    }

    /**
     * 求和
     */
    @RequestMapping(value = "/sum", method = RequestMethod.GET)
    public void sum(){
        //面积求和orElse





        //导购业绩求和
        List<SellerDO> sellerList = shopList.stream().flatMap(shopDO -> shopDO.getSellerList().stream()).collect(Collectors.toList());
        BigDecimal amount = sellerList.stream().map(SellerDO::getAmount).reduce(new BigDecimal("0"),(acc, a) ->  acc.add(a));
        System.out.println(amount);
    }

    /**
     * map,flatMap
     */
    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public void map() {
        //取出shopList里店铺的id，放入List<Long>
        List<Long> shopIds = new ArrayList<>();
        shopList.forEach(shopDO -> {
            shopIds.add(shopDO.getId());
        });
        List<ShopDO> result = new ArrayList<>();
        //map版
        List<Long> newShopIds = shopList.stream().filter(shopDO -> shopDO != null && shopDO.getId() != null).map(shopDO -> shopDO.getId()).collect(Collectors.toList());

        //取出所有导购id，放入List<Long>
        List<Long> sellerIds = new ArrayList<>();
        shopList.forEach(shopDO -> {
            shopDO.getSellerList().forEach(sellerDO -> {
                sellerIds.add(sellerDO.getId());
            });
        });
        //flatMap版
        List<Long> sellerIdsNew = shopList.stream()
                .flatMap(shopDO -> shopDO.getSellerList().stream())
                .map(sellerDO -> sellerDO.getId()).collect(Collectors.toList());

//        shopIdsNew.forEach(System.out::println);
        sellerIdsNew.forEach(System.out::println);
    }

    /**
     * 过滤
     */
    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public void filter(){
        List<Integer> list = Arrays.asList(1,2,3);
        list.forEach(i -> i=2);
        list.forEach(System.out::println);

    }

    /**
     * 数据分块、分组
     */
    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public void group(){
        List<SellerDO> sellerList = shopList.stream().flatMap(shopDO -> shopDO.getSellerList().stream()).collect(Collectors.toList());
        //分块(大于20岁的一组)
        Map<Boolean, List<SellerDO>> tmap = sellerList.stream().collect(Collectors.partitioningBy(sellerDO -> sellerDO.getAge() > 20));
        tmap.forEach((k,v) -> {
            System.out.println("key：" + k + ",values：" + v);
        });
        //分组(按年龄分组)
        Map<Integer, List<SellerDO>> gmap = sellerList.stream().collect(Collectors.groupingBy(sellerDO -> sellerDO.getAge()));

        //分组计数
        Map<Object,Long> map = sellerList.stream().collect(Collectors.groupingBy(SellerDO::getAge,Collectors.counting()));
        map.forEach((k,v) -> {
            System.out.println("key：" + k + ",values：" + v);
        });
    }

    /**
     * 排序
     */
    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    public void sort(){
        //导购按年龄升序排列
        List<SellerDO> sellerList = shopList.stream().flatMap(shopDO -> shopDO.getSellerList().stream()).collect(Collectors.toList());
        //list排序(用-号的局限：返回值必须int，改用Comparator.comparing(SellerDO::getAmount))
        SellerDO sellerDO = sellerList.stream().min(Comparator.comparing(SellerDO::getAmount)).orElse(null);
//        sellerList.forEach(sellerDO -> System.out.println(sellerDO.toString()));


        //流排序，按业绩降序
        sellerList = sellerList.stream().sorted(Comparator.comparing(SellerDO::getAmount).reversed()).collect(Collectors.toList());


    }

    /**
     * 最大、最小值
     */
    @RequestMapping(value = "/min", method = RequestMethod.GET)
    public void min(){
        List<SellerDO> sellerList = shopList.stream().flatMap(shopDO -> shopDO.getSellerList().stream()).collect(Collectors.toList());
        //最小销售额的导购

    }

    /**
     * 过滤并移除
     */
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public void remove(){
        List<SellerDO> sellerList = shopList.stream().flatMap(shopDO -> shopDO.getSellerList().stream()).collect(Collectors.toList());
        List<Long> needSellerIds = Arrays.asList(1L,2L,3L);
        sellerList.removeIf(sellerDO -> needSellerIds.contains(sellerDO.getId()));


    }

    /**
     * list转map
     */
    @RequestMapping(value = "/to/map", method = RequestMethod.GET)
    public void toMap(){
        //导购id为key,导购DO为value，并用peek给年龄大于20的乘10
        List<SellerDO> sellerList = shopList.stream().flatMap(shopDO -> shopDO.getSellerList().stream()).collect(Collectors.toList());

        Map<Long, SellerDO> map = sellerList.stream().collect(Collectors.toMap(sellerDO -> sellerDO.getId(), sellerDO -> sellerDO));
        map.forEach((k,v) -> {
            System.out.println("k:"+k+",v:"+v);
        });
    }

    /**
     * 连接成字符串
     */
    @RequestMapping(value = "/link/to/string", method = RequestMethod.GET)
    public void linkToString(){
        //将店铺名称用","连接成字符串
        List<String> list = Arrays.asList("a","b","c");
        String result = list.stream().collect(Collectors.joining(",","[","}"));
System.out.println(result);
    }

    /**
     * 先从map缓存找，没有再查数据库
     */
    @RequestMapping(value = "/map/or/db", method = RequestMethod.GET)
    public void mapOrDB(){
        //获取id是1的店铺，4的店铺
        Map<Long, ShopDO> shopMap = new HashMap<>();
        shopMap = shopList.stream().collect(Collectors.toMap(shopDO -> shopDO.getId(), shopDO -> shopDO));
//        ShopDO shopDO = shopMap.computeIfPresent(1L,this::getByDB);

    }

    private ShopDO getByDB(Long id){
        System.out.println("查数据库");
        ShopDO shopDO = new ShopDO();
        shopDO.setId(4L);
        return shopDO;
    }

    /**
     * date日期
     */
    @RequestMapping(value = "/date", method = RequestMethod.GET)
    public void or() {
//        System.out.println(LocalDate.now());
//        System.out.println(LocalTime.now());
//        System.out.println(LocalDateTime.now());
//
//        LocalDate localDate = LocalDate.now();
//        System.out.println(localDate.getYear() + "年" + localDate.getMonthValue() + "月" + localDate.getDayOfMonth() +"日");
//        System.out.println("周" + localDate.getDayOfWeek().getValue());
//        System.out.println(localDate.with(TemporalAdjusters.firstDayOfMonth()));
//        System.out.println(localDate.with(TemporalAdjusters.lastDayOfMonth()));
//        System.out.println(localDate.with(TemporalAdjusters.firstDayOfMonth()).plusDays(-1));
//        System.out.println(LocalTime.parse("14:14:14"));
        LocalDate localDateTmp = LocalDate.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String nowDateStr = localDateTmp.format(df);
        System.out.println(nowDateStr);

        localDateTmp = localDateTmp.plusMonths(-1);
        localDateTmp.plusWeeks(1);
        String lastDateStr = localDateTmp.format(df);
        System.out.println(lastDateStr);

        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14);
        list.parallelStream().forEach(i -> {
            Integer a = 2;
            Integer flag = 1;
            while (true){
                if(a > 1000000000){
                    flag = -1;
                }
                if(a < 0){
                    flag = 1;
                }
                a += flag;
            }
        });

    }

    /**
     * 取交集
     */
    @RequestMapping(value = "/retain", method = RequestMethod.GET)
    public void retain(){
        List<Integer> list1 = new ArrayList<>();list1.add(1);list1.add(2);list1.add(3);
        List<Integer> list2 = new ArrayList<>();list2.add(2);list2.add(3);list2.add(3);
        List<Integer> list3 = new ArrayList<>();list3.add(2);list3.add(3);list3.add(3);
        list1.retainAll(list3);
        list2.retainAll(list3);
        System.out.println(list1.toString());
        System.out.println(list2.toString());
        System.out.println(list3.toString());
    }

}
