package com.qqls.youxiangousys.pj.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SysCategory {
    private int id;
    private String name;
    private Integer typeType=1;
    private Integer parentId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;
    private Integer typeSell;
}
