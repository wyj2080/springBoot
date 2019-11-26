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
public class UserRule extends ChainRule{
    static final Integer USER_TYPE = 1;
    @Override
    public OutputDO execute(InputDO param) throws Exception {
        OutputDO outputDO = new OutputDO();
        if(param.getUserId() != null){
            //用户→店铺ids
            List<Long> shopIds = new ArrayList<>();
            outputDO.setEntitys(shopIds);
            outputDO.setType(USER_TYPE);
        }
        return outputDO;
    }
}
