package com.qqls.youxiangousys.pj.mp.sys.service;

import com.qqls.youxiangousys.pj.admin.sys.entity.SysItem;

import java.util.List;

public interface ItemService {


    /**
     * 查找首页推荐栏的商品
     * @param sellCurPage 当前页
     * @param sellPageSize 每页条数
     * @param sellState 推荐状态
     * @return
     */
    List<SysItem> findSellItem(Integer sellCurPage, Integer sellPageSize, Integer sellState);

}
