package com.qqls.youxiangousys.pj.admin.service.impl;

import com.qqls.youxiangousys.pj.admin.dao.SysItemDao;
import com.qqls.youxiangousys.pj.admin.service.SysItemService;
import com.qqls.youxiangousys.pj.common.entity.Pagination;
import com.qqls.youxiangousys.pj.common.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysItemServiceImpl implements SysItemService {

    @Autowired
    private SysItemDao itemDao;

    /**
     * 根据商品名找商品
     * @param itemName
     * @param curPage
     * @param pageSize
     * @return
     */
    public Pagination findAllItem(String itemName, Integer curPage, Integer pageSize) {
        Assert.isEmpty(curPage == null || pageSize == null,"请选择当前页码或者每页条数！！");
        int count = itemDao.findAllItemByItemName(itemName);
        return null;
    }
}
