package com.qqls.youxiangousys.pj.admin.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserRoleDao {

    /**
     * 添加用户角色关系数据
     * @param id
     * @param roleIds
     * @return
     */
    int insertUserRole(@Param("userId") Integer id,@Param("roles") Integer[] roleIds);
}
