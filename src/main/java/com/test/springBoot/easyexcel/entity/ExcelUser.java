package com.test.springBoot.easyexcel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author wangyinjia
 * @description
 * @date 2020/8/12
 */
@Data
public class ExcelUser {
    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("年龄")
    private Integer age;
}
