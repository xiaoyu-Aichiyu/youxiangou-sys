package com.qqls.youxiangousys.pj.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

//角色
@Data
@NoArgsConstructor//无参
@AllArgsConstructor//有参
public class SysRole implements Serializable {
    private Integer id;
    private String name;//角色姓名
    private String remarks;//备注
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date creationTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modificationTime;//修改时间
    private String createUser;//创建用户
    private String updateUser;//修改用户
}
