package com.test.springBoot.liteFlow;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.annotation.LiteflowRetry;
import com.yomahub.liteflow.core.NodeComponent;

/**
 * @author wangyinjia
 * @description
 * @date 2021/11/16
 */
@LiteflowRetry(3)
@LiteflowComponent(id="oneRetry",name="单重试")
public class OneRetry extends NodeComponent {
    @Override
    public void process() throws Exception {
        System.out.println("单个重试");
        Integer a= Integer.valueOf("aa");
        System.out.println("单个结束");
    }
}
