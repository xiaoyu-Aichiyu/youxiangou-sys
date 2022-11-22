package com.qqls.youxiangousys.sys.service;

import com.qqls.youxiangousys.common.entity.Pagination;

public interface SysItemService {

    /**
     * 根据商品名查商品
     * @param itemName
     * @param curPage
     * @param pageSize
     * @return
     */
    Pagination findAllItem(String itemName, Integer curPage, Integer pageSize, Integer itemState);

    /**
     * 修改用户启用禁用
     * @param id
     * @param state
     * @return
     */
    Integer updateState(Integer id, Integer state);

    /**
     * 修改用户推荐状态
     * @param id
     * @param itemSell
     * @return
     */
    Integer updateSell(Integer id, Integer itemSell);

    /**
     * 根据id删除商品
     * @param ids
     * @return
     */
    Integer deleteItem(Integer[] ids);
}
