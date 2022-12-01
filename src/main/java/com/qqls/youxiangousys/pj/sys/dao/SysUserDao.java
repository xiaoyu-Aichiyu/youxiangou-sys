package com.qqls.youxiangousys.pj.sys.dao;

import com.qqls.youxiangousys.pj.sys.entity.SysUser;
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
    List<SysUser> findUser(@Param("name") String name,@Param("start") Integer start,@Param("pageSize") Integer pageSize,@Param("state") Integer state);

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

    /**
     * 根据ID删除用户
     * @param ids
     * @return
     */
    Integer deleteUser(Integer[] ids);

    /**
     * 导出:查询所有用户信息
     * @return
     */
    List<SysUser> findAllUser();

    /**
     * 查询重复导入的用户
     * @param name
     * @param username
     * @return
     */
    SysUser findUserByNameAnduserName(String name, String username);

    /**
     * 导入插入商品
     * @param userData
     * @return
     */
    //Integer insertUserExcel(List<SysUser> userData);
}
