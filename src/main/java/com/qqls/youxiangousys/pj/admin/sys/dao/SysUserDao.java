package com.qqls.youxiangousys.pj.admin.sys.dao;

import com.qqls.youxiangousys.pj.admin.sys.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserDao {

    /**
     * 统计用户总条数，用来分页
     * @param name
     * @return
     */
    Integer countUser(String name);

    /**
     * 根据用户名找用户
     * @param name
     * @param start
     * @param pageSize
     * @return
     */
    List<SysUser> findUser(@Param("name") String name,@Param("start") Integer start,@Param("pageSize") Integer pageSize);

    /**
     * 根据用户id修改用户状态
     * @param id
     * @param state
     * @return
     */
    int updateValId(@Param("id") Integer id,@Param("state") Integer state);
}
