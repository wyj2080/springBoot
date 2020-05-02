package com.test.springBoot.http.controller;

import com.test.springBoot.http.SellerErpDO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @Description: http
 * @Author: wangyinjia
 * @Date: 2020/3/9
 * @Version: 1.0
 */
@Controller
@RequestMapping("/http")
public class HttpClientController {

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> client(){
        Map<String, Object> result = new HashMap<>();

        return result;
    }

    @RequestMapping(value = "/send/goods", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> sendGoods(@RequestBody String aaa){
        Map<String, Object> result = new HashMap<>();
        result.put("code","1000");
        result.put("results","successData");
        System.out.println("receive success");
        return result;
    }

    @RequestMapping(value = "/seller", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> seller() throws InvocationTargetException, IllegalAccessException {
        List<SellerErpDO> sellerList = new ArrayList<>();
        SellerErpDO sellerErpDO = new SellerErpDO();
        sellerErpDO.setName("张三");
        sellerErpDO.setNO("0001");
        sellerErpDO.setSEX(1);
        sellerErpDO.setADDRESS("杭州湾");
        sellerErpDO.setBEGIN_DATE(new Date());
        sellerErpDO.setBIRTHDATE(new Date());
        sellerErpDO.setC_PROVINCE_name("嘉兴市");
        sellerErpDO.setC_STORE_code("XX10");
        sellerErpDO.setEdiflag(80);
        sellerErpDO.setISACTIVE(1);
        sellerErpDO.setEND_DATE(null);
        sellerErpDO.setHANDSET("13577405999");
        sellerErpDO.setMODIFIEDDATE(new Date());
        sellerErpDO.setReadtime(new Date());
        sellerErpDO.setUptime(new Date());
        sellerErpDO.setRESULTS("原因不明");
        sellerList.add(sellerErpDO);
        SellerErpDO sellerErpDO2 = new SellerErpDO();
        BeanUtils.copyProperties(sellerErpDO2, sellerErpDO);
        sellerErpDO2.setName("里四");
        sellerErpDO2.setNO("0002");
        sellerErpDO2.setSEX(2);
        sellerErpDO2.setADDRESS("宁波市1001路");
        sellerErpDO2.setBEGIN_DATE(new Date());
        sellerErpDO2.setBIRTHDATE(new Date());
        sellerErpDO2.setC_PROVINCE_name("宁波");
        sellerErpDO2.setC_STORE_code("XX10");
        sellerErpDO2.setEdiflag(80);
        sellerList.add(sellerErpDO2);
        System.out.println(sellerList);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code","1000");
        resultMap.put("results", sellerList);
        return resultMap;
    }
}
