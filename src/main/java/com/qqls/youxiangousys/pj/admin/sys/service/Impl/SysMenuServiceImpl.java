package com.qqls.youxiangousys.pj.admin.sys.service.impl;



import com.qqls.youxiangousys.pj.admin.common.util.Assert;
import com.qqls.youxiangousys.pj.admin.sys.entity.SysMenu;
import com.qqls.youxiangousys.pj.admin.sys.dao.SysMenuDao;
import com.qqls.youxiangousys.pj.admin.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuDao dao;

    /**
     * 查询所有菜单及父菜单名称
     * @return
     */
    public List<Map<String, Object>> findObjects() {
        List<Map<String,Object>> list = dao.findObjects();
        Assert.isEmpty(list == null || list.size() == 0, "菜单不存在");
        return list;
    }

    /**
     * 找菜单id，菜单名，父菜单id
     * @return
     */
    public List<SysMenu> findZtreeMenu() {
        List<SysMenu> list = dao.findZtreeMenu();
        Assert.isEmpty(list == null || list.size() == 0,"菜单不存在" );
        return list;
    }


    /**
     * 添加
     * @param menu
     * @return
     */
    public int saveMenu(SysMenu menu) {
        Assert.isEmpty(menu == null || menu.getName() == null,"菜单名不能为空");
        SysMenu u = dao.findMenuByName(menu.getName(),menu.getParentId());
        Assert.isEmpty(u != null ,"菜单已存在！");
        int n = dao.saveMenu(menu);
        Assert.isEmpty( n == 0,"菜单添加失败" );
        return n;
    }


    public int updateMenu(SysMenu menu) {
        Assert.isEmpty(menu == null || menu.getId() == null,"请填写数据" );
        SysMenu u = dao.findMenuByName(menu.getName(),menu.getParentId());
        Assert.isEmpty(u != null ,"菜单已存在！");
        int n=dao.updateMenu(menu);
        Assert.isEmpty(n == 0 ,"菜单修改失败或数据已不存在");
        return n;
    }
}
