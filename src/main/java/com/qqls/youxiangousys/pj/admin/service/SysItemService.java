package com.qqls.youxiangousys.pj.admin.service;

import com.qqls.youxiangousys.pj.common.entity.Pagination;

public interface SysItemService {

    /**
     * 根据商品名查商品
     * @param itemName
     * @param curPage
     * @param pageSize
     * @return
     */
    Pagination findAllItem(String itemName, Integer curPage, Integer pageSize);
}
