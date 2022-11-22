package com.qqls.youxiangousys.pj.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserRoleDao {

    /**
     * 添加用户角色关系数据
     * @param id
     * @param roleIds
     * @return
     */
    Integer insertUserRole(@Param("userId") Integer id,@Param("roles") Integer[] roleIds);

    /**
     * 根据用户id删除用户角色关系数据
     * @param id
     * @return
     */
    Integer deleteUserRoleByUserId(Integer id);

    /**
     *查询角色id
     * @param userId
     * @return
     */
    List<Integer> findRoleUserById(Integer userId);
}
