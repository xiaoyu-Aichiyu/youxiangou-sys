package com.qqls.youxiangousys.pj.admin.sys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//角色
@Data
@NoArgsConstructor//无参
@AllArgsConstructor//有参
public class SysRole implements Serializable {
    private Integer id;
    private String roleName;
}
