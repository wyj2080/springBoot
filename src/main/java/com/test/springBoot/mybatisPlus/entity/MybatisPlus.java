package com.test.springBoot.mybatisPlus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("mybatis_plus")
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

    @ApiModelProperty(value = "配置")
    @TableField("config")
    private String config;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

}
