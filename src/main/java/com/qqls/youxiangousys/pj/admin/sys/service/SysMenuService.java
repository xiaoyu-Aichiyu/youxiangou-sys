package com.qqls.youxiangousys.pj.admin.sys.service;



import com.qqls.youxiangousys.pj.admin.sys.entity.SysMenu;

import java.util.List;
import java.util.Map;

public interface SysMenuService {

    /**
     * 查询所有菜单及父菜单名称
     * @return
     */
    List<Map<String, Object>> findObjects();

    /**
     * 找菜单id，菜单名，父菜单id
     * @return
     */
    List<SysMenu> findZtreeMenu();

    /**
     * 添加
     * @param menu
     * @return
     */
    int saveMenu(SysMenu menu);

    int updateMenu(SysMenu menu);
}
