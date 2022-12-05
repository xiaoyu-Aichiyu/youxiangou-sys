package com.qqls.youxiangousys.pj.sys.controller;

import com.qqls.youxiangousys.pj.common.entity.JsonResult;
import com.qqls.youxiangousys.pj.common.entity.Pagination;
import com.qqls.youxiangousys.pj.sys.entity.SysRole;
import com.qqls.youxiangousys.pj.sys.service.SysRoleService;
import com.qqls.youxiangousys.pj.sys.vo.SysRoleMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 添加角色，角色需要授权
     * @param role
     * @param menuIds
     * @return
     */
    @RequestMapping("saveRole")
    public JsonResult saveRole(SysRole role,@RequestParam("menuIds[]") Integer[] menuIds){
        JsonResult jr = new JsonResult(roleService.insertRole(role,menuIds));
        jr.setMessage("添加成功");
        return jr;
    }

    /**
     *修改角色
     * @param vo
     * @return
     */
    @RequestMapping("updateRoleById")
    public JsonResult updateRoleById(SysRoleMenuVO vo) {
        JsonResult jr = new JsonResult(roleService.updateRoleById(vo));
        jr.setMessage("修改成功");
        return jr;
    }

    /**
     * 通过id删除角色
     * @param id
     * @return
     */
    @RequestMapping("deleteRole")
    public JsonResult deleteRoleById(Integer id) {
        JsonResult jr = new JsonResult(roleService.deleteRoleById(id));
        jr.setMessage("删除成功");
        return jr;
    }

    /**
     * 通过角色id找角色和角色的菜单id
     * @param id
     * @return
     */
    @RequestMapping("findRoleMenuIdsByRoleId")
    public JsonResult findRoleMenuIds(Integer id) {
        SysRoleMenuVO vo = roleService.findRoleMenuIds(id);
        return new JsonResult(vo);
    }
}
