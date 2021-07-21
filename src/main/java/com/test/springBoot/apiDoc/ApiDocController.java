package com.test.springBoot.apiDoc;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangyinjia
 * @description
 * @date 2021/7/21
 */
@RestController
@RequestMapping("/api/doc")
@Api(tags = "api文档注释")
public class ApiDocController {


    @ApiOperation(value = "方法A", notes = "方法A")
    @ApiOperationSupport(order = 1)
    @ApiImplicitParam(name = "name",value = "姓名",required = true)
    @GetMapping(value="/fun")
    public ApiOutputDTO getA(@RequestParam("name")String name) {
        ApiOutputDTO outputDTO = new ApiOutputDTO();
        outputDTO.setName(name);
        return outputDTO;
    }


}
