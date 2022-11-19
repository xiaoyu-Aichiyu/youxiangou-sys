package com.qqls.youxiangousys.pj.admin.sys.service;

import com.qqls.youxiangousys.pj.admin.common.entity.Pagination;
import com.qqls.youxiangousys.pj.admin.sys.entity.SysUser;

public interface SysUserService {

    /**
     *查找角色
     * @param name 名字
     * @param curPage   当前页
     * @param pageSize  每页条数
     * @return
     */
    Pagination findUser(String name, Integer curPage, Integer pageSize);

    /**
     * 通过用户id修改用户禁用启用功能
     * @param id
     * @param state
     * @return
     */
    int updateValId(Integer id, Integer state);

    /**
     * 添加
     * @param user
     * @param roleIds
     * @return
     */
    int insertUser(SysUser user, Integer[] roleIds);
}
