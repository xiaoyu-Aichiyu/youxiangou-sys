package com.qqls.youxiangousys.pj.admin.sys.controller;


import com.qqls.youxiangousys.pj.admin.common.entity.JsonResult;
import com.qqls.youxiangousys.pj.admin.sys.entity.SysMenu;
import com.qqls.youxiangousys.pj.admin.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("menu")
public class SysMenuController {

    @Autowired
    private SysMenuService service;

    /**
     *查询所有菜单及父菜单名称
     * @return
     */
    @RequestMapping("doFindMenus")
    public JsonResult doFindMenus(){
        return new JsonResult(service.findObjects());
    }

    /**
     * 找菜单id，菜单名，父菜单id
     * @return
     */
    @RequestMapping("findZtreeMenu")
    public JsonResult findZtreeMenu(){
        return new JsonResult(service.findZtreeMenu());
    }

    @RequestMapping("saveMenu")
    public JsonResult saveMenu(SysMenu menu){
        JsonResult jr = new JsonResult(service.saveMenu(menu));
        jr.setMessage("添加成功");
        return jr;
    }

    @RequestMapping("updateMenu")
    public JsonResult updateMenu (SysMenu menu){
        JsonResult jr = new JsonResult(service.updateMenu(menu));
        jr.setMessage("修改成功");
        return jr;
    }
}
