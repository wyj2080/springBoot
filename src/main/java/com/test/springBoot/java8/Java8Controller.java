package com.test.springBoot.java8;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.*;
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
        SellerDO sellerDO6 = new SellerDO(6L, "金八", 16, new BigDecimal(3000));
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
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
        for(Integer i : list){
            System.out.println(i);
        }
        list.forEach(i -> {
            System.out.println(i);
        });

        Map<String, Object> map = new HashMap<>();
        map.put("keyA", "valueA");
        map.put("keyB", "valueB");
        for (Map.Entry<String, Object> entry : map.entrySet()) { //Map.Entry是一种键值对的类型
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
        //map的forEach...
    }

    /**
     * stream流
     */
    @RequestMapping(value = "/stream", method = RequestMethod.GET)
    public void stream(){
        List<Integer> list = Arrays.asList(2,1,3);
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
        List<Integer> result = new ArrayList<>();
        list.parallelStream().forEach(result::add);
        result.forEach(System.out::println);
        System.out.println("------------");
        //同步的方法

    }

    /**
     * 去重
     */
    @RequestMapping(value = "/distinct", method = RequestMethod.GET)
    public void distinct(){
        List<Integer> list = Arrays.asList(2,1,3,2);
        list.stream().distinct();
        list.stream().forEach(System.out::println);
        SellerDO sellerDO1 = new SellerDO(new Long(1), new String("小王"), new Integer(20), new BigDecimal(1000));
        SellerDO sellerDO2 = new SellerDO(new Long(1), new String("小王"), new Integer(20), new BigDecimal("1000"));
        List<SellerDO> sellerList = new ArrayList<>();
        sellerList.add(sellerDO1);
        sellerList.add(sellerDO2);
        sellerList = sellerList.stream().distinct().collect(Collectors.toList());
        sellerList.forEach(seller -> System.out.println(seller.toString()));
    }

    /**
     * 求和
     */
    @RequestMapping(value = "/sum", method = RequestMethod.GET)
    public void sum(){
        //面积求和

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
        //map版

        //取出所有导购id，放入List<Long>
        List<Long> sellerIds = new ArrayList<>();
        shopList.forEach(shopDO -> {
            shopDO.getSellerList().forEach(sellerDO -> {
                sellerIds.add(sellerDO.getId());
            });
        });
        //flatMap版

//        shopIdsNew.forEach(System.out::println);
//        sellerIdsNew.forEach(System.out::println);
    }

    /**
     * 过滤
     */
    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public void filter(){
        //过滤面积大于100的店铺

        //面积大于100的店铺数

        //并行过滤(filter里面写多行)

    }

    /**
     * 数据分块、分组
     */
    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public void group(){
        List<SellerDO> sellerList = shopList.stream().flatMap(shopDO -> shopDO.getSellerList().stream()).collect(Collectors.toList());
        //分块(大于20岁的一组)

        //分组(按年龄分组)

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

        //流排序

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


    }

    /**
     * list转map
     */
    @RequestMapping(value = "/to/map", method = RequestMethod.GET)
    public void toMap(){
        //导购id为key,导购DO为value，并用peek给年龄大于20的乘10
        List<SellerDO> sellerList = shopList.stream().flatMap(shopDO -> shopDO.getSellerList().stream()).collect(Collectors.toList());

    }

    /**
     * 连接成字符串
     */
    @RequestMapping(value = "/link/to/string", method = RequestMethod.GET)
    public void linkToString(){
        //将店铺名称用","连接成字符串

    }

    /**
     * 先从map缓存找，没有再查数据库
     */
    @RequestMapping(value = "/map/or/db", method = RequestMethod.GET)
    public void mapOrDB(){
        //获取id是1的店铺，4的店铺
        Map<Long, ShopDO> shopMap = new HashMap<>();
        shopMap = shopList.stream().collect(Collectors.toMap(shopDO -> shopDO.getId(), shopDO -> shopDO));

    }

    private ShopDO getByDB(Long id){
        System.out.println("查数据库");
        ShopDO shopDO = new ShopDO();
        shopDO.setId(4L);
        return shopDO;
    }

    /**
     * test
     */
    @RequestMapping(value = "/or", method = RequestMethod.GET)
    public void or() {
        Set<String> ss = new HashSet<>();
        ss.add("大姐");
        ss.add("小儿");
        ss.add("啊");
        ss.forEach(System.out::println);
        ss.stream().forEach(a -> System.out.println(a));
    }
}
