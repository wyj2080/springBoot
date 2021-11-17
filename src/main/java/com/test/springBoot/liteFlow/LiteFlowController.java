package com.test.springBoot.liteFlow;

import com.alibaba.fastjson.JSONObject;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.entity.data.LiteflowResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wangyinjia
 * @description liteFlow流程编排
 * @date 2021/9/1
 */
@RestController
@RequestMapping("/lite-flow")
@Api(tags = "liteFlow流程编排")
public class LiteFlowController {
    @Resource
    private FlowExecutor flowExecutor;


    @ApiOperation(value = "测试", notes = "测试")
    @GetMapping(value="/test")
    @Transactional(rollbackFor = Exception.class)
    public void test() {
        //errorResume="false"出错后，下面的节点不执行
        //when group一样也是顺序的，when内的并行
        //@LiteflowRetry(3)注解不管用
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aaa","123");
        LiteflowResponse<MySlot> response = flowExecutor.execute2Resp("chain1", jsonObject, MySlot.class);
        System.out.println(response.toString());

        //重新加载配置
        flowExecutor.reloadRule();

        //全局切面implements ICmpAroundAspect，前置和后置方法
    }

}
