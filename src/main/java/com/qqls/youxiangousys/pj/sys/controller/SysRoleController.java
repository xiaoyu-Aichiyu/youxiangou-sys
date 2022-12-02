package com.qqls.youxiangousys.pj.sys.controller;

import com.qqls.youxiangousys.pj.common.entity.JsonResult;
import com.qqls.youxiangousys.pj.common.entity.Pagination;
import com.qqls.youxiangousys.pj.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//角色
@RestController
@RequestMapping("role")
public class SysRoleController {

    @Autowired
    private SysRoleService roleService;

    @RequestMapping("findAllRole")
    public JsonResult findAllRole(){
        return new JsonResult(roleService.findAllRole());
    }

    /**
     * 初始加载分页数据
     * @param name
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("findRole")
    public JsonResult findRole(String name,Integer curPage,Integer pageSize){
        Pagination obj = roleService.findRole(name, curPage, pageSize);
        return new JsonResult(obj);
    }
}
