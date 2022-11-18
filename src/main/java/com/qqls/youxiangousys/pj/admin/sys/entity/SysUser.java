package com.qqls.youxiangousys.pj.admin.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//用户实体类
@Data
public class SysUser implements Serializable {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String salt;
    private String userImg;
    private String phone;
    private Integer state;
    private Integer gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedTime;
}
