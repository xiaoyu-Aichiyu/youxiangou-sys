package com.qqls.youxiangousys.pj.sys.service;

import com.qqls.youxiangousys.pj.common.entity.Pagination;
import com.qqls.youxiangousys.pj.sys.entity.SysRole;

import java.util.List;

//角色
public interface SysRoleService {
    List<SysRole> findAllRole();

    /**
     * 初始加载分页数据
     * @param name
     * @param curPage
     * @param pageSize
     * @return
     */
    Pagination findRole(String name, Integer curPage, Integer pageSize);
}
