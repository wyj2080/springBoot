package com.test.springBoot.liteFlow;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

/**
 * @author wangyinjia
 * @description
 * @date 2021/11/3
 */
@Component("c")
public class FlowC extends NodeComponent {
    @Override
    public void process() throws Exception {
        System.out.println("this c");
    }
}
