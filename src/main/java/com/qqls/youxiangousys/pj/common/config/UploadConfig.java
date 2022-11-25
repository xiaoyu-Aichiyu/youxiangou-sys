package com.qqls.youxiangousys.pj.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration//@Configuration
public class UploadConfig {

		//@Bean注解相当于Spring配置文件中的bean对象,name相当于bean标签中的id
		//也就是加了该注解的返回值对象会交给容器管理
		@Bean(name="multipartResolver")
		public MultipartResolver multipartResolver(){
			return new CommonsMultipartResolver();
		}
}
