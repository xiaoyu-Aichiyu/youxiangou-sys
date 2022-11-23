package com.qqls.youxiangousys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class YouxiangouSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(YouxiangouSysApplication.class, args);
	}

}
