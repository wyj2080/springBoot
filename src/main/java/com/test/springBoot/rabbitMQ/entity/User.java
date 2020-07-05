package com.test.springBoot.rabbitMQ.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String addr;
    private String schoolName;
    private BigDecimal salary;
    private String job;
    private Date createTime;
    private Date updateTime;
}
