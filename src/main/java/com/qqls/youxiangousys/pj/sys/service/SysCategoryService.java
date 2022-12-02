package com.qqls.youxiangousys.pj.sys.service;

import com.qqls.youxiangousys.pj.sys.entity.SysCategory;

import java.util.List;
import java.util.Map;

public interface SysCategoryService {

    /**
     * 查询所有菜单及父菜单名称
     * @return
     */
    List<Map<String, Object>> doFindCategory();

    /**
     * 找菜单id，菜单名，父菜单id
     * @return
     */
    List<SysCategory> findZtreeCategory();

    /**
     *
     * 添加菜单
     * @param category
     * @return
     */
    int saveCategory(SysCategory category);

    /**
     * 修改分类
     * @param category
     * @return
     */
    int updateCategory(SysCategory category);

    /**
     * 修改推荐
     * @param id
     * @param typeSell
     * @return
     */
    int updateSell(Integer id, Integer typeSell);


    int deleteCategory(Integer[] ids, Integer typeType);
}
