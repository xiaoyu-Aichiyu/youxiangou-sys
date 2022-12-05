package com.qqls.youxiangousys.pj.mp.service;

import com.qqls.youxiangousys.pj.common.entity.Pagination;
import com.qqls.youxiangousys.pj.sys.entity.SysItem;

public interface MpItemService {


    /**
     * 查找首页推荐栏的商品
     * @param sellCurPage 当前页
     * @param sellPageSize 每页条数
     * @param sellState 推荐状态
     * @return
     */
    Pagination findSellItem(Integer sellCurPage, Integer sellPageSize, Integer sellState);

    /**
     * 查找首页猜你喜欢栏的商品
     * @param likeCurPage 当前页
     * @param likePageSize 每页条数
     * @param likeItemType 商品类型id
     * @return
     */
    Pagination findLikeItem(Integer likeCurPage, Integer likePageSize, Integer likeItemType);

    SysItem findItemById(Integer id);
}
