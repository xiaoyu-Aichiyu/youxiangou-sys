package com.qqls.youxiangousys.common.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration//对象的创建交给容器管理，加了这个注解表示这个类的配置类
@Data
public class PageProperties {
    private Integer pageSize;
}
