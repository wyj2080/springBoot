package com.test.springBoot.liteFlow;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

/**
 * @author wangyinjia
 * @description
 * @date 2021/11/3
 */
@LiteflowComponent(id="a",name="组建A")
public class FlowA extends NodeComponent {
    @Override
    public void process() throws Exception {

        System.out.println("this a"+this.getTag());
        for (int i = 0; i < 10000; i++) {
            System.out.println("a"+i);
        }
//        MySlot slot = getSlot();
//        System.out.println("a:"+slot.toString());
//        slot.setAge(18);
//        slot.setName("哈哈");

    }
}
