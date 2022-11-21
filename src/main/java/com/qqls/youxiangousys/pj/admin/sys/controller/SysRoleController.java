package com.qqls.youxiangousys.pj.admin.sys.controller;

import com.qqls.youxiangousys.pj.admin.common.entity.JsonResult;
import com.qqls.youxiangousys.pj.admin.sys.service.SysRoleService;
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
}