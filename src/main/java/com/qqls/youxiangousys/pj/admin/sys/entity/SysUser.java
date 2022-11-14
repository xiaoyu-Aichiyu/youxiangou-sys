package com.qqls.youxiangousys.pj.admin.sys.entity;

import lombok.Data;

import java.io.Serializable;

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
}
