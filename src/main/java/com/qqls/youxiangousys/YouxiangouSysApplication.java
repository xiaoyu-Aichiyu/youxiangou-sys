package com.qqls.youxiangousys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync//启动异步注解
@EnableCaching//启动缓存注解
public class YouxiangouSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(YouxiangouSysApplication.class, args);
	}

}
