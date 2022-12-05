package com.qqls.youxiangousys.pj.sys.dao;

import com.qqls.youxiangousys.pj.sys.entity.SysRole;
import com.qqls.youxiangousys.pj.sys.vo.SysRoleMenuVO;
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

    /**
     * 添加角色
     * @param role
     * @return
     */
    Integer insertRole(SysRole role);

    /**
     * 根据角色id修改角色
     * @param vo
     * @return
     */
    Integer updateRoleById(SysRoleMenuVO vo);

    /**
     * 通过角色id找角色数据和角色对应的菜单id
     * @param id
     * @return
     */
    SysRoleMenuVO findRoleMenuIds(Integer id);

    /**
     * 根据ID删除角色
     * @param id
     * @return
     */
    Integer deleteRoleById(Integer id);
}
