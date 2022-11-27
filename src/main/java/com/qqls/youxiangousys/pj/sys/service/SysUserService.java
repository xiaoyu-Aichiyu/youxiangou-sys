package com.qqls.youxiangousys.pj.sys.service;

import com.qqls.youxiangousys.pj.common.entity.Pagination;
import com.qqls.youxiangousys.pj.sys.entity.SysUser;

import java.util.List;

public interface SysUserService {

    /**
     *查找角色
     * @param name 名字
     * @param curPage   当前页
     * @param pageSize  每页条数
     * @return
     */
    Pagination findUser(String name, Integer curPage, Integer pageSize,Integer state);

    /**
     * 通过用户id修改用户禁用启用功能
     * @param id
     * @param state
     * @return
     */
    Integer updateValId(Integer id, Integer state);

    /**
     * 添加
     * @param user
     * @param roleIds
     * @return
     */
    Integer insertUser(SysUser user, Integer[] roleIds);

    /**
     *通过用户id修改用户
     * @param user
     * @param Ids
     * @return
     */
    Integer updateUser(SysUser user, Integer[] Ids);

    /**
     * 通过用户id找角色id
     * @param userId
     * @return
     */
    List<Integer> findRoleUserById(Integer userId);

    /**
     * 根据ID删除用户
     * @param ids
     * @return
     */
    Integer deleteUser(Integer[] ids);

    /**
     * 导出所有
     */
    void exportAll();
}
