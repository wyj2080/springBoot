package com.test.springBoot.apiDoc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangyinjia
 * @description
 * @date 2021/7/21
 */
@Data
@ApiModel(value="api对象", description="api表")
public class ApiOutputDTO {

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("年龄")
    private Integer age;
}
