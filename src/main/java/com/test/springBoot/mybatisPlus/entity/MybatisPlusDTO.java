package com.test.springBoot.mybatisPlus.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2021-07-28
 */
@Data
@ApiModel(value="MybatisPlus对象", description="")
public class MybatisPlusDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "配置")
    private Config config;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @Data
    public static class Config implements Serializable{
        private String key;
        private Integer status;
        private BigDecimal amount;
    }

}
