package com.test.springBoot.clickhouse;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * DO
 */
@Data
@TableName(value = "ebi_request")
public class RequestLog {
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
