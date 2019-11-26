package com.test.springBoot.chain.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: wangyinjia
 * @Date: 2019/11/26
 * @Company: kiisoo
 * @Version: 1.0
 */
@Data
public class ShopRule extends ChainRule{
    static final Integer SHOP_TYPE = 4;

    @Override
    public OutputDO execute(InputDO param) throws Exception {
        OutputDO outputDO = new OutputDO();
        if(param.getShopId() != null){
            List<Long> shopIds = Arrays.asList(param.getShopId());
            outputDO.setEntitys(shopIds);
            outputDO.setType(SHOP_TYPE);
        }else{
            outputDO = chainRule.execute(param);
        }
        return outputDO;
    }
}
