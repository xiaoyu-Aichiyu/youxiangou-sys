package com.qqls.youxiangousys.pj.sys.controller;

import com.qqls.youxiangousys.pj.common.entity.JsonResult;
import com.qqls.youxiangousys.pj.common.entity.Pagination;
import com.qqls.youxiangousys.pj.sys.entity.SysUser;
import com.qqls.youxiangousys.pj.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /**
     * 添加用户
     * @param user
     * @param roleIds
     * @return
     */
    @RequestMapping("addUser")
    public JsonResult addUser(SysUser user,@RequestParam("roleIds[]") Integer[] roleIds){
        JsonResult jr = new JsonResult(userService.insertUser(user,roleIds));
        jr.setMessage("添加成功");
        return jr;
    }

    /**
     * 通过用户id修改用户
     * @param user
     * @param Ids
     * @return
     */
    @RequestMapping("updateUser")
    public JsonResult updateUser(SysUser user,@RequestParam(required = false,value = "roleIds[]") Integer[] Ids){
        JsonResult jr = new JsonResult(userService.updateUser(user,Ids));
        jr.setMessage("修改成功");
        return jr;
    }

    /**
     * 通过用户id找角色id
     * @param userId
     * @return
     */
    @RequestMapping("findRoleUserById")
    public JsonResult findRoleUserById(Integer userId){
        List<Integer> list = (userService.findRoleUserById(userId));
        return new JsonResult(list);
    }
}
