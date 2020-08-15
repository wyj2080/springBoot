package com.test.springBoot.easyexcel.entity;

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
    @ExcelProperty(value = "姓名", index = 0)
    private String name;

    @ExcelProperty(value = "年龄", index = 1)
    private Integer age;
}
