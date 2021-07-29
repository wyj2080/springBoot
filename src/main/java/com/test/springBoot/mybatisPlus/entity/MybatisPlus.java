package com.test.springBoot.mybatisPlus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2021-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "mybatis_plus")
@ApiModel(value="MybatisPlus对象", description="")
public class MybatisPlus extends Model<MybatisPlus> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "年龄")
    @TableField("age")
    private Integer age;

    private Integer num;

    private BigDecimal amount;


    /**
     * 自动解析，上面tableName里加autoResultMap
     * TableField里typeHandler = FastjsonTypeHandler.class
     */
    @ApiModelProperty(value = "配置")
    private String config;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableLogic(value = "0", delval = "1")
    private Integer deleted;

    @Version
    private Integer version;

}
