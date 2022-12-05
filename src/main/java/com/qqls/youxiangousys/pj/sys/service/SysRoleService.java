package com.qqls.youxiangousys.pj.sys.service;

import com.qqls.youxiangousys.pj.common.entity.Pagination;
import com.qqls.youxiangousys.pj.sys.entity.SysRole;
import com.qqls.youxiangousys.pj.sys.vo.SysRoleMenuVO;

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

    /**
     * 添加角色，角色需要搜去
     * @param role
     * @param ids
     * @return
     */
    Integer insertRole(SysRole role, Integer[] ids);

    /**
     * 修改角色
     * @param vo
     * @return
     */
    Integer updateRoleById(SysRoleMenuVO vo);

    /**
     * 通过角色id找角色和角色的菜单id
     * @param id
     * @return
     */
    SysRoleMenuVO findRoleMenuIds(Integer id);

    /**
     * 删除
     * @param id
     * @return
     */
    Integer deleteRoleById(Integer id);
}
