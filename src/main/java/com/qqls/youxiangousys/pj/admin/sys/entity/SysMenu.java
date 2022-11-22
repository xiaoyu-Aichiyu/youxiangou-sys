package com.qqls.youxiangousys.pj.admin.sys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMenu implements Serializable {
    private static final long serialVersionUID = -8805983256624854549L;
    private Integer id;
    private String name;
    private String menuNote;
    private Integer menuType=1;
    private Integer parentId;
    private String permission;
    private String createdUser;
    private String modifiedUser;
    private Date createdTime;
    private Date modifiedTime;
}
