package com.qqls.youxiangousys.pj.sys.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysRoleMenuVO implements Serializable {
    private static final long serialVersionUID = 6909409211931674678L;
    private Integer id;//角色id
    private String name ;//角色名
    private String remarks;//角色描述
    private List<Integer> menuIds;//角色对应的菜单集合
}
