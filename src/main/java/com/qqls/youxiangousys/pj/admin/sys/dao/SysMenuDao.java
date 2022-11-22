package com.qqls.youxiangousys.pj.admin.sys.dao;

import com.qqls.youxiangousys.pj.admin.sys.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysMenuDao {

    /**
     * 查询所有菜单及父菜单名称
     * @return
     */
    List<Map<String,Object>> findObjects();

    /**
     * 找菜单id，菜单名，父菜单id
     * @return
     */
    List<SysMenu> findZtreeMenu();

    /**
     * 添加菜单
     * @param menu
     * @return
     */
    int saveMenu(SysMenu menu);

    /**
     * 查找菜单名，上级菜单id
     * @param name
     * @param parentId
     * @return
     */
    @Select("select * from t_menu where name = #{name} and parent_id = #{parentId}")
    SysMenu findMenuByName(String name, Integer parentId);

    int updateMenu(SysMenu menu);
}
