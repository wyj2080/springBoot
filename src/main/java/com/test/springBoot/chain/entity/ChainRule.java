package com.test.springBoot.chain.entity;

import lombok.Data;

/**
 * @Description: 链条抽象类
 * @Author: wangyinjia
 * @Date: 2019/11/25
 * @Version: 1.0
 */
public abstract class ChainRule {
    //下一级链条
    protected ChainRule chainRule;

    public abstract OutputDO execute(InputDO param) throws Exception;

    /**设置下一节链条*/
    public void setRule(ChainRule chainRule) {
        this.chainRule = chainRule;
    }

}
