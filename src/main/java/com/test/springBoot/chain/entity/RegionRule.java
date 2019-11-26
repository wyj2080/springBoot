package com.test.springBoot.chain.entity;

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
public class RegionRule extends ChainRule{
    static final Integer REGION_TYPE = 3;
    @Override
    public OutputDO execute(InputDO param) throws Exception {
        OutputDO outputDO = new OutputDO();
        if(param.getRegionId() != null){
            Long regionIds = param.getRegionId();
            //区域找到shopids
            List<Long> shopIds = new ArrayList<>();
            //然后
            outputDO.setEntitys(shopIds);
            outputDO.setType(REGION_TYPE);
        }else{
            outputDO = chainRule.execute(param);
        }
        return outputDO;
    }
}
