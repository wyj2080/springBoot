package com.test.springBoot.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @description 读取application里的配置，使用的时候Autowired注入
 * @date 2023/10/12
 */
@Getter
@Configuration
public class MyConfig {
    @Value("${test.name}")
    private String name;
}
