package com.qqls.youxiangousys.pj.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysLog implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;//序号
    private String username;//用户名
    private String operation;//操作
    private String method;//请求方法
    private String params;//请求参数
    private Long time;//执行时长
    private String ip;//IP地址
    //指定日期格式
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;//创建时间
}
