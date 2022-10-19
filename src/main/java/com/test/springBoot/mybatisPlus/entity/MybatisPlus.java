package com.test.springBoot.mybatisPlus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
@Accessors(chain = true)
@TableName(value = "mybatis_plus", autoResultMap = true)
@ApiModel(value="MybatisPlus对象", description="")
public class MybatisPlus  implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId("id")
//    @JsonSerialize(using = ToStringSerializer.class)
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
    @TableField(typeHandler = FastjsonTypeHandler.class)
//    @TableField(typeHandler = JacksonTypeHandler.class)
    private String config;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

//    @TableLogic(value = "0", delval = "1")
    private Integer deleted;

    @Version
    private Integer version;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Config implements Serializable{
        private String a;
        private String b;
    }

}
