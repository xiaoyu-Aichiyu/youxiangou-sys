package com.qqls.youxiangousys.pj.sys.controller;


import com.qqls.youxiangousys.pj.common.entity.JsonResult;
import com.qqls.youxiangousys.pj.sys.entity.SysCategory;
import com.qqls.youxiangousys.pj.sys.entity.SysMenu;
import com.qqls.youxiangousys.pj.sys.service.SysCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class SysCategoryCotroller {

    @Autowired
    private SysCategoryService service;

    /**
     * 查询所有菜单及父菜单名称
     * @return
     */
    @RequestMapping("doFindCategory")
    public JsonResult doFindCategory(){
        return new JsonResult(service.doFindCategory());
    }

    /**
     * 找菜单id，菜单名，父菜单id
     * @return
     */
    @RequestMapping("findZtreeCategory")
    public JsonResult findZtreeCategory(){
        return new JsonResult(service.findZtreeCategory());
    }

    /**
     * 添加
     * @param category
     * @return
     */
    @RequestMapping("saveCategory")
    public JsonResult saveCategory(SysCategory category){
        JsonResult jr = new JsonResult(service.saveCategory(category));
        jr.setMessage("添加成功");
        return jr;
    }

    /**
     * 修改
     * @param category
     * @return
     */
    @RequestMapping("updateCategory")
    public JsonResult updateCategory (SysCategory category){
        JsonResult jr = new JsonResult(service.updateCategory(category));
        jr.setMessage("修改成功");
        return jr;
    }

    /**
     * 推荐
     * @param id
     * @param typeSell
     * @return
     */
    @RequestMapping("updateSell")
    public JsonResult updateSell(Integer id , Integer typeSell){
        JsonResult jr = new JsonResult(service.updateSell(id,typeSell));
        jr.setMessage("推荐成功");
        return jr;
    }
    /**
     * 根据id批量删除商品
     * @param ids
     * @return
     */
    @RequestMapping("deleteCategory")
    public JsonResult deleteCategor(@RequestParam("ids[]") Integer[] ids, Integer typeType){
        return new JsonResult(service.deleteCategory(ids,typeType));
    }
}
