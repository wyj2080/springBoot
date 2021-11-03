package com.test.springBoot.liteFlow;

import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.entity.data.DefaultSlot;
import com.yomahub.liteflow.entity.data.LiteflowResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    public void test() {
        LiteflowResponse<DefaultSlot> response = flowExecutor.execute2Resp("chain1", "arg");
        System.out.println(response.toString());
    }

}
