package com.qqls.youxiangousys.pj.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限和菜单关系dao层
 * @author Administrator
 *
 */
@Mapper
public interface SysRoleMenuDao {

	/**
	 * 插入角色和菜单关系图
	 * @param id
	 * @param ids
	 * @return
	 */
	Integer insertRoleMenu(@Param("roleId") Integer id, @Param("ids") Integer[] ids);

	/**
	 * 通过菜单id导出权限菜单关系数据
	 * @param menuId
	 * @return
	 */
	Integer deleteRoleMenuByMenuId(Integer menuId);

	/**
	 * 通过角色id删除角色菜单关系数据
	 * @param roleId
	 * @return
	 */
    Integer deleteRoleMenuByRoleId(Integer roleId);

	/**
	 * 通过roleId查询所有菜单Id
	 * @param roleIds	角色Ids
	 * @return
	 */
	List<Integer> findMenuIdsByRoleIds(Integer[] roleIds);
}
