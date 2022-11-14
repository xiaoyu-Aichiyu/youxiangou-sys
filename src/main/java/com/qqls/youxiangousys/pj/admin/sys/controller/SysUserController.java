package com.qqls.youxiangousys.pj.admin.sys.controller;

import com.qqls.youxiangousys.pj.admin.common.entity.JsonResult;
import com.qqls.youxiangousys.pj.admin.common.entity.Pagination;
import com.qqls.youxiangousys.pj.admin.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//用户
@RequestMapping("user")
@RestController
public class SysUserController {

    @Autowired
    private SysUserService userService;

    /**
     * 分页数据展示
     * @param name
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("findUser")
    public JsonResult findUser(String name,Integer curPage,Integer pageSize){
        Pagination obj = userService.findUser(name,curPage,pageSize);
        return new JsonResult(obj);
    }

    /**
     * 修改状态
     * @param id
     * @param state
     * @return
     */
    @RequestMapping("updateValId")
    public JsonResult updateValId(Integer id,Integer state){
        JsonResult jr = new JsonResult(userService.updateValId(id,state));
        jr.setMessage("修改成功");
        return jr;
    }
}
