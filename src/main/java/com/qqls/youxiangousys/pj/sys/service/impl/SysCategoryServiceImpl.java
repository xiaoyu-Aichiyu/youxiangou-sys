package com.qqls.youxiangousys.pj.sys.service.impl;

import com.qqls.youxiangousys.pj.common.util.Assert;
import com.qqls.youxiangousys.pj.sys.dao.SysCategoryDao;
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
}
