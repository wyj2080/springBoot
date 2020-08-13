package com.test.springBoot.easyexcel.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangyinjia
 * @description
 * @date 2020/8/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelUser {
    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("年龄")
    private Integer age;

    @ExcelIgnore
    private String hl;
}
