package com.test.springBoot;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan({"com.test.springBoot.*.mapper"})
//jetCache
@EnableMethodCache(basePackages = "com.test.springBoot")
@EnableCreateCacheAnnotation
//异步注解
@EnableAsync
public class Application extends SpringBootServletInitializer {
	/**
	 * 实现SpringBootServletInitializer
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		builder.sources(this.getClass());
		return super.configure(builder);
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}



}
