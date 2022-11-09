package com.qqls.youxiangousys.pj.admin.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysItemDao {

    /**
     * 根据商品名查商品
     * @param itemName
     * @return
     */
    int findAllItemByItemName(String itemName);
}
