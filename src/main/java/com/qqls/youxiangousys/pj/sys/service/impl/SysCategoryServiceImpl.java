package com.qqls.youxiangousys.pj.sys.service.impl;

import com.qqls.youxiangousys.pj.common.annotation.RequiredLog;
import com.qqls.youxiangousys.pj.common.util.Assert;
import com.qqls.youxiangousys.pj.sys.dao.SysCategoryDao;
import com.qqls.youxiangousys.pj.sys.entity.SysCategory;
import com.qqls.youxiangousys.pj.sys.service.SysCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysCategoryServiceImpl implements SysCategoryService {
    @Autowired
    private SysCategoryDao dao;

    /**
     * 查询所有菜单及父菜单名称
     * @return
     */
    public List<Map<String, Object>> doFindCategory() {
        List<Map<String,Object>> list = dao.doFindCategory();
        Assert.isEmpty(list == null || list.size() == 0, "菜单不存在");
        return list;
    }
    /**
     * 找菜单id，菜单名，父菜单id
     * @return
     */
    public List<SysCategory> findZtreeCategory() {
        List<SysCategory> list = dao.findZtreeCategory();
        Assert.isEmpty(list == null || list.size() == 0,"菜单不存在" );
        return list;
    }


    /**
     * 添加
     * @param category
     * @return
     */
    public int saveCategory(SysCategory category) {
        Assert.isEmpty(category == null || category.getName() == null,"菜单名不能为空");
        SysCategory u = dao.findCategoryByName(category.getName(),category.getParentId());
        Assert.isEmpty(u != null ,"菜单已存在！");
        int n = dao.saveCategory(category);
        Assert.isEmpty( n == 0,"菜单添加失败" );
        return n;
    }

    /**
     * 根据id修改
     * @param category
     * @return
     */
    public int updateCategory(SysCategory category) {
        Assert.isEmpty(category == null || category.getId() == 0,"请填写数据" );
        SysCategory u = dao.findCategoryByName(category.getName(),category.getParentId());
        Assert.isEmpty(u != null ,"菜单已存在！");
        int n=dao.updateCategory(category);
        Assert.isEmpty(n == 0 ,"菜单修改失败或数据已不存在");
        return n;
    }

    /**
     * 根据id修改推荐
     * @param id
     * @param typeSell
     * @return
     */
    @RequiredLog("修改商品推荐")
    public int updateSell(Integer id,Integer typeSell) {
        System.out.println(typeSell);
        Assert.isEmpty(id == null || id == 0, "请选择要修改状态的用户！");
        Assert.isEmpty(typeSell == null, "操作有误！");
        Integer n = dao.updateSell(id, typeSell);
        Assert.isEmpty(n == 0,"推荐状态修改失败！");
        return n;
    }

    /**
     * 根据id批量删除商品信息
     * @param ids
     * @return
     */
    public int deleteCategory(Integer[] ids, Integer typeType) {
        System.out.println(typeType);
            String message1 = "请选择要删除的数据！";
            String message2 = "商品删除失败！";
            if (typeType == 1){
                message1 = "请选择要上架的数据！";
                message2 = "商品上架失败！";
            }
            Assert.isEmpty(ids == null || ids.length == 0,message1);
            Integer n = dao.deleteCategoryByIds(ids,typeType);
            Assert.isEmpty(n == 0,message2);
            return n;
    }
}
