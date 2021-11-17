package com.test.springBoot.liteFlow;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

/**
 * @author wangyinjia
 * @description
 * @date 2021/11/3
 */
@Component("d")
public class FlowD extends NodeComponent {
    @Override
    public void process() throws Exception {
        System.out.println("this d");
        for (int i = 0; i < 10000; i++) {
            System.out.println("d"+i);
        }
    }
}
