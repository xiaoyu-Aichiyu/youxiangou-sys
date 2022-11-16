package com.qqls.youxiangousys.pj.admin.sys.service;

import com.qqls.youxiangousys.pj.admin.common.entity.Pagination;

public interface SysItemService {

    /**
     * 根据商品名查商品
     * @param itemName
     * @param curPage
     * @param pageSize
     * @return
     */
    Pagination findAllItem(String itemName, Integer curPage, Integer pageSize);

    /**
     * 修改用户启用禁用
     * @param id
     * @param state
     * @return
     */
    Integer updateState(Integer id, Integer state);

    Integer updateTj(Integer id, Integer itemTj);
}
