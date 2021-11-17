package com.test.springBoot.liteFlow;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeCondComponent;

/**
 * @author wangyinjia
 * @description
 * @date 2021/11/3
 */
@LiteflowComponent(id="tj",name="条件组件")
public class FlowTJ extends NodeCondComponent {
    @Override
    public void process() throws Exception {
        System.out.println("this tj");

    }

    @Override
    public String processCond() throws Exception {
        //flow.xml里写上可走的节点(a|b|c)，不然这里返回d,不执行的
        return "d";
    }
}
