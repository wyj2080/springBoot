package com.test.springBoot.chain.controller;

import com.test.springBoot.chain.entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description: 链条(逐级调用)
 * 大家都继承自链条规则抽象类，然后一级一级往下找，找到就返回
 * @Author: wangyinjia
 * @Date: 2019/11/25
 * @Version: 1.0
 */
@Controller
@RequestMapping("/chain")
public class ChainController {


    //输入参数
    public InputDO setInputParam(Long shopId, Long regionId, Long userId){
        InputDO inputDO = new InputDO();
        inputDO.setShopId(shopId);
        inputDO.setRegionId(regionId);
        inputDO.setUserId(userId);
        return  inputDO;
    }

    /**
     * 初始化节点
     */
    private ChainRule ruleConfig() {
        //第一级：店铺
        ShopRule shopRule = new ShopRule();
        //第二级：区域
        RegionRule regionRule = new RegionRule();
        //第三级：用户
        UserRule userRule = new UserRule();

        /*最后一个链条没有接力链条*/
        shopRule.setRule(regionRule);
        regionRule.setRule(userRule);
        return shopRule;
    }

    //执行逐级查找
    @RequestMapping(value = "/execute", method = RequestMethod.GET)
    public OutputDO execute(InputDO param) throws Exception {
//        param调用的地方传进来
//        param.setShopId(4998L);
//        param.setRegionId(66L);
        ChainRule chainRule = ruleConfig();
        OutputDO outputDO = chainRule.execute(param);
        return outputDO;
    }

}
