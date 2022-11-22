package com.qqls.youxiangousys.sys.dao;

import com.qqls.youxiangousys.sys.entity.SysUser;
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
    Integer updateValId(@Param("id") Integer id,@Param("state") Integer state);

    /**
     * 根据账号查找用户是否存在
     * @param username
     * @return
     */
    SysUser findUserByName(String username);

    /**
     * 添加用户
     * @param user
     * @return
     */
    Integer insertUser(SysUser user);

    /**
     * 通过用户id修改用户
     * @param user
     * @return
     */
    Integer updateUser(SysUser user);
}
