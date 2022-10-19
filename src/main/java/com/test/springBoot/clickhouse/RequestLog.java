package com.test.springBoot.clickhouse;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * DO
 */
@Data
@TableName(value = "ebi_request")
public class RequestLog {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private String url;
    private String protocolVersion;
    private Integer statusCode;
    private String reasonPhrase;
    private String msg;
    private String mark;
    private String requestTime;
    private String createTime;
    private String updateTime;
}
