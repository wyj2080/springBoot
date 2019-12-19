package com.test.springBoot.annotation;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description: validDO
 * @Author: wangyinjia
 * @Date: 2019/12/19
 * @Version: 1.0
 */
@Data
public class ValidDO {
    @NotBlank(message = "name不能为空")
    private String name;

    private Integer age;
}
