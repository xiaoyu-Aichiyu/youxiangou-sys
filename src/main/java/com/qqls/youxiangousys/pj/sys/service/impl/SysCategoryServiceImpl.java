package com.qqls.youxiangousys.pj.sys.service.impl;

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


    public int updateCategory(SysCategory category) {
        Assert.isEmpty(category == null || category.getId() == 0,"请填写数据" );
        SysCategory u = dao.findCategoryByName(category.getName(),category.getParentId());
        Assert.isEmpty(u != null ,"菜单已存在！");
        int n=dao.updateCategory(category);
        Assert.isEmpty(n == 0 ,"菜单修改失败或数据已不存在");
        return n;
    }
}
