package com.test.springBoot.liteFlow;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

/**
 * @author wangyinjia
 * @description
 * @date 2021/11/3
 */
@Component("b")
public class FlowB extends NodeComponent {
    @Override
    public void process() throws Exception {
        Thread.sleep(2200);
        System.out.println("this b");
    }
}
