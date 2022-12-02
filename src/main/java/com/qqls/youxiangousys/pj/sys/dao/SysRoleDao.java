package com.qqls.youxiangousys.pj.sys.dao;

import com.qqls.youxiangousys.pj.sys.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleDao {

     List<SysRole> findAllRole();

    /**
     * 统计角色总条数，用来分页
     * @param roleName
     * @return
     */
    Integer countRole(String roleName);

    /**
     * 根据角色名找角色
     * @param name
     * @param start
     * @param pageSize
     * @return
     */
    List<SysRole> findRole(@Param("name")String name, @Param("start")int start, @Param("pageSize")Integer pageSize);
}
